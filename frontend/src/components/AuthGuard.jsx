import React from 'react';
import { Navigate } from 'react-router-dom';

const AuthGuard = ({ children }) => {
    const user = localStorage.getItem('user');

    if (user) {
        return <Navigate to="/" />;
    }

    return <>{children}</>;
};

export default AuthGuard;