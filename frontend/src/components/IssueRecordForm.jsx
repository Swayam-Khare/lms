import React, { useState } from "react";

const IssueRecordForm = ({
  showModal,
  onClose,
  record,
  onChange,
  onSubmit,
  addBook,
}) => {
  
  const [loading, setLoading] = useState(true);
  const [issueDate, setIssueDate] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [borrower, setBorrower] = useState(null);
  const [librarian, setLibrarian] = useState(null);
  const [isbnNumber, setIsbnNumber] = useState("");
  const [fine, setFine] = useState(0);
  const [isReturned, setIsReturned] = useState(false);
  

  const deleteBook = (index) => {
    const updatedBooks = record.books.filter((_, i) => i !== index);
    onChange({ target: { name: "books", value: updatedBooks } });
  };

  const handleBookChange = (index, field, value) => {
    const updatedBooks = [...record.books];
    updatedBooks[index][field] = value;
    onChange({ target: { name: "books", value: updatedBooks } });
  };

  return (
    showModal && (
      <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
        <div className="bg-white p-12 rounded shadow-lg max-w-3xl w-full max-h-[80vh]">
          <h2 className="text-xl font-bold mb-4">
            {record.id ? "Update" : "Add"} Issue Record
          </h2>
          <div className="max-h-[60vh] overflow-y-auto">
            <input
              type="date"
              name="issueDate"
              placeholder="Issue Date"
              value={record.issueDate}
              onChange={onChange}
              className="border rounded w-full mb-2 p-2"
            />
            <input
              type="date"
              name="dueDate"
              placeholder="Due Date"
              value={record.dueDate}
              onChange={onChange}
              className="border rounded w-full mb-2 p-2"
            />
            <input
              type="text"
              name="userName"
              placeholder="User Name"
              value={record.userName}
              onChange={onChange}
              className="border rounded w-full mb-2 p-2"
            />
            <input
              type="text"
              name="librarianName"
              placeholder="Librarian Name"
              value={record.librarianName}
              onChange={onChange}
              className="border rounded w-full mb-2 p-2"
            />

            <h3 className="font-bold">Books:</h3>
            {record.books.map((book, index) => (
              <div key={index} className="mb-2">
                <input
                  type="text"
                  placeholder="Book Name"
                  value={book.bookName}
                  onChange={(e) =>
                    handleBookChange(index, "bookName", e.target.value)
                  } // Updated onChange handler
                  className="border rounded w-full mb-2 p-2"
                />
                <input
                  type="text"
                  placeholder="Book ID"
                  value={book.bookId}
                  onChange={(e) =>
                    handleBookChange(index, "bookId", e.target.value)
                  } // Updated onChange handler
                  className="border rounded w-full mb-2 p-2"
                />
                {record.books.length > 1 && (
                  <button
                    onClick={() => deleteBook(index)}
                    className="bg-red-500 text-white px-2 py-1 rounded"
                  >
                    Delete
                  </button>
                )}
              </div>
            ))}
            <button
              onClick={addBook}
              className="bg-blue-500 text-white px-4 py-1 rounded mb-2"
            >
              Add Another Book
            </button>
          </div>
          <div className="flex justify-between mt-4">
            <button
              onClick={onSubmit}
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

export default IssueRecordForm;
