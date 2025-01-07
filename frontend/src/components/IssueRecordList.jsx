import React from "react";

const IssueRecordList = ({ issueRecords, onDelete, onUpdate }) => {
  const role = localStorage.getItem("role");

  if (role == "LIBRARIAN") {
    return (
      <LibrarianList issueRecords={issueRecords} onDelete={onDelete} onUpdate={onUpdate} />
    );
  }
  else {
    return (
      <UserList issueRecords={issueRecords} />
    );
  }
};

const LibrarianList = ({ issueRecords, onDelete, onUpdate }) => {
  const role = localStorage.getItem("role");

  if (issueRecords.length === 0) {
    return (
      <div className="text-center mt-8">
        <h1 className="text-2xl font-semibold">No Issue Records Found</h1>
      </div>
    )
  }

  console.log("issueRecords: ", issueRecords);
  

  return (
    <table className="min-w-full mt-8 table-auto border-collapse">
      <thead>
        <tr>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            S. No.
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
        {issueRecords.map((record, index) => (
          <tr key={record.id}>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {index+1}
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
}

const UserList = ({ issueRecords }) => {
  const role = localStorage.getItem("role");

  if (issueRecords.length === 0) {
    return (
      <div className="text-center mt-8">
        <h1 className="text-2xl font-semibold">No Issue Records Found</h1>
      </div>
    )
  }

  return (
    <table className="min-w-full mt-8 table-auto border-collapse">
      <thead>
        <tr>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            S. No.
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Issue Date
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Due Date
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Librarian Name
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Book
          </th>
          <th className="border-b-2 border-gray-300 py-2 px-4 text-left">
            Returned
          </th>
        </tr>
      </thead>
      <tbody>
        {issueRecords.map((record, index) => (
          <tr key={record.id}>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {index+1}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.issueDate}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.dueDate}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.librarian.firstName} {record.librarian.lastName}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.issueBook?.length}
            </td>
            <td className="border-b border-gray-200 py-2 px-4 text-left">
              {record.returned ? "Yes" : "No"}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default IssueRecordList;
