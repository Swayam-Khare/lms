

export default function getToken() {
    const result = document.cookie;
    console.log(result);
    const token = result.split('=')[1];
    return token;
}