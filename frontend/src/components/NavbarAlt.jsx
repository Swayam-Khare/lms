import React, { useState } from "react";
import Logo from "./Logo";
import ProfileMenu from "./ProfileMenu";
import { useNavigate } from "react-router-dom";

export default function NavbarAlt({ user }) {
  const navigate = useNavigate();
  const role = localStorage.getItem("role");

  const goTo = (path) => {
    navigate(`/${path}`);
  };

  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav>
      <div className="flex shadow-md justify-between items-center">
        <div className="ml-32 cursor-pointer">
          <a
            onClick={() => {
              goTo("");
            }}
          >
            <Logo width={40} />
          </a>
        </div>
        <div className="flex gap-10 mr-2 font-medium">
          <div className="flex items-center gap-5">
            {role == "LIBRARIAN" ? (
              <>
                <span
                  onClick={() => goTo("viewUsers")}
                  className="hover:text-primary border-white border-b-4 hover:border-primary  h-full py-6 px-4 cursor-pointer"
                >
                  Members
                </span>
              </>
            ) : (
              <></>
            )}
            <span
              onClick={() => goTo("viewBooks")}
              className="hover:text-primary border-white border-b-4 hover:border-primary  h-full py-6 px-4 cursor-pointer"
            >
              Books
            </span>

            <span
              onClick={() => goTo("viewIssueRecords")}
              className="hover:text-primary border-white border-b-4 hover:border-primary  h-full py-6 px-4 cursor-pointer"
            >
              Issue Records
            </span>
          </div>

          <div className="border-2 my-4"></div>

          <div className="flex items-center gap-5">
            <div className="relative">
              <button
                className="flex justify-center items-center mr-4"
                onClick={() => setIsOpen(!isOpen)}
              >
                <div className="bg-red-100 flex w-4 h-4 p-5 border-gray-300 border-2 hover:border-black justify-center items-center mr-2 rounded-full ">
                  {user.firstName.charAt(0).toUpperCase()}
                </div>
              </button>
              {isOpen && <ProfileMenu user={user} onClick={goTo} />}
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
