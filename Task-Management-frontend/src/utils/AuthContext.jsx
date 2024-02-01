import React, { createContext, useContext, useEffect, useState } from 'react';

export const AuthContext = createContext();  

export const AuthProvider = ({ children }) => {
  const [authToken, setAuthToken] = useState(null);

  useEffect(() => {
    try {
      const storedToken = localStorage.getItem('authToken');
      if (storedToken) {
        setAuthToken(storedToken);
      }
    } catch (error) {
      console.error('Error retrieving token:', error);
    }
  }, []);

  const login = (token) => {
    try {
      setAuthToken(token);
      localStorage.setItem('authToken', token);
    } catch (error) {
      console.error('Error saving token:', error);
    }
  };

  const logout = () => {
    setAuthToken(null);
    localStorage.removeItem('authToken');
  };

  useEffect(() => {
    console.log('authToken:', authToken);
  }, [authToken]);

  return (
    <AuthContext.Provider value={{ login, logout, authToken }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
