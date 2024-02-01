import React, { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../../utils/AuthContext';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
    
  const { authToken, login } = useContext(AuthContext);

   const storedToken = localStorage.getItem('authToken');

  if (storedToken == null) {

    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
};

export default PrivateRoute;
