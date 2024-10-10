import './App.css'
import Home from './views/Home';
import Signin from './views/Signin';
import Signup from './views/Signup';
import { createBrowserRouter, RouterProvider } from "react-router-dom";

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
]);

function App() {

  return <RouterProvider router={router} />;
}

export default App
