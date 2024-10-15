// components/UserDetailsForm.js

import React from "react";

const UserDetailsForm = ({ user, onInputChange }) => {
  return (
    <form className="space-y-6">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label htmlFor="firstName" className="block font-medium text-gray-700">
            First Name
          </label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={user.firstName}
            onChange={onInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            required
          />
        </div>
        <div>
          <label htmlFor="lastName" className="block font-medium text-gray-700">
            Last Name
          </label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={user.lastName}
            onChange={onInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            required
          />
        </div>
        <div>
          <label htmlFor="email" className="block font-medium text-gray-700">
            Email
          </label>
          <input
            type="email"
            id="email"
            name="email"
            value={user.email}
            onChange={onInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            required
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Join Date</label>
          <input
            type="text"
            value={user.joinDate}
            readOnly
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md bg-gray-200 cursor-not-allowed"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Due Date</label>
          <input
            type="text"
            value={user.dueDate}
            readOnly
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md bg-gray-200 cursor-not-allowed"
          />
        </div>
        <div>
          <label className="block font-medium text-gray-700">Address</label>
          <input
            type="text"
            id="lane1"
            name="lane1"
            value={user.address.lane1}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "lane1", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="Lane 1"
            required
          />
          <input
            type="text"
            id="lane2"
            name="lane2"
            value={user.address.lane2}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "lane2", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="Lane 2"
          />
          <input
            type="text"
            id="city"
            name="city"
            value={user.address.city}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "city", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="City"
            required
          />
          <input
            type="text"
            id="state"
            name="state"
            value={user.address.state}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "state", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="State"
            required
          />
          <input
            type="text"
            id="country"
            name="country"
            value={user.address.country}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "country", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="Country"
            required
          />
          <input
            type="text"
            id="pincode"
            name="pincode"
            value={user.address.pincode}
            onChange={(e) => onInputChange({ ...e, target: { ...e.target, name: "pincode", value: e.target.value } })}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md focus:ring focus:ring-green-500"
            placeholder="Pincode"
            required
          />
        </div>
      </div>
    </form>
  );
};

export default UserDetailsForm;
