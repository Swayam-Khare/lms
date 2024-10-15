import React, { useState } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary

const ChangePassword = () => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleChangePassword = () => {
    if (newPassword.length < 6) {
      setErrorMessage("New password must be at least 6 characters long.");
      return;
    }
    if (newPassword !== confirmPassword) {
      setErrorMessage("New password and confirmation do not match.");
      return;
    }

    // Here, you would typically send the new password to your API for updating.
    // For now, we will just simulate a successful password change.
    console.log("Changing password...");
    setSuccessMessage("Password changed successfully!");
    setCurrentPassword("");
    setNewPassword("");
    setConfirmPassword("");
    setErrorMessage("");
  };

  return (
    <div className="bg-gray-100 min-h-screen p-6 relative">
      <div className="relative max-w-md mx-auto bg-white bg-opacity-90 backdrop-blur-md shadow-lg rounded-lg p-8">
        <h1 className="text-3xl font-bold text-green-700 mb-6 text-center">Change Password</h1>

        <div className="space-y-4">
          {errorMessage && (
            <div className="bg-red-100 text-red-700 p-2 rounded-md">
              {errorMessage}
            </div>
          )}
          {successMessage && (
            <div className="bg-green-100 text-green-700 p-2 rounded-md">
              {successMessage}
            </div>
          )}

          <div>
            <label htmlFor="currentPassword" className="block font-medium text-gray-700">
              Current Password
            </label>
            <input
              type="password"
              id="currentPassword"
              value={currentPassword}
              onChange={(e) => setCurrentPassword(e.target.value)}
              className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
              required
            />
          </div>

          <div>
            <label htmlFor="newPassword" className="block font-medium text-gray-700">
              New Password
            </label>
            <input
              type="password"
              id="newPassword"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
              required
            />
          </div>

          <div>
            <label htmlFor="confirmPassword" className="block font-medium text-gray-700">
              Confirm New Password
            </label>
            <input
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
              required
            />
          </div>

          <div className="mt-6 flex justify-center">
            <button
              type="button"
              onClick={handleChangePassword}
              className="bg-green-700 text-white px-6 py-2 rounded-md hover:bg-green-600 transition"
            >
              Change Password
            </button>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ChangePassword;
