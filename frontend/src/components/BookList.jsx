const BookList = ({ books, onEdit, onDelete }) => {

  const role = localStorage.getItem("role");

  return (
    <div className="mt-8">
      <h2 className="text-2xl font-semibold mb-4 text-gray-800">Books List</h2>
      {books.length === 0 ? (
        <p className="text-gray-600">No books available</p>
      ) : (
        <ul className="space-y-4">
          {books.map((book) => (
            <li key={book.id} className="flex flex-col p-4 bg-white rounded-lg shadow-sm">
              <div className="flex justify-between items-center">
                <div className="flex flex-col">
                  <span className="text-lg font-medium mb-2">{book.title}</span>
                  <div className="flex flex-wrap space-x-4 mb-2">
                    <span className="text-sm text-gray-500">ISBN: {book.isbnNumber}</span>
                    <span className="text-sm text-gray-500">Edition: {book.edition}</span>
                    <span className="text-sm text-gray-500">Publishing House: {book.publishingHouse.name}</span>
                    <span className="text-sm text-gray-500">Year: {book.publishYear}</span>
                    <span className="text-sm text-gray-500">Pages: {book.pages}</span>
                  </div>
                </div>
                {role == "LIBRARIAN" ? (<div className="flex space-x-2">
                  <button
                    onClick={() => onEdit(book)}
                    className="bg-[#00684a] text-white px-4 py-2 rounded-lg hover:bg-green-500 transition"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => onDelete(book.id)}
                    className="bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-500 transition"
                  >
                    Delete
                  </button>
                </div>) : <></>}
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default BookList;
