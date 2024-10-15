
import { useEffect, useState } from "react";
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';
import Footer from "../components/Footer";
import IssueRecordList from "../components/IssueRecordList"; 
import IssueRecordForm from "../components/IssueRecordForm"; 

const ViewIssueRecords = () => {
  const [loading, setLoading] = useState(true);
  const [issueRecords, setIssueRecords] = useState([]);
  const [showModal, setShowModal] = useState(false); 
  const [record, setRecord] = useState({
    id: '',
    issueDate: '',
    dueDate: '',
    userName: '',
    librarianName: '',
    books: [{ bookId: '', bookName: '' }],
  });

  useEffect(() => {
    // Dummy data
    const dummyRecords = [
      {
        id: 1,
        issueDate: '2024-01-01',
        dueDate: '2024-01-15',
        userName: 'John Doe',
        librarianName: 'Librarian A',
        books: [
          { bookId: 101, bookName: 'Book One' },
          { bookId: 102, bookName: 'Book Two' },
        ],
      },
      {
        id: 2,
        issueDate: '2024-02-01',
        dueDate: '2024-02-15',
        userName: 'Jane Smith',
        librarianName: 'Librarian B',
        books: [
          { bookId: 103, bookName: 'Book Three' },
        ],
      },
    ];

    setIssueRecords(dummyRecords);
    setLoading(false);
  }, []);

  const handleDelete = (id) => {
    setIssueRecords(issueRecords.filter(record => record.id !== id));
  };

  const handleUpdate = (selectedRecord) => {
    setRecord(selectedRecord);
    setShowModal(true);
  };

  const handleAddOrUpdate = () => {
    if (record.id) {
      setIssueRecords(issueRecords.map(r => (r.id === record.id ? record : r)));
    } else {
      setIssueRecords([...issueRecords, { ...record, id: issueRecords.length + 1 }]);
    }
    setShowModal(false);
    resetForm();
  };

  const resetForm = () => {
    setRecord({
      id: '',
      issueDate: '',
      dueDate: '',
      userName: '',
      librarianName: '',
      books: [{ bookId: '', bookName: '' }],
    });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setRecord((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const addBook = () => {
    setRecord((prev) => ({
      ...prev,
      books: [...prev.books, { bookId: '', bookName: '' }],
    }));
  };

  return (
    <div className="p-6">
      <button 
        onClick={() => setShowModal(true)} 
        className="bg-[#00684a] text-white px-4 py-2 rounded mb-4"
      >
        Add Issue Record
      </button>
      {loading ? (
        <Skeleton height={400} count={5} />
      ) : (
        <>
          <IssueRecordList 
            issueRecords={issueRecords} 
            onDelete={handleDelete} 
            onUpdate={handleUpdate} 
          />
          <Footer />
          <IssueRecordForm 
            showModal={showModal}
            onClose={() => setShowModal(false)}
            record={record}
            onChange={handleChange}
            onSubmit={handleAddOrUpdate}
            addBook={addBook}
          />
        </>
      )}
    </div>
  );
};

export default ViewIssueRecords;
