import React, { useState, useEffect } from "react";
import Footer from "../components/Footer";
import axios from "axios";
import { getToken } from "../utils/cookieUtils";
import BookList from "../components/BookList";
import Navbar from "../components/Navbar";
import NavbarAlt from "../components/NavbarAlt";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import BookDialog from "../components/BookDialog";

export default function ViewBooks() {
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState(null);
  const [book, setBook] = useState(null);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigateTo = useNavigate();

  async function addUser(book) {
    try {
      const result = await axios.post("http://localhost:8080/api/book/", book, {
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

      setSelectedBook(null);

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
        selectedBook,
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
      setSelectedBook(null);
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
        setBook(response.data);
      } else {
        setBook(null);
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
      setBook(null);
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

  const handleAddOrUpdateUser = async (user) => {
    if (selectedBook.id) {
      // Update existing user
      console.log(selectedBook);
      await updateUser();
    } else {
      // Add new user with a unique ID
      console.log("Add user ", user);

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

  useEffect(() => {
    secure();
    fetchUser();
    fetchUsers();
    setLoading(false);
  }, []);

  return loading ? (
    <div>Loading...</div>
  ) : (
    <>
      {book ? <NavbarAlt user={book} /> : <Navbar />}
      <div className="bg-gray-100 min-h-screen p-6 relative">
        <div
          className="absolute inset-0 bg-fixed bg-no-repeat bg-cover opacity-10"
          style={{
            backgroundImage: 'url("/path-to-your-background-image.jpg")',
          }}
        ></div>
        <div className="relative max-w-7xl mx-auto bg-white bg-opacity-80 backdrop-blur-md shadow-lg rounded-lg p-8">
          <h1 className="text-4xl font-bold text-primary mb-6 text-center">
            View Books
          </h1>
          <div className="flex justify-end">
            <button
              onClick={() => {
                setOpen(true);
                setSelectedBook(null);
                setErrorMessage("");
              }}
              className="bg-primary box-border text-white px-4 py-2 rounded-md border-primary border-2
              hover:border-black transition"
            >
              Add Book
            </button>
          </div>
          <BookList
            books={books}
            onEdit={(book) => {
              setOpen(true);
              setErrorMessage("");
              setSelectedBook(book);
            }}
            onDelete={handleDeleteUser}
          />
        </div>
        <ToastContainer />
        <BookDialog
          open={open}
          setOpen={setOpen}
          onSubmit={handleAddOrUpdateUser}
          selectedBook={selectedBook}
          setSelectedBook={setSelectedBook}
          errorMessage={errorMessage}
        />
      </div>
      <Footer />
    </>
  );
}
