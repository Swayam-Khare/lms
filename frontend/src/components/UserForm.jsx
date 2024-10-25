import React, { useState, useEffect } from "react";

export default function UserForm({ onSubmit, selectedUser, setSelectedUser }) {
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    joinDate: "",
    dueDate: "",
    address: {
      lane1: "",
      lane2: "",
      city: "",
      state: "",
      country: "",
      pincode: "",
      phoneNumber: ""
    },
  });

  useEffect(() => {
    if (selectedUser) {
      setUser(selectedUser);
    } else {
      resetForm();
    }
  }, [selectedUser]);

  const resetForm = () => {
    setUser({
      firstName: "",
      lastName: "",
      email: "",
      joinDate: "",
      dueDate: "",
      address: {
        lane1: "",
        lane2: "",
        city: "",
        state: "",
        country: "",
        pincode: ""
      },
      phoneNumber: []
    });
    setSelectedUser(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(user);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSelectedUser({ ...user, [name]: value });
  };

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setSelectedUser({ ...user, address: { ...user.address, [name]: value } });
  };

  // const handlePhoneChange = (index, value) => {
  //   const updatedPhones = [...user.phoneNumber];
  //   updatedPhones[index] = {number: value};
  //   setSelectedUser({ ...user, phoneNumber: updatedPhones });
  // };

  // const addPhoneField = () => {
  //   setSelectedUser({ ...user, phoneNumber: [...user.phoneNumber, {number: ""}] });
  // };

  // const removePhoneField = (index) => {
  //   const updatedPhones = user.phoneNumber.filter((_, i) => i !== index);
  //   setSelectedUser({ ...user, phoneNumber: updatedPhones });
  // };

  return (
    <form onSubmit={handleSubmit} className="bg-white rounded-lg">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <div>
          <label className="block font-medium text-gray-700">First Name</label>
          <input
            type="text"
            name="firstName"
            value={user.firstName}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Last Name</label>
          <input
            type="text"
            name="lastName"
            value={user.lastName}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Email</label>
          <input
            type="email"
            name="email"
            value={user.email}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Join Date</label>
          <input
            type="date"
            name="joinDate"
            value={user.joinDate}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Due Date</label>
          <input
            type="date"
            name="dueDate"
            value={user.dueDate}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">
            Address Lane 1
          </label>
          <input
            type="text"
            name="lane1"
            value={user.address?.lane1}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">
            Address Lane 2
          </label>
          <input
            type="text"
            name="lane2"
            value={user.address?.lane2}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">City</label>
          <input
            type="text"
            name="city"
            value={user.address?.city}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">State</label>
          <input
            type="text"
            name="state"
            value={user.address?.state}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Country</label>
          <input
            type="text"
            name="country"
            value={user.address?.country}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Pincode</label>
          <input
            type="number"
            name="pincode"
            value={user.address?.pincode}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Phone Number</label>
          <input
            type="text"
            name="phoneNumber"
            value={user.address?.phoneNumber}
            onChange={handleAddressChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
      </div>

      <div className="mt-6">
        <button
          type="submit"
          className="bg-green-700 text-white px-6 py-2 mr-2 rounded-md hover:bg-green-600 transition"
        >
          {selectedUser?.id ? "Update User" : "Add User"}
        </button>

        <button
          type="button"
          onClick={resetForm}
          className="bg-gray-500 text-white px-6 py-2 rounded-md hover:bg-gray-400 transition"
        >
          Reset
        </button>
      </div>
    </form>
  );
}
