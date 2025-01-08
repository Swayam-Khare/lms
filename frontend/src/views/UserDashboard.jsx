import React, { useState, useEffect } from "react";
// import NavbarUserDash from "../components/NavBarUserDash";
import Navbar from "../components/Navbar";
import NavbarAlt from "../components/NavbarAlt";
import Footer from "../components/Footer"; // Adjust the path as necessary
import IssueRecordList from "../components/IssueRecordList"; // Import IssueRecordList
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getToken } from "../utils/cookieUtils";

export default function UserDashboard() {
  const [userData, setUserData] = useState({
    booksIssued: 0,
    totalFine: 0,
    nextReturnDate: null,
  });
  const [user, setUser] = useState(null);
  const [issueRecords, setIssueRecords] = useState([]); // State for issue records
  const [loading, setLoading] = useState(true);

  const navigateTo = useNavigate();

  async function fetchUser() {
    try {
      const response = await axios.get("http://localhost:8080/api/auth/me", {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      if (response.data) {
        setUser(response.data);
      } else {
        setUser(null);
      }
    } catch (error) {
      console.log(error);
      setUser(null);
    }
  }

  useEffect(() => {

    // Simulate fetching user data from an API or backend service
    const fetchUserData = async () => {
      // This is dummy data. Replace this with an actual API call.
      const dummyData = {
        booksIssued: 5,
        totalFine: 50,
        nextReturnDate: "2024-10-22",
      };
      setUserData(dummyData);
    };

    const fetchIssueRecords = async () => {
      // Dummy issue records; replace this with an actual API call.
      const dummyIssueRecords = [
        {
          id: 1,
          issueDate: "2024-10-01",
          dueDate: "2024-10-22",
          userName: "Alice Johnson",
          librarianName: "Mr. Smith",
          books: [
            { bookName: "Book A", bookId: 101 },
            { bookName: "Book B", bookId: 102 },
          ],
        },
        {
          id: 2,
          issueDate: "2024-10-05",
          dueDate: "2024-10-19",
          userName: "Bob Brown",
          librarianName: "Ms. Johnson",
          books: [{ bookName: "Book C", bookId: 103 }],
        },
        // Add more issue records as needed...
      ];
      setIssueRecords(dummyIssueRecords);
    };

    fetchUser();
    fetchUserData();
    fetchIssueRecords();
    setLoading(false);
  }, []);

  const handleUpdate = (record) => {
    console.log("Update record:", record);
    // Implement update logic here
  };

  const handleDelete = (recordId) => {
    console.log("Delete record with id:", recordId);
    setIssueRecords(issueRecords.filter((record) => record.id !== recordId)); // Remove record from state
  };

  return loading ? (
    <div>Loading...</div>
  ) : (
    <>
      {user ? <NavbarAlt user={user} /> : <Navbar />}
      <div className="bg-gray-100 min-h-screen p-6 relative">
        {/* Background Art */}
        <div
          className="absolute inset-0 bg-fixed bg-no-repeat bg-cover opacity-10"
          style={{
            backgroundImage: 'url("/path-to-your-background-image.jpg")',
          }}
        ></div>
        {/* <section id="hero">{<NavbarUserDash />}</section> */}
        <div className="relative max-w-7xl mx-auto bg-white bg-opacity-80 backdrop-blur-md shadow-lg rounded-lg p-4 mt-8">
          <h1 className="text-4xl font-bold text-green-700 mb-6 text-center">
            User Dashboard
          </h1>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {/* Card 1: Number of Books Issued */}
            <div className="bg-white shadow-lg rounded-lg p-6 text-center">
              <h2 className="text-2xl font-semibold text-gray-700">
                Books Issued
              </h2>
              <p className="text-6xl font-bold text-green-700 mt-4">
                {userData.booksIssued}
              </p>
              <p className="text-gray-500 mt-2">
                Total number of books you have issued
              </p>
            </div>

            {/* Card 2: Total Fine Amount */}
            <div className="bg-white shadow-lg rounded-lg p-6 text-center">
              <h2 className="text-2xl font-semibold text-gray-700">
                Total Fine
              </h2>
              <p className="text-6xl font-bold text-red-600 mt-4">
                ₹{userData.totalFine}
              </p>
              <p className="text-gray-500 mt-2">
                Total accumulated fine for overdue books
              </p>
            </div>

            {/* Card 3: Next Return Date */}
            <div className="bg-white shadow-lg rounded-lg p-6 text-center">
              <h2 className="text-2xl font-semibold text-gray-700">
                Next Return Date
              </h2>
              <p className="text-4xl font-bold text-blue-600 mt-6">
                {userData.nextReturnDate
                  ? new Date(userData.nextReturnDate).toLocaleDateString()
                  : "N/A"}
              </p>
              <p className="text-gray-500 mt-5">
                The return date for your next due book
              </p>
            </div>
          </div>

          {/* Render IssueRecordList Component */}
          <div className="bg-white  rounded-lg p-6 text-center">
            <h2 className="text-2xl font-semibold text-gray-700 mb-6">
              Current Issued Books
            </h2>
            {/* <IssueRecordList
              issueRecords={issueRecords}
              onDelete={handleDelete}
              onUpdate={handleUpdate}
            /> */}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
