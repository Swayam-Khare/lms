// src/views/ViewUsers.js

import { useEffect, useState } from "react";
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import Footer from "../components/Footer"; // Adjust the path as necessary
import UserList from "../components/UserList"; // Import UserList
import UserForm from "../components/UserForm"; // Import UserForm

const ViewUsers = () => {
  const [loading, setLoading] = useState(true);
  const [users, setUsers] = useState([]);
  const [showModal, setShowModal] = useState(false); // For the add user modal
  const [newUser, setNewUser] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    joinDate: '',
    dueDate: '',
    address: '',
    phoneNumbers: [''], // Start with one phone number field
  });

  useEffect(() => {
    const fetchUsers = () => {
      // Dummy user data
      const dummyUsers = [
        {
          id: 1,
          firstName: 'John',
          lastName: 'Doe',
          email: 'john.doe@example.com',
          password: 'password123',
          joinDate: '2022-01-15',
          dueDate: '2023-01-15',
          address: '123 Elm Street, Springfield',
          phoneNumbers: ['123-456-7890', '987-654-3210'],
        },
        {
          id: 2,
          firstName: 'Jane',
          lastName: 'Smith',
          email: 'jane.smith@example.com',
          password: 'password123',
          joinDate: '2021-06-20',
          dueDate: '2023-06-20',
          address: '456 Oak Avenue, Springfield',
          phoneNumbers: ['234-567-8901'],
        },
        {
          id: 3,
          firstName: 'Alice',
          lastName: 'Johnson',
          email: 'alice.johnson@example.com',
          password: 'password123',
          joinDate: '2020-11-11',
          dueDate: '2022-11-11',
          address: '789 Maple Road, Springfield',
          phoneNumbers: ['345-678-9012', '321-654-9870'],
        },
      ];

      setUsers(dummyUsers);
      setLoading(false);
    };

    fetchUsers();
  }, []);

  const handleDelete = (id) => {
    setUsers(users.filter(user => user.id !== id));
  };

  const handleUpdate = (user) => {
    setNewUser(user);
    setShowModal(true);
  };

  const handleAddUser = () => {
    const updatedUsers = [...users, { ...newUser, id: users.length + 1 }];
    setUsers(updatedUsers);
    setShowModal(false);
    resetForm();
  };

  const resetForm = () => {
    setNewUser({
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      joinDate: '',
      dueDate: '',
      address: '',
      phoneNumbers: [''],
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUser((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const addPhoneNumber = () => {
    setNewUser((prev) => ({
      ...prev,
      phoneNumbers: [...prev.phoneNumbers, ''],
    }));
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
};

export default ViewUsers;
