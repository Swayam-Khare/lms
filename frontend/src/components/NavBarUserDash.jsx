import Logo from "./Logo";
import Button from "./Button";

export default function NavbarUserDash() {
  const goTo = (path) => {
    window.location.href = path;
  }

  function handleSignup() {
    window.location.href = "/signup";
  }

  function handleSignin() {
    window.location.href = "/signin";
  }

  return (
    <nav>
      <div className="flex shadow-md justify-between items-center">
        <div className="ml-32 cursor-pointer">
          <a href="/"><Logo width={40} /></a>
        </div>
        <div className="flex gap-10 mr-2 font-medium">
          <div className="flex items-center gap-5">
            {/* Replace Home and About buttons */}
            <span 
              onClick={() => goTo('/view-all-books')} 
              className="hover:text-primary hover:border-primary h-full py-6 px-4 cursor-pointer"
            >
              View All Books
            </span>
            <span 
              onClick={() => goTo('/view-all-books')} 
              className="hover:text-primary hover:border-primary h-full py-6 px-4 cursor-pointer"
            >
              Book Issue History
            </span>
          </div>

          <div className="border-2 my-4"></div>

          <div className="flex items-center gap-5">
            <span onClick={handleSignin} className="cursor-pointer hover:text-primary">
              Sign in
            </span>
            <div className="flex items-center ">
              <Button label={"Sign Up"} onClick={handleSignup} />
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
