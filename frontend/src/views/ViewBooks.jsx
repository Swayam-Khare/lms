import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // Import navigation hook
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import Footer from "../components/Footer"; // Adjust the path as necessary

const ViewBooks = () => {
  const [loading, setLoading] = useState(true);
  const [books, setBooks] = useState([]);
  const navigate = useNavigate(); // Initialize the navigation

  useEffect(() => {
    // Simulate fetching books from an API or database
    const fetchBooks = () => {
      // Dummy book data
      const dummyBooks = [
        { id: 1, title: 'The Great Gatsby', isbn: '978-0743273565', edition: '1st', publishingName: 'Scribner', publishingYear: 1925, pages: 180 },
        { id: 2, title: '1984', isbn: '978-0451524935', edition: '1st', publishingName: 'Secker & Warburg', publishingYear: 1949, pages: 328 },
        { id: 3, title: 'To Kill a Mockingbird', isbn: '978-0061120084', edition: '1st', publishingName: 'J.B. Lippincott & Co.', publishingYear: 1960, pages: 281 },
        { id: 4, title: 'Pride and Prejudice', isbn: '978-1503290563', edition: '2nd', publishingName: 'T. Egerton', publishingYear: 1813, pages: 279 },
        { id: 5, title: 'Moby Dick', isbn: '978-1503280786', edition: '1st', publishingName: 'Harper & Brothers', publishingYear: 1851, pages: 635 },
      ];

      setBooks(dummyBooks);
      setLoading(false); // Simulate loading completed
    };

    fetchBooks();
  }, []);

  // Function to handle editing a book
  const handleEdit = (book) => {
    // Navigate to the BooksPage for editing and pass the selected book data
    navigate(`/editBook/${book.id}`, { state: { book } });
  };

  // Function to handle deleting a book
  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this book?")) {
      setBooks(books.filter((book) => book.id !== id));
    }
  };

  return (
    <div className="p-6">
      {loading ? (
        <Skeleton height={400} count={5} />
      ) : (
        <>
          <h1 className="text-3xl font-bold mb-6 text-gray-800">View Books</h1>
          <table className="min-w-full table-auto border-collapse">
            <thead>
              <tr>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Title</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">ISBN</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Edition</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Publisher</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Published Year</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Pages</th>
                <th className="border-b-2 border-gray-300 py-2 px-4 text-left">Actions</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book) => (
                <tr key={book.id}>
                  <td className="border-b border-gray-200 py-2 px-4">{book.title}</td>
                  <td className="border-b border-gray-200 py-2 px-4">{book.isbn}</td>
                  <td className="border-b border-gray-200 py-2 px-4">{book.edition}</td>
                  <td className="border-b border-gray-200 py-2 px-4">{book.publishingName}</td>
                  <td className="border-b border-gray-200 py-2 px-4">{book.publishingYear}</td>
                  <td className="border-b border-gray-200 py-2 px-4">{book.pages}</td>
                  <td className="border-b border-gray-200 py-2 px-4">
                    <button
                      onClick={() => handleEdit(book)}
                      className="bg-green-600 text-white px-2 py-1 rounded hover:bg-green-700"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => handleDelete(book.id)} // Call handleDelete with the book ID
                      className="bg-red-600 text-white px-2 py-1 rounded hover:bg-red-700 ml-2"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <Footer />
        </>
      )}
    </div>
  );
};

export default ViewBooks;
