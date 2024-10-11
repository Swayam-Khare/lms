// src/components/UserList.js

import React from 'react';

const UserList = ({ users, onDelete, onUpdate }) => {
  return (
    <table className="min-w-full table-auto border-collapse">
      <thead>
        <tr>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">First Name</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Last Name</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Email</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Join Date</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Due Date</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Address</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Phone Numbers</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Actions</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user) => (
          <tr key={user.id}>
            <td className="border-b border-gray-200 py-2 px-4">{user.firstName}</td>
            <td className="border-b border-gray-200 py-2 px-4">{user.lastName}</td>
            <td className="border-b border-gray-200 py-2 px-4">{user.email}</td>
            <td className="border-b border-gray-200 py-2 px-4">{user.joinDate}</td>
            <td className="border-b border-gray-200 py-2 px-4">{user.dueDate}</td>
            <td className="border-b border-gray-200 py-2 px-4">{user.address}</td>
            <td className="border-b border-gray-200 py-2 px-4">
              {user.phoneNumbers.join(', ')}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 flex space-x-2">
              <button
                onClick={() => onUpdate(user)}
                className="bg-[#00684a] text-white px-4 py-1 rounded"
              >
                Update
              </button>
              <button
                onClick={() => onDelete(user.id)}
                className="bg-red-500 text-white px-4 py-1 rounded"
              >
                Delete
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default UserList;
