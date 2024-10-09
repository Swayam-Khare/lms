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
        <div className="text-2xl mb-6 font-bold text-gray-800">An Online Platform for <br /> Self Assessment</div>
        <div className="text-xl font-medium mb-2">Conquering exams through personalized prep.</div>
        <div className="text-7xl text-primary font-bold">
          Welcome <br />
          to{" "}
          <ReactTyped
            strings={["Edify!", "Success!", "Growth!"]}
            typeSpeed={130}
            loop
            backSpeed={30}
            cursorChar="|"
            showCursor={true}
          />
        </div>
        <div className="text-xl font-medium mt-6">An innovative online platform to test student's placement preparation. Edify is designed to seamlessly integrate comprehensive tests with personalized feedback</div>
        <div className="w-40 mt-4">
          <Button label={"Sign Up"} onClick={handleSignup} />
        </div>
      </div>

      <div className="mr-10 cursor-default">
        <Lottie options={defaultOptions} width={570} />
      </div>
    </div>
  )
}