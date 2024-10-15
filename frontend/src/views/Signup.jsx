import React, { useEffect, useState } from 'react';
import Lottie from 'react-lottie';
import animationData2 from '../assets/sign.json';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Signup() {
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  useEffect(() => {
    document.title = 'Signup - Edify';
  }, []);

  const defaultOptions1 = {
    loop: true,
    autoplay: true,
    animationData: animationData2,
    rendererSettings: {
      preserveAspectRatio: 'xMidYMid slice'
    }
  };

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  const handleSignup = async (e) => {
    e.preventDefault();
    setLoading(true);
    const data = {
      firstName,
      lastName,
      email,
      password
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/register/librarian",
        data,
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        }
      );
      const result = response.data;

      console.log(result);
      


        toast.success('Signup successful', {
          position: 'bottom-center',
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });

        // redirect to home page
        // setTimeout(() => {
        //   window.location.href = '/signin';
        // }, 3000);
      
      setLoading(false);
    } catch (error) {
      console.log(error.response.data.errors);
      
      toast.error(`Signup failed: ${error.response.data.errors}`, {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
      setLoading(false);
    }
  };

  return (
    <div className="h-screen flex item-center justify-center">
      <div className="py-10 flex items-center px-16">
        <div>
          <div className="text-3xl text-gray-600">
            Join in to Get <br />
            Started
          </div>

          <div className="mt-6 text-2xl text-left text-primary mb-2 font-bold">
            Create Account
          </div>
          <hr />
          <div className="mt-4">
            <form onSubmit={handleSignup} className="w-80 lg:w-96">
              <input
                type="text"
                placeholder="First Name"
                required
                name='firstName'
                className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />

              <input
                type="text"
                placeholder="Last Name"
                required
                name='lastName'
                className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />

              <input
                type="email"
                placeholder="Email"
                required
                name='email'
                className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />

              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  required
                  placeholder="Password"
                  value={password}
                  name='password'
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full mt-4 p-2 border-2 border-gray-300 rounded-md"
                />
                <button
                  className="absolute top-2/4 mt-2 mr-1 right-2 transform -translate-y-1/2 focus:outline-none"
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
                className="w-full bg-primary hover:bg-green-900 text-white mt-4 p-2 rounded-md"
              >
                {loading ? "Creating..." : "Sign Up"}
              </button>
            </form>

            <div className="mt-4 text-center">
              Already have an account?{" "}
              <a href="/signin" className="text-primary hover:underline">
                Login
              </a>
            </div>
          </div>
        </div>
      </div>

      <div className="hidden lg:block border-2"></div>

      <div className="hidden lg:block flex-grow bg-red-50">
        <div className="mt-10">
          <Lottie options={defaultOptions1} width={970} />
        </div>
      </div>

      <ToastContainer />
    </div>
  );
}
