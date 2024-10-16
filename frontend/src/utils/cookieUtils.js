

export function getToken() {
    const result = document.cookie;
    const token = result.split('=')[1];
    return token;
}

export function deleteCookie() {
    const date = new Date();
    // Set it expire in -1 days
    date.setTime(date.getTime() + (-1 * 24 * 60 * 60 * 1000));
    // Set it
    document.cookie = "jwtAuth"+"=; expires="+date.toUTCString()+"; path=/";
}