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
      pincode: ""
    },
    phoneNumbers: [""]
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
      phoneNumbers: [""]
    });
    setSelectedUser(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(user);
    resetForm();
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, address: { ...user.address, [name]: value } });
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

  return (
    <form onSubmit={handleSubmit} className="bg-white shadow-lg rounded-lg p-6 mb-6">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
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
            required
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
          <label className="block font-medium text-gray-700">Address Lane 1</label>
          <input 
            type="text" 
            name="lane1" 
            value={user.address.lane1} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Address Lane 2</label>
          <input 
            type="text" 
            name="lane2" 
            value={user.address.lane2} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">City</label>
          <input 
            type="text" 
            name="city" 
            value={user.address.city} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">State</label>
          <input 
            type="text" 
            name="state" 
            value={user.address.state} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Country</label>
          <input 
            type="text" 
            name="country" 
            value={user.address.country} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Pincode</label>
          <input 
            type="number" 
            name="pincode" 
            value={user.address.pincode} 
            onChange={handleAddressChange} 
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            required
          />
        </div>
      </div>

      <div className="mt-4">
        <label className="block font-medium text-gray-700">Phone Numbers</label>
        {user.phoneNumbers.map((phone, index) => (
          <div key={index} className="flex items-center mt-1">
            <input 
              type="text" 
              value={phone} 
              onChange={(e) => handlePhoneChange(index, e.target.value)} 
              className="block w-full p-2 border border-gray-300 rounded-md mr-2"
            />
            {index > 0 && (
              <button 
                type="button" 
                onClick={() => removePhoneField(index)} 
                className="text-red-500 hover:text-red-700"
              >
                Remove
              </button>
            )}
          </div>
        ))}
        <button 
          type="button" 
          onClick={addPhoneField} 
          className="mt-2 text-green-600 hover:text-green-800"
        >
          + Add Phone
        </button>
      </div>

      <div className="mt-6">
        <button 
          type="submit" 
          className="bg-green-700 text-white px-6 py-2 rounded-md hover:bg-green-600 transition"
        >
          {selectedUser ? "Update User" : "Add User"}
        </button>
      </div>
    </form>
  );
}
