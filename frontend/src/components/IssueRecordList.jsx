
import React from 'react';

const IssueRecordList = ({ issueRecords, onDelete, onUpdate }) => {
  return (
    <table className="min-w-full table-auto border-collapse">
      <thead>
        <tr>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Issue ID</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Issue Date</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Due Date</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">User Name</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Librarian Name</th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Books</th>
        </tr>
      </thead>
      <tbody>
        {issueRecords.map((record) => (
          <tr key={record.id}>
            <td className="border-b border-gray-200 py-2 px-4 text-left">{record.id}</td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">{record.issueDate}</td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">{record.dueDate}</td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">{record.userName}</td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">{record.librarianName}</td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.books.map((book, index) => (
                <div key={index}>{book.bookName} (ID: {book.bookId})</div>
              ))}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default IssueRecordList;
