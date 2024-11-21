import axios from "axios";
import React, { useEffect, useState } from "react";
import { getToken } from "../utils/cookieUtils";

const IssueRecordForm = ({ onSubmit, selectedRecord, setSelectedRecord }) => {
  const [loading, setLoading] = useState(true);
  const [issueDate, setIssueDate] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [borrower, setBorrower] = useState(null);
  const [users, setUsers] = useState([]);
  const [librarian, setLibrarian] = useState(null);
  const [books, setBooks] = useState([]);
  const [fine, setFine] = useState(0);
  const [issueBooks, setIssueBooks] = useState([]);
  const [isReturned, setIsReturned] = useState(false);

  async function fetchUsers() {
    try {
      const response = await axios.get("http://localhost:8080/api/user/", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });
      const result = response.data;

      setUsers([...result]);
    } catch (error) {
      console.log(error);
    }
  }

  async function fetchBooks() {
    try {
      const response = await axios.get("http://localhost:8080/api/book/", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });
      const result = response.data;

      setBooks([...result]);
    } catch (error) {
      console.log(error);
    }
  }

  function handleUserChange(i) {
    const borrow = users.find((val) => val.id == i);
    setBorrower(borrow);
  }

  function handleBookChange(id, index) {
    const bookFound = books.find((val) => val.id == id);
    issueBooks[index] = bookFound;
  }

  async function handleSubmit(e) {
    e.preventDefault();

    const issueBook = [];

    issueBooks.forEach((book, index) => {
      issueBook.push({
        isbnNumber: book.isbnNumber,
      });
    });

    onSubmit({
      issueDate,
      dueDate,
      isReturned,
      user: borrower,
      issueBook,
    });
  }

  useEffect(() => {
    const promise1 = fetchUsers();
    const promise2 = fetchBooks();

    Promise.all([promise1, promise2]).then((_val) => setLoading(false));
  }, []);

  return loading ? (
    <div>Loading...</div>
  ) : (
    <form className="bg-white p-1 rounded-lg">
      <div className="grid grid-cols-1 md:grid-cols-1 lg:grid-cols-1 gap-4 mb-4 mx-4">
        <div className="">
          <label htmlFor="issueDate" className="text-primary font-bold ml-1">
            Issue Date:
          </label>
          <input
            type="date"
            name="issueDate"
            placeholder="Issue Date"
            value={issueDate}
            onChange={(e) => setIssueDate(e.target.value)}
            className="border rounded w-full mb-2 p-2"
          />

          <label htmlFor="dueDate" className="text-primary font-bold ml-1">
            Due Date:
          </label>
          <input
            type="date"
            name="dueDate"
            placeholder="Due Date"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            className="border rounded w-full mb-2 p-2"
          />

          <div className="flex mb-4 items-center space-x-4">
            <div className="flex-1">
              <label className="block text-gray-700 font-medium">
                Borrower:
              </label>
              <select
                defaultValue={users[0].id}
                onChange={(e) => handleUserChange(e.target.value)}
                required
                className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
                focus:ring focus:ring-primary focus:border-primary"
              >
                {users.map((val, i) => (
                  <option key={i} value={val.id}>
                    {val.firstName} {val.lastName}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <label
            htmlFor="isReturned"
            className="text-primary font-bold ml-1 mr-2 cursor-pointer"
          >
            Book returned
          </label>
          <input
            id="isReturned"
            name="isReturned"
            type="checkbox"
            value={isReturned}
            onChange={(e) => setIsReturned(e.target.value)}
            className="border rounded cursor-pointer"
          />

          <h3 className="font-bold my-2">Books:</h3>

          {issueBooks.map((book, index) => (
            <div key={index} className="mb-2">
              <div className="flex mb-4 items-center space-x-4">
                <div className="flex-1 flex gap-2 items-center">
                  <label className=" text-gray-700 font-semibold text-xl">
                    {index + 1}
                  </label>
                  <select
                    defaultValue={books[0].id}
                    onChange={(e) => handleBookChange(e.target.value, index)}
                    required
                    className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
                focus:ring focus:ring-primary focus:border-primary"
                  >
                    {books.map((val, i) => (
                      <option key={i} value={val.id}>
                        {val.title}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              {issueBooks.length >= 1 && (
                <button
                  type="button"
                  onClick={(e) => {
                    const array = issueBooks.filter(
                      (_val, ind) => ind !== index
                    );
                    setIssueBooks(array);
                  }}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Delete
                </button>
              )}
            </div>
          ))}

          <button
            onClick={() => {
              setIssueBooks([...issueBooks, { id: "" }]);
            }}
            type="button"
            className="bg-blue-500 text-white px-4 py-1 rounded mb-2 mt-2"
          >
            Add Another Book
          </button>

          <div className="flex justify-between mt-4">
            <button
              onClick={handleSubmit}
              className="bg-[#00684a] text-white px-4 py-2 rounded"
            >
              Submit
            </button>
          </div>
        </div>
      </div>
    </form>
  );
};

export default IssueRecordForm;
