

export default function TestOAuth() {

    const googleLogin = () => {
        window.location.href="http://localhost:8080/oauth2/authorization/google";
    }

    const githubLogin = () => {
        window.location.href="http://localhost:8080/oauth2/authorization/github";
    }

  return (
    <div className="p-4">
      <h1 className="text-center text-2xl mb-8">Test OAuth</h1>
      <div className="flex justify-center gap-4">
          <button className="border-2 border-blue-300 p-2 rounded-lg hover:bg-gray-100" onClick={googleLogin}>Login with Google</button>
          <button className="border-2 border-blue-300 p-2 rounded-lg hover:bg-gray-100" onClick={githubLogin}>Login with GitHub</button>
      </div>
    </div>
  );
}
