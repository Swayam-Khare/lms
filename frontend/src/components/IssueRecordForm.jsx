import React, { useEffect, useState } from "react";

const IssueRecordForm = ({ onSubmit, selectedRecord, setSelectedRecord }) => {
  const [loading, setLoading] = useState(true);
  const [issueDate, setIssueDate] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [borrower, setBorrower] = useState(null);
  const [librarian, setLibrarian] = useState(null);
  const [isbnNumber, setIsbnNumber] = useState("");
  const [fine, setFine] = useState(0);
  const [issueBooks, setIssueBooks] = useState([]);
  const [isReturned, setIsReturned] = useState(false);

  useEffect(() => {
    setLoading(false);
  }, []);

  return loading ? (
    <div>Loading...</div>
  ) : (
    <form className="bg-white p-3 rounded-lg">
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
          <label htmlFor="user" className="text-primary font-bold ml-1">
            Borrower:
          </label>
          <input
            type="text"
            name="user"
            placeholder="User"
            value={borrower}
            onChange={(e) => setBorrower(e.target.value)}
            className="border rounded w-full mb-2 p-2"
          />
          <label htmlFor="librarian" className="text-primary font-bold ml-1">
            Librarian:
          </label>
          <input
            type="text"
            name="librarian"
            placeholder="Librarian"
            value={librarian}
            onChange={(e) => setLibrarian(e.target.value)}
            className="border rounded w-full mb-2 p-2"
          />

          <h3 className="font-bold">Books:</h3>
          {issueBooks.map((book, index) => (
            <div key={index} className="mb-2">
              <input
                type="text"
                placeholder="ISBN Number"
                value={isbnNumber}
                name="isbnNumber"
                onChange={(e) => setIsbnNumber(e.target.value)}
                className="border rounded w-full mb-2 p-2"
              />
              <input
                name="fine"
                type="number"
                min={0}
                placeholder="Fine"
                value={fine}
                onChange={(e) => setFine(e.target.value)}
                className="border rounded w-full mb-2 p-2"
              />

              <label htmlFor="checkbox"></label>
              <input
                name="isReturned"
                type="checkbox"
                value={isReturned}
                onChange={(e) => setIsReturned(e.target.value)}
                className="border rounded w-full mb-2 p-2"
              />

              {issueBooks.length > 1 && (
                <button
                  type="button"
                  // onClick={() => deleteBook(index)}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Delete
                </button>
              )}
            </div>
          ))}
          <button
            onClick={() => {
              setIssueBooks([
                ...issueBooks,
                { isbnNumber: "", fine: "", isReturned: false },
              ]);
            }}
            type="button"
            className="bg-blue-500 text-white px-4 py-1 rounded mb-2"
          >
            Add Another Book
          </button>
          <div className="flex justify-between mt-4">
            <button
              // onClick={onSubmit}
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
