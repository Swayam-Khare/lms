import React, { useState, useEffect } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary
import UserDetailsForm from "../components/UserDetailsForm";
import PhoneNumberSection from "../components/PhoneNumberSection";

export default function MyAccount() {
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
    },
    phoneNumbers: [""],
    joinDate: "",
    dueDate: "",
  });

  useEffect(() => {
    // Dummy user data; replace with real user data from an API
    const dummyUser = {
      firstName: "John",
      lastName: "Doe",
      email: "johndoe@example.com",
      address: {
        lane1: "1234 Elm Street",
        lane2: "Apt 12B",
        city: "Some City",
        state: "Some State",
        country: "Some Country",
        pincode: "123456",
      },
      phoneNumbers: ["123-456-7890"],
      joinDate: "2023-01-15",
      dueDate: "2023-12-15",
    };

    setUser(dummyUser);
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handlePhoneChange = (index, value) => {
    const updatedPhones = [...user.phoneNumbers];
    updatedPhones[index] = value;
    setUser({ ...user, phoneNumbers: updatedPhones });
  };

  const addPhoneField = () => {
    setUser({ ...user, phoneNumbers: [...user.phoneNumbers, ""] });
  };

  const removePhoneField = (index) => {
    const updatedPhones = user.phoneNumbers.filter((_, i) => i !== index);
    setUser({ ...user, phoneNumbers: updatedPhones });
  };

  const handleSave = () => {
    // Handle save logic (e.g., API call to update user details)
    console.log("User details saved:", user);
  };

  return (
    <div className="bg-gray-100 min-h-screen p-6 relative">
      <div className="relative max-w-7xl mx-auto bg-white bg-opacity-90 backdrop-blur-md shadow-lg rounded-lg p-8">
        <h1 className="text-4xl font-bold text-green-700 mb-6 text-center">My Account</h1>

        <div className="space-y-6">
          <UserDetailsForm user={user} onInputChange={handleInputChange} />

          <PhoneNumberSection
            phoneNumbers={user.phoneNumbers}
            onPhoneChange={handlePhoneChange}
            addPhoneField={addPhoneField}
            removePhoneField={removePhoneField}
          />

          <div className="mt-6 flex justify-center">
            <button
              type="button"
              onClick={handleSave}
              className="bg-green-700 text-white px-6 py-2 rounded-md hover:bg-green-600 transition"
            >
              Save Changes
            </button>
          </div>

          <div className="mt-4 text-center">
            <span
              onClick={() => alert("Change Password Clicked")} // Replace with the actual change password function
              className="text-blue-600 cursor-pointer hover:underline font-semibold"
            >
              Change Password
            </span>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
