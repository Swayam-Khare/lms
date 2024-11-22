import React from "react";

const IssueRecordList = ({ issueRecords, onDelete, onUpdate }) => {

  const role = localStorage.getItem("role");
  console.log(issueRecords);

  return (
    <table className="min-w-full mt-8 table-auto border-collapse">
      <thead>
        <tr>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Issue ID
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Issue Date
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Due Date
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Borrower Name
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Book
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Actions
          </th>
        </tr>
      </thead>
      <tbody>
        {issueRecords.map((record) => (
          <tr key={record.id}>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.id}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.issueDate}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.dueDate}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.user.firstName} {record.user.lastName}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.issueBook?.length}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {role == "LIBRARIAN" ? (
                <div className="flex space-x-2">
                  <button
                    onClick={() => onUpdate(record)}
                    className="bg-[#00684a] text-white px-4 py-2 rounded-lg hover:bg-green-500 transition"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => onDelete(record.id)}
                    className="bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-500 transition"
                  >
                    Delete
                  </button>
                </div>
              ) : (
                <></>
              )}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default IssueRecordList;
