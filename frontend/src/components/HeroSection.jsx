import { ReactTyped } from "react-typed";
import Logo from "../components/Logo"
import Lottie from 'react-lottie';
import animationData from '../assets/Hero.json';
import Button from "../components/Button";

export default function HeroSection() {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: animationData,
    rendererSettings: {
      preserveAspectRatio: 'xMidYMid slice'
    }
  };

  function handleSignup() {
    window.location.href = "/signup";
  }

  return (
    <div className="mx-32 py-10 flex flex-wrap justify-between">
      <div style={{ width: "600px" }} className="mt-14">
        <div className="text-2xl mb-6 font-bold text-gray-800">
          Edify, your gateway to knowledge
        </div>
        <div className="text-xl font-medium mb-2">
          Our Library Management System is designed to provide easy access to
          our extensive collection of books and journals
        </div>
        <div className="text-7xl text-primary font-bold">
          <ReactTyped
            strings={["Explore!", "Borrow!", "Learn!"]}
            typeSpeed={130}
            loop
            backSpeed={30}
            cursorChar="|"
            showCursor={true}
          />
        </div>
        <div className="text-xl font-medium mt-6">
          Explore our vast selection of books, e-books, research papers, and
          journals across a variety of genres and subjects. Borrow books and
          resources with just a few clicks. Track your borrowed items, return
          dates, and request renewals online.
        </div>
        <div className="w-40 mt-4">
          <Button label={"Sign Up"} onClick={handleSignup} />
        </div>
      </div>

      <div className="mr-10 cursor-default">
        <Lottie options={defaultOptions} width={570} />
      </div>
    </div>
  );
}