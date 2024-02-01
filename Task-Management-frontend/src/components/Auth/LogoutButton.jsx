import React from 'react';
import { useAuth } from '../../utils/AuthContext';

const LogoutButton = () => {
  const { logout } = useAuth();

  const handleLogout = () => {
    logout();
  };

  return <button className='btn' onClick={handleLogout}>Logout</button>;
};

export default LogoutButton;
