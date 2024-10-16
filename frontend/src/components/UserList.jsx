import React from 'react';

export default function UserList({ users, onEdit, onDelete }) {
  return (
    <div className="mt-8 overflow-x-auto">
      <table className="min-w-full bg-white shadow-lg rounded-lg">
        <thead className="bg-green-700 text-white">
          <tr>
            <th className="py-3 px-6">First Name</th>
            <th className="py-3 px-6">Last Name</th>
            <th className="py-3 px-6">Email</th>
            <th className="py-3 px-6">Join Date</th>
            <th className="py-3 px-6">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, index) => (
            <tr key={index} className="border-b">
              <td className="py-3 px-6">{user.firstName}</td>
              <td className="py-3 px-6">{user.lastName}</td>
              <td className="py-3 px-6">{user.email}</td>
              <td className="py-3 px-6">{user.joinDate}</td>
              <td className="py-3 px-6">
                <button onClick={() => onEdit(user)} className="text-blue-600 hover:text-blue-800 mr-4">Edit</button>
                <button onClick={() => onDelete(user.id)} className="text-red-600 hover:text-red-800">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
