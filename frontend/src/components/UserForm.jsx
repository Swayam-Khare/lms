// src/components/UserForm.js

import React from 'react';

const UserForm = ({ showModal, onClose, newUser, onChange, onAddUser, addPhoneNumber }) => {
  return (
    showModal && (
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
        <div className="bg-white p-6 rounded shadow-lg">
          <h2 className="text-xl font-bold mb-4">Add New User</h2>
          <input
            type="text"
            name="firstName"
            placeholder="First Name"
            value={newUser.firstName}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={newUser.lastName}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={newUser.email}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={newUser.password}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="date"
            name="joinDate"
            placeholder="Join Date"
            value={newUser.joinDate}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="date"
            name="dueDate"
            placeholder="Due Date"
            value={newUser.dueDate}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={newUser.address}
            onChange={onChange}
            className="border rounded w-full mb-2 p-2"
          />
          <h3 className="font-bold">Phone Numbers:</h3>
          {newUser.phoneNumbers.map((phone, index) => (
            <input
              key={index}
              type="text"
              placeholder={`Phone Number ${index + 1}`}
              value={phone}
              onChange={(e) => onChange({ target: { name: `phoneNumbers[${index}]`, value: e.target.value } })}
              className="border rounded w-full mb-2 p-2"
            />
          ))}
          <button 
            onClick={addPhoneNumber} 
            className="bg-blue-500 text-white px-4 py-1 rounded mb-2"
          >
            Add Another Phone Number
          </button>
          <div className="flex justify-between mt-4">
            <button 
              onClick={onAddUser} 
              className="bg-[#00684a] text-white px-4 py-2 rounded"
            >
              Submit
            </button>
            <button 
              onClick={onClose} 
              className="bg-red-500 text-white px-4 py-2 rounded"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    )
  );
};

export default UserForm;
