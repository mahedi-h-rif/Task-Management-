import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './App.css';
import { RouterProvider } from 'react-router-dom';
import { createBrowserRouter } from 'react-router-dom';


import App from './App';
import TaskList from './Features/TaskManagement/TaskList';
import { AuthProvider } from './utils/AuthContext';
import LoginForm from './components/Auth/LoginForm';
import PrivateRoute from './components/Auth/PrivateRoute';
import Register from './components/Auth/Registration';
import TaskForm from './Features/TaskManagement/TaskForm';


const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '/tasks',
        element: <PrivateRoute>< TaskList /></PrivateRoute>,
      },
      {
        path: '/login',
        element: < LoginForm />,
      },
      {
        path: '/register',
        element: < Register />,
      },
      {
        path : "/create-tasks",
        element :<PrivateRoute><TaskForm/></PrivateRoute>,
      },
    ]
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router}></RouterProvider>
    </AuthProvider>
  </React.StrictMode>
);
