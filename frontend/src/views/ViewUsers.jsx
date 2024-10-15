import React, { useState, useEffect } from "react";
import Footer from "../components/Footer"; // Adjust path as necessary
import Fab from "../components/Fab"; // Floating Action Button for 'Add User'
import UserForm from "../components/UserForm";
import UserList from "../components/UserList";

export default function ViewUsers() {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null); // For editing a user

  useEffect(() => {
    // Dummy user data
    const dummyUsers = [
      {
        id: 1,
        firstName: "John",
        lastName: "Doe",
        email: "johndoe@example.com",
        joinDate: "2023-01-15",
        dueDate: "2023-12-15",
        address: "1234 Elm Street",
        phoneNumbers: ["123-456-7890", "987-654-3210"]
      },
      {
        id: 2,
        firstName: "Jane",
        lastName: "Smith",
        email: "janesmith@example.com",
        joinDate: "2022-03-22",
        dueDate: "2023-03-22",
        address: "5678 Oak Street",
        phoneNumbers: ["555-555-5555"]
      }
    ];

    setUsers(dummyUsers);
  }, []);

  const handleAddOrUpdateUser = (user) => {
    if (selectedUser) {
      setUsers(users.map(u => (u.id === selectedUser.id ? user : u)));
    } else {
      setUsers([...users, { ...user, id: users.length + 1 }]); // Add new user with a dummy ID
    }
    setSelectedUser(null);
  };

  const handleDeleteUser = (id) => {
    setUsers(users.filter(user => user.id !== id));
  };

  return (
    <>
      <div className="p-6">
        <button
          onClick={() => setShowModal(true)}
          className="bg-[#00684a] text-white px-4 py-2 rounded mb-4"
        >
          Add User
        </button>
        {loading ? (
          <Skeleton height={400} count={5} />
        ) : (
          <>
            <UserList
              users={users}
              onDelete={handleDelete}
              onUpdate={handleUpdate}
            />
            <UserForm
              showModal={showModal}
              onClose={() => setShowModal(false)}
              newUser={newUser}
              onChange={handleChange}
              onAddUser={handleAddUser}
              addPhoneNumber={addPhoneNumber}
            />
          </>
        )}
      </div>
      <Footer />
    </>
  );
}
