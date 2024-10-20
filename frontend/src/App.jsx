import './App.css';
import BooksPage from './views/BooksPage';
import Home from './views/Home';
import Signin from './views/Signin';
import Signup from './views/Signup';
import ViewBooks from './views/ViewBooks';
import ViewUsers from './views/ViewUsers';
import ViewIssueRecords from './views/ViewIssueRecords'; 
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import MyAccount from './views/MyAccount';
import ChangePassword from './views/ChangePassword';
import UserDashboard from './views/UserDashboard';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/signin",
    element: <Signin />,
  },
  {
    path: "/signup",
    element: <Signup />,
  },
  {
    path: "/addBook",
    element: <BooksPage />, // Add book page
  },
  {
    path: "/editBook/:bookId",
    element: <BooksPage />, // Edit book page
  },
  {
    path: "/viewBooks",
    element: <ViewBooks />, // View all books
  },
  {
    path: "/viewUsers", // Add route for ViewUsers
    element: <ViewUsers />,
  },
  {
    path: "/viewIssueRecords", // Add the new route for issue records
    element: <ViewIssueRecords />,
  },
  {
    path: "/myAccount", // Add the new route for issue records
    element: <MyAccount />,
  },
  {
    path: "/changePassword", // Add the new route for issue records
    element: <ChangePassword />,
  },
  {
    path: "/userDashboard", // Add the new route for issue records
    element: <UserDashboard />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
