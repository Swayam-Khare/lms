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
import TestOAuth from "./views/TestOAuth";
import Dashboard from "./views/Dashboard";

import ProtectedRoute from "./components/ProtectedRoute";

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
    element: (
      <ProtectedRoute>
        <BooksPage />
      </ProtectedRoute>
    ),
  },
  {
    path: "/editBook/:bookId",
    element: (
      <ProtectedRoute>
        <BooksPage />
      </ProtectedRoute>
    ),
  },
  {
    path: "/viewBooks",
    element: (
      <ProtectedRoute>
        <ViewBooks />
      </ProtectedRoute>
    ),
  },
  {
    path: "/viewUsers",
    element: (
      <ProtectedRoute>
        <ViewUsers />
      </ProtectedRoute>
    ),
  },
  {
    path: "/viewIssueRecords",
    element: (
      <ProtectedRoute>
        <ViewIssues />
      </ProtectedRoute>
    ),
  },
  {
    path: "/myAccount",
    element: (
      <ProtectedRoute>
        <MyAccount />
      </ProtectedRoute>
    ),
  },
  {
    path: "/userDashboard",
    element: (
      <ProtectedRoute>
        <UserDashboard />
      </ProtectedRoute>
    ),
  },
  {
    path: "/profile",
    element: (
      <ProtectedRoute>
        <MyAccount />
      </ProtectedRoute>
    ),
  },
  {
    path: "/test",
    element: (
      <ProtectedRoute>
        <TestOAuth />
      </ProtectedRoute>
    ),
  },
  {
    path: "/test/dash",
    element: (
      <ProtectedRoute>
        <Dashboard />
      </ProtectedRoute>
    ),
  }
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
