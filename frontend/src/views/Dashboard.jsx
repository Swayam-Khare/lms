import { useState, useEffect } from "react";
import axios from "axios";

export default function Dashboard() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/user", {
        withCredentials: true,
      })
      .then((response) => {
        setUser(response.data);
        console.log(response.data);
        
      });
  }, []);

  return (
    <div className="flex flex-col gap-4">
      <h2>Dashboard</h2>
      {user ? (
        <>
          <p>
            <strong>Name: </strong>{user.name}
          </p>
          <p>
            <strong>Email: </strong>{user.email}
          </p>
        </>
      ) : (
        <div>Loading...</div>
      )}
    </div>
  );
}
