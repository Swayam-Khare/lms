import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Lottie from "react-lottie";
import axios from "axios";
import animationData2 from "../assets/signin.json";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Signin({ getRole }) {
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState(getRole);

  localStorage.setItem("role", getRole);

  const currentRole = getRole.toLowerCase();
  const altRole = currentRole == "librarian" ? "User" : "Librarian";

  const altSigninUrl = altRole !== "User" ? "/lib/signin" : "/user/signin";

  const navigateTo = useNavigate();

  const defaultOptions1 = {
    loop: true,
    autoplay: true,
    animationData: animationData2,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };

  const redirectSignin = () => {
    window.location.href = altSigninUrl;
  }

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  const validateInputs = () => {
    const result = {
      status: true,
      message: "Success",
    };

    const isValidEmail = /^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$/i.test(
      email
    );
    const isValidPassword =
      /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{6,}$/.test(
        password
      );
    const isValidRole = role === "SELECT ROLE..." ? false : true;

    if (!isValidEmail) {
      result.status = false;
      result.message = "Please enter a valid email";
    } else if (!isValidPassword) {
      result.status = false;
      result.message =
        "Password must be 6 characters long and combination of uppercase letters, lowercase letters, numbers, special characters";
    } else if (!isValidRole) {
      result.status = false;
      result.message = "Please select a role";
    }

    return result;
  };

  const handleSignin = async (e) => {
    e.preventDefault();
    setLoading(true);
    const data = {
      email,
      password,
      role,
    };

    const validateResult = validateInputs();

    if (!validateResult.status) {
      toast.error(`${validateResult.message}`, {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        type: "error",
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      setLoading(false);
      return;
    }

    try {
      console.log(data);

      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        data,
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );
      const result = response.data;
      localStorage.setItem("user", JSON.stringify(data));

      toast.success("Login successful", {
        position: "bottom-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      // redirect to home page
      setTimeout(() => {
        if (role === "LIBRARIAN") {
          navigateTo("/viewUsers", { replace: true });
        } else {
          navigateTo("/viewBooks", { replace: true });
        }
      }, 1000);

      setLoading(false);
    } catch (error) {
      if (error.response) {
        toast.error(`Login failed: ${error.response.data.message}`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
      } else {
        toast.error(`Login failed: Network Error`, {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: false,
          progress: undefined,
        });
      }

      setLoading(false);
    }
  };

  return (
    <div className="h-screen flex item-center justify-center">
      <div className="hidden lg:block flex-grow bg-red-50">
        <div className="mt-10">
          <Lottie options={defaultOptions1} width={750} height={600} />
        </div>
      </div>

      <div className="hidden lg:block border-2"></div>

      <div className="py-10 flex items-center px-16">
        <div>
          <div className="text-3xl text-gray-600">Welcome back!</div>

          <div className="mt-6 text-2xl text-left text-primary mb-2 font-bold">
            {`Login to your ${currentRole} account`}
          </div>
          <hr />
          <div className="mt-4">
            <form onSubmit={handleSignin} className="w-80 lg:w-96">
              <input
                type="email"
                autocomplete="off"
                placeholder="Email"
                required
                className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                value={email}
                onChange={(e) => setEmail(e.target.value.toLowerCase())}
              />

              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  required
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                />

                <button
                  className="absolute top-1/3 mr-1 right-2 transform translate-y-2 focus:outline-none"
                  type="button"
                  onClick={handleTogglePassword}
                >
                  {showPassword ? (
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                      className="w-5 h-5"
                    >
                      <path d="M10 12.5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z" />
                      <path
                        fillRule="evenodd"
                        d="M.664 10.59a1.651 1.651 0 0 1 0-1.186A10.004 10.004 0 0 1 10 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0 1 10 17c-4.257 0-7.893-2.66-9.336-6.41ZM14 10a4 4 0 1 1-8 0 4 4 0 0 1 8 0Z"
                        clipRule="evenodd"
                      />
                    </svg>
                  ) : (
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                      className="w-5 h-5"
                    >
                      <path
                        fillRule="evenodd"
                        d="M3.28 2.22a.75.75 0 0 0-1.06 1.06l14.5 14.5a.75.75 0 1 0 1.06-1.06l-1.745-1.745a10.029 10.029 0 0 0 3.3-4.38 1.651 1.651 0 0 0 0-1.185A10.004 10.004 0 0 0 9.999 3a9.956 9.956 0 0 0-4.744 1.194L3.28 2.22ZM7.752 6.69l1.092 1.092a2.5 2.5 0 0 1 3.374 3.373l1.091 1.092a4 4 0 0 0-5.557-5.557Z"
                        clipRule="evenodd"
                      />
                      <path d="m10.748 13.93 2.523 2.523a9.987 9.987 0 0 1-3.27.547c-4.258 0-7.894-2.66-9.337-6.41a1.651 1.651 0 0 1 0-1.186A10.007 10.007 0 0 1 2.839 6.02L6.07 9.252a4 4 0 0 0 4.678 4.678Z" />
                    </svg>
                  )}
                </button>
              </div>

              <button
                type="submit"
                disabled={loading}
                className="w-full bg-primary hover:bg-green-900 text-white mt-4 p-2 rounded-md"
              >
                {loading ? "Signing in..." : "Sign In"}
              </button>
            </form>

            <div className="mt-4 flex justify-between">
            {currentRole === "librarian" && (
              <div>
              Don't have an account?{" "}
              <a href="/signup" className="text-primary hover:underline">
                Sign up
              </a>
            </div>
            )}
            </div>
          </div>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
}
