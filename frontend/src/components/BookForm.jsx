import { useEffect, useState } from "react";

const BookForm = ({ onSubmit, selectedBook, setSelectedBook }) => {
  const [isbn, setISBN] = useState("");
  const [title, setTitle] = useState("");
  const [publishingYear, setPublishingYear] = useState("");
  const [pages, setPages] = useState("");
  const [edition, setEdition] = useState("");
  const [publishingID, setPublishingID] = useState("");
  const [publishingName, setPublishingName] = useState("");
  const [authors, setAuthors] = useState([{ id: "", name: "" }]);
  const [genres, setGenres] = useState([{ id: "", name: "" }]);

  useEffect(() => {
    if (selectedBook) {
      setTitle(selectedBook.title);
      setISBN(selectedBook.isbn);
      setPublishingYear(selectedBook.publishingYear);
      setPages(selectedBook.pages);
      setEdition(selectedBook.edition);
      setPublishingID(selectedBook.publishingID);
      setPublishingName(selectedBook.publishingName);
      setAuthors(selectedBook.authors || [{ id: "", name: "" }]);
      setGenres(selectedBook.genres || [{ id: "", name: "" }]);
    } else {
      resetForm();
    }
  }, [selectedBook]);

  const resetForm = () => {
    setTitle("");
    setISBN("");
    setPublishingYear("");
    setPages("");
    setEdition("");
    setPublishingID("");
    setPublishingName("");
    setAuthors([{ id: "", name: "" }]);
    setGenres([{ id: "", name: "" }]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({
      title,
      isbn,
      publishingYear,
      pages,
      edition,
      publishingID,
      publishingName,
      authors,
      genres,
    });
    setSelectedBook(null);
  };

  const handleAddAuthor = () => {
    setAuthors([...authors, { id: "", name: "" }]);
  };

  const handleRemoveAuthor = () => {
    if (authors.length > 1) {
      setAuthors(authors.slice(0, authors.length - 1));
    }
  };

  const handleAddGenre = () => {
    setGenres([...genres, { id: "", name: "" }]);
  };

  const handleRemoveGenre = () => {
    if (genres.length > 1) {
      setGenres(genres.slice(0, genres.length - 1));
    }
  };

  const handleAuthorChange = (index, field, value) => {
    const newAuthors = [...authors];
    newAuthors[index][field] = value;
    setAuthors(newAuthors);
  };

  const handleGenreChange = (index, field, value) => {
    const newGenres = [...genres];
    newGenres[index][field] = value;
    setGenres(newGenres);
  };

  return (
    <form onSubmit={handleSubmit} className="mb-8 bg-white p-6 rounded-lg shadow-lg">
      <h2 className="text-xl font-bold text-primary mb-4">{selectedBook ? "Edit Book" : "Add New Book"}</h2>

      {/* ISBN Number */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">ISBN Number</label>
        <input
          type="text"
          placeholder="Enter ISBN Number"
          value={isbn}
          onChange={(e) => setISBN(e.target.value)}
          required
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
        />
      </div>

      {/* Book Title */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Book Title</label>
        <input
          type="text"
          placeholder="Enter Book Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
        />
      </div>

      {/* Published Year */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Published Year</label>
        <input
          type="number"
          placeholder="Enter Published Year"
          value={publishingYear}
          onChange={(e) => setPublishingYear(e.target.value)}
          required
          min="1"
          step="1"
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
          onKeyDown={(e) => {
            // Allow only number keys, backspace, delete, and arrow keys
            if (
              !/[0-9]/.test(e.key) &&
              e.key !== "Backspace" &&
              e.key !== "Delete" &&
              e.key !== "ArrowLeft" &&
              e.key !== "ArrowRight"
            ) {
              e.preventDefault();
            }
          }}
        />
      </div>

      {/* Pages */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Pages</label>
        <input
          type="number"
          placeholder="Enter Pages"
          value={pages}
          onChange={(e) => setPages(e.target.value)}
          required
          min="1"
          step="1"
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
          onKeyDown={(e) => {
            // Allow only number keys, backspace, delete, and arrow keys
            if (
              !/[0-9]/.test(e.key) &&
              e.key !== "Backspace" &&
              e.key !== "Delete" &&
              e.key !== "ArrowLeft" &&
              e.key !== "ArrowRight"
            ) {
              e.preventDefault();
            }
          }}
        />
      </div>

      {/* Edition */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Edition</label>
        <input
          type="number"
          placeholder="Enter Edition"
          value={edition}
          onChange={(e) => setEdition(e.target.value)}
          required
          min="1"
          step="1"
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
          
        />
      </div>

      {/* Publishing House ID */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Publishing House ID</label>
        <input
          type="text"
          placeholder="Enter Publishing House ID"
          value={publishingID}
          onChange={(e) => setPublishingID(e.target.value)}
          required
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
        />
      </div>

      {/* Publishing House Name */}
      <div className="mb-4">
        <label className="block text-gray-700 font-medium">Publishing House Name</label>
        <input
          type="text"
          placeholder="Enter Publishing House Name"
          value={publishingName}
          onChange={(e) => setPublishingName(e.target.value)}
          required
          className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
        />
      </div>

      {/* Authors Section */}
      <div className="flex items-center mb-4">
        <button
          type="button"
          onClick={handleAddAuthor}
          className="bg-primary text-white rounded-full w-8 h-8 flex items-center justify-center mr-2"
          aria-label="Add Author"
        >
          +
        </button>
        <h3 className="text-lg font-semibold">Authors</h3>
        {authors.length > 1 && (
          <button
            type="button"
            onClick={handleRemoveAuthor}
            className="bg-red-600 text-white rounded-full w-8 h-8 flex items-center justify-center ml-2"
            aria-label="Remove Author"
          >
            -
          </button>
        )}
      </div>

      {authors.map((author, index) => (
        <div key={index} className="flex mb-4 items-center space-x-4">
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">Author ID</label>
            <input
              type="text"
              placeholder="Enter Author's ID"
              value={author.id}
              onChange={(e) => handleAuthorChange(index, "id", e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
            />
          </div>
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">Author Name</label>
            <input
              type="text"
              placeholder="Enter Author's Name"
              value={author.name}
              onChange={(e) => handleAuthorChange(index, "name", e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
            />
          </div>
        </div>
      ))}

      {/* Genres Section */}
      <div className="flex items-center mb-4">
        <button
          type="button"
          onClick={handleAddGenre}
          className="bg-primary text-white rounded-full w-8 h-8 flex items-center justify-center mr-2"
          aria-label="Add Genre"
        >
          +
        </button>
        <h3 className="text-lg font-semibold">Genres</h3>
        {genres.length > 1 && (
          <button
            type="button"
            onClick={handleRemoveGenre}
            className="bg-red-600 text-white rounded-full w-8 h-8 flex items-center justify-center ml-2"
            aria-label="Remove Genre"
          >
            -
          </button>
        )}
      </div>

      {genres.map((genre, index) => (
        <div key={index} className="flex mb-4 items-center space-x-4">
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">Genre ID</label>
            <input
              type="text"
              placeholder="Enter Genre ID"
              value={genre.id}
              onChange={(e) => handleGenreChange(index, "id", e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
            />
          </div>
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">Genre Name</label>
            <input
              type="text"
              placeholder="Enter Genre Name"
              value={genre.name}
              onChange={(e) => handleGenreChange(index, "name", e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none focus:ring focus:ring-primary focus:border-primary"
            />
          </div>
        </div>
      ))}

      {/* Submit Button */}
      <button
        type="submit"
        className="bg-primary text-white py-2 px-4 rounded-lg hover:bg-primary-dark transition"
      >
        {selectedBook ? "Update Book" : "Add Book"}
      </button>
    </form>
  );
};

export default BookForm;
