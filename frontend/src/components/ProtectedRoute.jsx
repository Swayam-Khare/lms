import React from 'react';
import { Navigate } from 'react-router-dom';

export const ProtectedRoute = ({ children }) => {
    const isAuthenticated = localStorage.getItem('user') ? true : false;

    return isAuthenticated ? (
        React.isValidElement(children) ? children : null
    ) : (
        <Navigate to="/lib/signin" />
    );
};