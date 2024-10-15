

export default function getToken() {
    const result = document.cookie;
    const token = result.split('=')[1];
    return token;
}