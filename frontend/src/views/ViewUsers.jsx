import React, { useState, useEffect } from "react";
import Footer from "../components/Footer";
import axios from "axios";
import { getToken } from "../utils/cookieUtils";
import UserList from "../components/UserList";
import Navbar from "../components/Navbar";
import NavbarAlt from "../components/NavbarAlt";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import UserDialog from "../components/UserDialog";

export default function ViewUsers() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigateTo = useNavigate();

  async function addUser(user) {
    try {
      const result = await axios.post("http://localhost:8080/api/user/", user, {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      toast.success("User Added", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      setSelectedUser(null);
      setOpen(false);

    } catch (error) {
      console.log(error);
      if (
        error.response?.data?.status === 400 &&
        error.response?.data?.message.includes("Duplicate Entry")
      ) {
        setErrorMessage(`User with the email already present`);
      } else if (
        error.status === 400 &&
        error.response?.data?.errors?.length > 0
      ) {
        setErrorMessage(error.response.data.errors[0]);
      }
    }
  }

  async function updateUser() {
    try {
      const result = await axios.put(
        "http://localhost:8080/api/user/",
        selectedUser,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      toast.success("User Updated", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      setTimeout(() => {
        navigateTo("/viewUsers");
      }, 1000);

      setErrorMessage("");

      setOpen(false);
      setSelectedUser(null);
    } catch (error) {
      console.log(error);
      if (
        error.response?.data?.status === 400 &&
        error.response?.data?.message.includes("Duplicate Entry")
      ) {
        setErrorMessage(`User with the email already present`);
      } else if (
        error.status === 400 &&
        error.response?.data?.errors?.length > 0
      ) {
        setErrorMessage(error.response.data.errors[0]);
      }
    }
  }

  async function fetchUser() {
    try {
      const response = await axios.get("http://localhost:8080/api/auth/me", {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      if (response.data) {
        setUser(response.data);
      } else {
        setUser(null);
      }
    } catch (error) {
      console.log(error);
      if (error.response?.data?.message?.startsWith("JWT")) {
        toast.error(`Login time expired. Please Login Again`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });

        setTimeout(() => {
          localStorage.clear();
          navigateTo("/lib/signin");
        }, 2000);
      } else if (error.response?.status == 404) {
        toast.error(`Error fetching user. Please log in again`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });

        setTimeout(() => {
          navigateTo("/lib/signin");
        }, 2000);
      }
      setUser(null);
    }
  }

  async function fetchUsers() {
    try {
      const response = await axios.get("http://localhost:8080/api/user/", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });
      const result = response.data;

      setUsers([...result]);
    } catch (error) {
      console.log(error);
    }
  }

  async function secure() {
    const token = getToken();

    if (!token) {
      navigateTo("/");
    }
  }

  const handleAddOrUpdateUser = async (user) => {
    if (selectedUser.id) {
      
      await updateUser();
    } else {

      await addUser(user);
    }
    fetchUsers();
  };

  const handleDeleteUser = async (id) => {
    try {
      const result = await axios.delete(
        "http://localhost:8080/api/user/" + id,
        {
          headers: {
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      toast.success("User Deleted", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      await fetchUsers();
    } catch (error) {
      console.log(error);
      if (error.response) {
        if (error.response.data.message.startsWith("JWT expired")) {
          toast.error(`Login time expired. Please Login Again`, {
            position: "top-center",
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: false,
            progress: undefined,
          });

          setTimeout(() => {
            localStorage.clear();
            navigateTo("/lib/signin");
          }, 2000);
        }
      }
    }
  };

  useEffect(() => {
    const promise1 = secure();
    const promise2 = fetchUser();
    const promise3 = fetchUsers();

    Promise.all([promise1, promise2, promise3]).then((values) => {
      setLoading(false);
    });
  }, []);

  return loading ? (
    <>Loading...</>
  ) : (
    <>
      {user ? <NavbarAlt user={user} /> : <Navbar />}
      <div className="bg-gray-100 min-h-screen p-6 relative">
        {/* Background Art */}
        <div
          className="absolute inset-0 bg-fixed bg-no-repeat bg-cover opacity-10"
          style={{
            backgroundImage: 'url("/path-to-your-background-image.jpg")',
          }}
        ></div>
        <div className="relative max-w-7xl mx-auto bg-white bg-opacity-80 backdrop-blur-md shadow-lg rounded-lg p-8">
          <h1 className="text-4xl font-bold text-primary mb-6 text-center">
            View Users
          </h1>
          <div className="flex justify-end">
            <button
              onClick={() => {
                setOpen(true);
                setSelectedUser(null);
                setErrorMessage("");
              }}
              className="bg-primary box-border text-white px-4 py-2 rounded-md border-primary border-2
              hover:border-black transition"
            >
              Add User
            </button>
          </div>
          <UserList
            users={users}
            onEdit={(user) => {
              setOpen(true);
              setErrorMessage("");
              setSelectedUser(user);
            }}
            onDelete={handleDeleteUser}
          />
        </div>
        <ToastContainer />
        <UserDialog
          open={open}
          setOpen={setOpen}
          onSubmit={handleAddOrUpdateUser}
          selectedUser={selectedUser}
          setSelectedUser={setSelectedUser}
          errorMessage={errorMessage}
        />
      </div>
      <Footer />
    </>
  );
}
