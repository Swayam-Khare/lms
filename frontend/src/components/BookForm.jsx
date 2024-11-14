import { useEffect, useState } from "react";
import axios from "axios";
import { getToken } from "../utils/cookieUtils";

const BookForm = ({ onSubmit, selectedBook, setSelectedBook }) => {
  const [loading, setLoading] = useState(true);
  const [isbn, setISBN] = useState("");
  const [title, setTitle] = useState("");
  const [publishingYear, setPublishingYear] = useState("");
  const [pages, setPages] = useState("");
  const [edition, setEdition] = useState("");
  const [publishingHouse, setPublishingHouse] = useState(null);
  const [author, setAuthor] = useState(null);
  const [genre, setGenre] = useState(null);
  const [allAuthors, setAllAuthors] = useState([]);
  const [allPublishingHouses, setAllPublishingHouses] = useState([]);
  const [allGenres, setAllGenres] = useState([]);
  const [selectAuthor, setSelectAuthor] = useState(1);

  const resetForm = () => {
    setTitle("");
    setISBN("");
    setPublishingYear("");
    setPages("");
    setEdition("");
    setPublishingHouse("");
    setAuthor([]);
    setGenre([]);
  };

  useEffect(() => {
    if (selectedBook) {
      setSelectedBook((book) => {
        return {
          id: book.id,
          title,
          isbnNumber: isbn,
          publishYear: publishingYear,
          pages,
          edition,
          publishingHouse,
          author: [author],
          genre: [genre],
        };
      });
    }
  }, [isbn, title, publishingYear, pages, edition, publishingHouse, author, genre]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    onSubmit({
      title,
      isbnNumber: isbn,
      publishYear: publishingYear,
      pages,
      edition,
      publishingHouse,
      author: [author],
      genre: [genre],
    });
    // setSelectedBook(null);
  };

  const handleAuthorChange = (i) => {
    const auth = allAuthors.find((val) => val.id == i);
    setAuthor(auth);
  };

  const handleGenreChange = (i) => {
    const gen = allGenres.find((val) => val.id == i);
    setGenre(gen);
  };

  const handlePublishingHouseChange = (i) => {
    const pubHouse = allPublishingHouses.find((val) => val.id == i);
    setPublishingHouse(pubHouse);
  };

  async function fetchAuthors() {
    try {
      const response = await axios.get("http://localhost:8080/api/author/", {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      if (response.data?.length) {
        setAllAuthors(response.data);
        setAuthor(response.data[0]);
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function fetchGenres() {
    try {
      const response = await axios.get("http://localhost:8080/api/genre/", {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        withCredentials: true,
      });

      if (response.data?.length) {
        setAllGenres(response.data);
        setGenre(response.data[0]);
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function fetchPublishingHouse() {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/publishing-house/",
        {
          headers: {
            Authorization: "Bearer " + getToken(),
          },
          withCredentials: true,
        }
      );

      if (response.data?.length) {
        setAllPublishingHouses(response.data);
        setPublishingHouse(response.data[0]);
      }
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    const promise1 = fetchAuthors();
    const promise2 = fetchGenres();
    const promise3 = fetchPublishingHouse();

    if (selectedBook) {
      setTitle(selectedBook.title);
      setISBN(selectedBook.isbnNumber);
      setPages(selectedBook.pages);
      setEdition(selectedBook.edition);
      setPublishingHouse(selectedBook.publishingHouse);
      setAuthor(selectedBook.author[0] || null);
      setGenre(selectedBook.genre[0] || null);

      const year = parseInt(selectedBook.publishYear)
      setPublishingYear(year)
      
    } else {
      resetForm();
    }

    Promise.all([promise1, promise2, promise3]).then((_values) => {
      setLoading(false);
    });
  }, []);

  return loading ? (
    <div>Loading...</div>
  ) : (
    <form onSubmit={handleSubmit} className="bg-white p-3 rounded-lg">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mb-4">
        {/* ISBN Number */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium">ISBN Number</label>
          <input
            type="text"
            placeholder="Enter ISBN Number"
            value={isbn}
            onChange={(e) => setISBN(e.target.value)}
            required
            className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
              focus:ring focus:ring-primary focus:border-primary"
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
            className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
              focus:ring focus:ring-primary focus:border-primary"
          />
        </div>

        {/* Published Year */}
        <div className="mb-4">
          <label className="block text-gray-700 font-medium">
            Published Year
          </label>
          <input
            type="number"
            placeholder="Enter Published Year"
            value={publishingYear}
            onChange={(e) => setPublishingYear(e.target.value)}
            required
            min="1"
            step="1"
            className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900
              focus:outline-none focus:ring focus:ring-primary focus:border-primary"
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
            className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
              focus:ring focus:ring-primary focus:border-primary"
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
            className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
              focus:ring focus:ring-primary focus:border-primary"
          />
        </div>

        {/* Publishing House */}
        <div className="flex mb-4 items-center space-x-4">
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">
              Publishing House
            </label>
            <select
              defaultValue={allPublishingHouses[0].id}
              onChange={(e) => handlePublishingHouseChange(e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
                focus:ring focus:ring-primary focus:border-primary"
            >
              {allPublishingHouses.map((val, i) => (
                <option key={i} value={val.id}>
                  {val.name}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Author Name */}
        <div className="flex mb-4 items-center space-x-4">
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">
              Author Name
            </label>
            <select
              defaultValue={selectAuthor}
              onChange={(e) => handleAuthorChange(e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg 
               text-gray-900 focus:outline-none focus:ring focus:ring-primary 
               focus:border-primary"
            >
              {allAuthors.map((val, i) => (
                <option key={i} value={val.id}>
                  {val.firstName} {val.lastName}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Genre */}
        <div className="flex mb-4 items-center space-x-4">
          <div className="flex-1">
            <label className="block text-gray-700 font-medium">Genre</label>
            <select
              defaultValue={allGenres[0].id}
              onChange={(e) => handleGenreChange(e.target.value)}
              required
              className="w-full p-2 mt-2 border border-gray-300 rounded-lg text-gray-900 focus:outline-none
                focus:ring focus:ring-primary focus:border-primary"
            >
              {allGenres.map((val, i) => (
                <option key={i} value={val.id}>
                  {val.genre}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Submit Button */}
        <div className="flex justify-start items-end">
          <button
            type="submit"
            className="bg-primary text-white py-2 px-4 rounded-lg hover:bg-primary-dark transition"
          >
            {selectedBook ? "Update Book" : "Add Book"}
          </button>
        </div>
      </div>
    </form>
  );
};

export default BookForm;
