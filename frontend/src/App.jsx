import "./App.css";
import BooksPage from "./views/BooksPage";
import Home from "./views/Home";
import Signin from "./views/Signin";
import Signup from "./views/Signup";
import ViewBooks from "./views/ViewBooks";
import ViewUsers from "./views/ViewUsers";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import MyAccount from "./views/MyAccount";
import UserDashboard from "./views/UserDashboard";
import ViewIssues from "./views/ViewIssues";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/lib/signin",
    element: <Signin getRole="LIBRARIAN" />,
  },
  {
    path: "/user/signin",
    element: <Signin getRole="USER" />,
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
    element: <ViewIssues />,
  },
  {
    path: "/myAccount", // Add the new route for issue records
    element: <MyAccount />,
  },
  {
    path: "/userDashboard", // Add the new route for issue records
    element: <UserDashboard />,
  },
  {
    path: "/profile",
    element: <MyAccount />,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
