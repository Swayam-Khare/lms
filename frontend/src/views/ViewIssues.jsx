import React, { useState, useEffect } from "react";
import Footer from "../components/Footer";
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
  const [selectedRecord, setSelectRecord] = useState(null);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigateTo = useNavigate();
  const role = localStorage.getItem("role");

  async function addRecord(record) {
    try {
      const result = await axios.post(
        "http://localhost:8080/api/issue-record/",
        record,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      toast.success("Issue Record Added", {
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
        setErrorMessage(`Issue Record already present`);
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
        "http://localhost:8080/api/issue-record/",
        selectedRecord,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      toast.success("Record Updated", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      setTimeout(() => {
        navigateTo("/viewIssueRecords");
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
        setErrorMessage(`Record already present`);
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
        await fetchRecords(response.data.id);
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
      }
      setUser(null);
    }
  }

  async function fetchRecords(id) {

    const api = role == "LIBRARIAN" ? "librarian" : "user";

    try {
      const response = await axios.get(
        `http://localhost:8080/api/issue-record/${api}/${id}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );
      const result = response.data;

      setRecords([...result]);
    } catch (error) {
      console.log(error);
    }
  }

  const handleAddOrUpdateRecord = async (record) => {
    if (selectedRecord?.id) {
      selectedRecord.librarian = user;
      await updateRecord();
      
    } else {
      record.librarian = user;
      await addRecord(record);
    }
    fetchRecords(user.id);
  };

  const handleDeleteRecord = async (id) => {

    const isDelete = confirm("Are you sure you want to delete this record?");
    console.log(isDelete);
    
    if (!isDelete) {
      return;
    }

    try {
      const result = await axios.delete(
        "http://localhost:8080/api/issue-record/" + id,
        {
          headers: {
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      console.log(result);

      toast.success("Record Deleted", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      await fetchRecords(user.id);

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
    const promise2 = fetchUser();

    Promise.all([promise2]).then((values) => {
      setLoading(false);
    });
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
          {role == "LIBRARIAN" ? (
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
          ) : (
            <></>
          )}
          <IssueRecordList
            issueRecords={records}
            onUpdate={(record) => {
              console.log("record: ", record);
              setSelectRecord(record)
              setOpen(true);
              setErrorMessage("");
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
