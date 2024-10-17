import React, { useState, useEffect } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary
import axios from "axios";
import { getToken } from "../utils/cookieUtils";
import UserForm from "../components/UserForm";
import UserList from "../components/UserList";
import Navbar from "../components/Navbar";
import NavbarAlt from "../components/NavbarAlt";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

export default function ViewUsers() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null); // For editing a user
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

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

      console.log(result);

      toast.success("User Added", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } catch (error) {
      console.log(error);

      if (error.response) {
        toast.error(`Add User failed: ${error.response.data.message}`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
      } else {
        toast.error(`Add User failed: Network Error`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
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
      console.log(result);

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
    } catch (error) {
      console.log(error);
      if (error.response) {
        toast.error(`Update failed: ${error.response.data.message}`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
      } else {
        toast.error(`Update failed: Network Error`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
      }
    }
  }

  async function fetchUser() {
    try {
      const response = await axios.get("http://localhost:8080/api/user/me", {
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

      console.log(result);

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

  useEffect(() => {
    secure();
    fetchUser();
    fetchUsers();
    setLoading(false);
  }, []);

  const handleAddOrUpdateUser = async (user) => {
    if (selectedUser.id) {
      // Update existing user
      console.log(selectedUser);
      await updateUser();
    } else {
      // Add new user with a unique ID
      console.log("Add user ", user);

      await addUser(user);
    }
    setSelectedUser(null);
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

      console.log(result);
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
            navigateTo("/signin");
          }, 2000);
        }
      }
    }
  };

  return (loading ? <div>Loading...</div> : (
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
          <h1 className="text-4xl font-bold text-green-700 mb-6 text-center">
            View Users
          </h1>
          <UserForm
            onSubmit={handleAddOrUpdateUser}
            selectedUser={selectedUser}
            setSelectedUser={setSelectedUser}
          />
          <UserList
            users={users}
            onEdit={setSelectedUser}
            onDelete={handleDeleteUser}
          />
        </div>
        <ToastContainer />
      </div>

      <Footer />
    </>
  ));
}
