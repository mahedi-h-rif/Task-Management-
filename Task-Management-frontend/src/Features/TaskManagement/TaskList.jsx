import React, { useEffect, useState } from 'react';
import { useAuth } from '../../utils/AuthContext';
import api from '../../service/Api';
import { jwtDecode } from 'jwt-decode';

const TaskList = () => {
  const { authToken } = useAuth();
  const [tasks, setTasks] = useState([]);
  const [userInfo, setUserInfo] = useState(null);
  const [userRoles, setUserRoles] = useState([]);

  const fetchUserRoles = async () => {
    try {
      const roles = await api.getUserRoles(authToken);
      setUserRoles(roles.split(',')); // Assuming roles are comma-separated
    } catch (error) {
      // Handle error fetching user roles
    }
  };

  const fetchTasks = async () => {
    try {
      await fetchUserRoles();

      if (userRoles.includes('ADMIN')) {
        const response = await api.getAllTasks(0, 10, 'id', authToken);
        setTasks(response.content);
      } else {
        const response = await api.getAllUserTasks(authToken);
        setTasks(response);
      }

      const decodedToken = jwtDecode(authToken);
      setUserInfo(decodedToken);
    } catch (error) {
      // Handle error fetching tasks or roles
    }
  };

  useEffect(() => {
    fetchTasks();
  }, [authToken]);

  const handleDeleteTask = async (taskId) => {
    try {
      await api.deleteTask(taskId, authToken);
      console.log('Task deleted:', taskId);
      fetchTasks();
    } catch (error) {
      // Handle error deleting task
    }
  };

  return (
    <>
      <div className="flex gap-6">
        <a className="inline-block px-12 py-3 text-sm font-medium text-white bg-blue-600 border border-blue-600 rounded active:text-blue-500 hover:bg-transparent hover:text-blue-600 focus:outline-none focus:ring" href="/create-tasks">
          Add
        </a>
      </div>

      <div className="max-w-sm mx-auto mt-16">
        <ul className="bg-white shadow overflow-hidden sm:rounded-md">
          {tasks.map((task) => (
            <li key={task.id} className="border-t border-gray-200">
              <div className="px-4 py-5 sm:px-6">
                <div className="flex items-center justify-between">
                  <h3 className="text-lg leading-6 font-medium text-gray-900">{task.title}</h3>
                  <p className="mt-1 max-w-2xl text-sm text-gray-500">{task.description}</p>
                </div>
                <div className="mt-4 flex items-center justify-between">
                  <p className="text-sm font-medium text-gray-500">
                    Status: <span className={`text-${task.status === 'Active' ? 'green' : (task.status === 'Inactive' ? 'red' : 'yellow')}-600`}>{task.status}</span>
                  </p>
                  {/* {userRoles.includes('ADMIN') && (
                      )} */}
                      <button onClick={() => handleDeleteTask(task.id)} className="font-medium text-indigo-600 hover:text-indigo-500">Delete</button>
                </div>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default TaskList;
