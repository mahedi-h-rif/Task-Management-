import React from 'react';
import { useAuth } from '../../utils/AuthContext';

const LogoutButton = () => {
  const { logout } = useAuth();

  const handleLogout = () => {
    logout();
  };

  return <button className=' btn btn-neutral ml-80' onClick={handleLogout}>Logout</button>;
};

export default LogoutButton;
