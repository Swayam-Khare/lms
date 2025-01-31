import React, { useState, useEffect } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary
import UserDetailsForm from "../components/UserDetailsForm";
import axios from "axios";
import { getToken } from "../utils/cookieUtils";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import NavbarAlt from "../components/NavbarAlt";

export default function MyAccount() {
  const navigateTo = useNavigate();
  const [loading, setLoading] = useState(true);

  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    address: {
      lane1: "",
      lane2: "",
      city: "",
      state: "",
      country: "",
      pincode: "",
      phoneNumber: "",
    },
  });

  async function fetchUser() {
    try {
      const response = await axios.get("http://localhost:8080/api/auth/me", {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      console.log(response);

      if (response.data) {
        setUser(response.data);
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
          localStorage.removeItem("user");
          localStorage.removeItem("role");
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
    }
  }

  useEffect(() => {
    fetchUser().then((val) => {
      setLoading(false);
    });
  }, []);

  const handleInputChange = (e) => {
    let { name, value } = e.target;

    if (name.startsWith("address")) {
      name = name.split(".")[1];
      setUser({ ...user, address: { ...user.address, [name]: value } });
    } else {
      setUser({ ...user, [name]: value });
    }
  };

  const handleSave = async () => {
    const role = localStorage.getItem("role").toLowerCase();

    try {
      const response = await axios.put(
        `http://localhost:8080/api/${role}/`,
        user,
        {
          headers: {
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      console.log("response", response);

      if (response.status == 200) {
        toast.success("Details Updated", {
          position: "bottom-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      }
    } catch (error) {
      if (error.status === 400 && error.response?.data?.errors?.length > 0) {
        toast.error(`${error.response.data.errors[0]}`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      }
    }
  };

  return loading ? (
    <div>Loading...</div>
  ) : (
    <>
      <NavbarAlt user={user} />
      <div className="bg-gray-100 min-h-screen p-6 relative">
        <div className="relative max-w-7xl mx-auto bg-white bg-opacity-90 backdrop-blur-md shadow-lg rounded-lg p-8">
          <h1 className="text-4xl font-bold text-green-700 mb-6 text-center">
            My Account
          </h1>

          <div className="space-y-6">
            <UserDetailsForm user={user} onInputChange={handleInputChange} />

            <div className="mt-6 flex justify-center">
              <button
                type="button"
                onClick={handleSave}
                className="bg-green-700 text-white px-6 py-2 rounded-md hover:bg-green-600 transition"
              >
                Save Changes
              </button>
            </div>
          </div>
        </div>
        <ToastContainer />
      </div>
      <Footer />
    </>
  );
}
