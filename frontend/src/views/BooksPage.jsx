import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom"; // Import useLocation for route state
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import Footer from "../components/Footer"; // Adjust the path as necessary
import Fab from "../components/Fab"; // Adjust the path as necessary
import BookForm from "../components/BookForm";

export default function BooksPage() {
  const [loading, setLoading] = useState(true);
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState(null); // For editing a book

  const location = useLocation(); // Access route state

  useEffect(() => {
    // Set dummy books data
    const dummyBooks = [
      { id: 1, title: 'Book One', authorName: 'Author One' },
      { id: 2, title: 'Book Two', authorName: 'Author Two' },
      { id: 3, title: 'Book Three', authorName: 'Author Three' },
    ];

    setBooks(dummyBooks);
    setLoading(false); // Simulate loading completed

    // Check if a book is passed via route state for editing
    if (location.state && location.state.book) {
      setSelectedBook(location.state.book); // Pre-fill the form with selected book details
    }
  }, [location.state]);

  async function addBook(book) {
    // TODO
  }

  const handleAddOrUpdateBook = async (book) => {
    if (selectedBook.id) {
      setBooks(books.map(b => (b.id === selectedBook.id ? book : b)));
    } else {
      console.log("handleAdd", book);

      await addBook
    }
    setSelectedBook(null);
  };

  const handleDeleteBook = (id) => {
    setBooks(books.filter(b => b.id !== id));
  };

  return (
    <div className="bg-white min-h-screen">
      <div className="max-w-7xl mx-auto p-4">
        <h1 className="text-3xl font-bold text-primary my-4">Books</h1>
        {loading ? (
          <Skeleton height={400} count={5} />
        ) : (
          <>
            <BookForm
              onSubmit={handleAddOrUpdateBook}
              selectedBook={selectedBook}
              setSelectedBook={setSelectedBook}
            />
            <Footer />
            <Fab />
          </>
        )}
      </div>
    </div>
  );
}
