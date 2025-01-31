import React, { useState } from "react";
import { Input } from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import debouncify from "../utils/debouncify.js";

export default function UserList({ users, onEdit, onDelete }) {
  return (
    <div className="mt-4 overflow-x-auto">
      <table className="min-w-full bg-white shadow-lg rounded-lg">
        <thead className="bg-primary text-white">
          <tr>
            <th className="py-3 px-4 text-left text-sm font-medium">
              First Name
            </th>
            <th className="py-3 px-4 text-left text-sm font-medium">
              Last Name
            </th>
            <th className="py-3 px-4 text-left text-sm font-medium">Email</th>
            <th className="py-3 px-4 text-left text-sm font-medium">
              Join Date
            </th>
            <th className="py-3 px-4 text-left text-sm font-medium">
              Due Date
            </th>
            <th className="py-3 px-4 text-left text-sm font-medium">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id} className="border-b hover:bg-gray-100 transition">
              <td className="py-3 px-4">{user.firstName}</td>
              <td className="py-3 px-4">{user.lastName}</td>
              <td className="py-3 px-4">{user.email}</td>
              <td className="py-3 px-4">{user.joinDate}</td>
              <td className="py-3 px-4">{user.dueDate}</td>
              <td className="py-3 px-4 flex space-x-2">
                <button
                  onClick={() => onEdit(user)}
                  className="bg-green-600 text-white px-4 py-2 rounded-md hover:bg-green-500 transition"
                >
                  Edit
                </button>
                <button
                  onClick={() => onDelete(user.id)}
                  className="bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-500 transition"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
