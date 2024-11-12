import React, { useState, useEffect } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary
import axios from "axios";
import { getToken } from "../utils/cookieUtils";
import Navbar from "../components/Navbar";
import NavbarAlt from "../components/NavbarAlt";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import IssueRecordList from "../components/IssueRecordList";
import IssueDialog from "../components/IssueDialog";

export default function ViewIssues() {
  const [records, setRecords] = useState([]);
  const [selectedRecord, setSelectRecord] = useState(null); // For editing a user
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigateTo = useNavigate();

  async function addRecord(user) {
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

      setSelectRecord(null);

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

  async function updateRecord() {
    try {
      const result = await axios.put(
        "http://localhost:8080/api/user/",
        selectedRecord,
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

      setErrorMessage("");

      setOpen(false);
      setSelectRecord(null);
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
          navigateTo("/signin");
        }, 2000);
      }
      setUser(null);
    }
  }

  async function fetchRecords() {
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

      setRecords([...result]);
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

  const handleAddOrUpdateRecord = async (user) => {
    if (selectedRecord.id) {
      // Update existing user
      console.log(selectedRecord);
      await updateRecord();
    } else {
      // Add new user with a unique ID
      console.log("Add user ", user);

      await addRecord(user);
    }
    fetchRecords();
  };

  const handleDeleteRecord = async (id) => {
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

      await fetchRecords();
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

  useEffect(() => {
    secure();
    fetchUser();
    fetchRecords();
    setLoading(false);
  }, []);

  return loading ? (
    <div>Loading...</div>
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
            View Issue Records
          </h1>
          <div className="flex justify-end">
            <button
              onClick={() => {
                setOpen(true);
                setSelectRecord(null);
                setErrorMessage("");
              }}
              className="bg-primary box-border text-white px-4 py-2 rounded-md border-primary border-2
              hover:border-black transition"
            >
              Add Record
            </button>
          </div>
          <IssueRecordList
            issueRecords={records}
            onUpdate={(user) => {
              setOpen(true);
              setErrorMessage("");
              setSelectRecord(user);
            }}
            onDelete={handleDeleteRecord}
          />
        </div>
        <ToastContainer />
        <IssueDialog
          open={open}
          setOpen={setOpen}
          onSubmit={handleAddOrUpdateRecord}
          selectedRecord={selectedRecord}
          setSelectedRecord={setSelectRecord}
          errorMessage={errorMessage}
        />
      </div>
      <Footer />
    </>
  );
}
