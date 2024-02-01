const BASE_URL = 'http://localhost:8080/api';

const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
};


const api = {
    register: (userData) =>
        fetch(`${BASE_URL}/register`, {
            method: 'POST',
            headers,
            body: JSON.stringify(userData),
        }).then((response) => response.json()),

    login: (authData) =>
        fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers,
            body: JSON.stringify(authData),
        }).then((response) => response.text()),

    createTask: (taskData, token) =>
        fetch(`${BASE_URL}/createTask`, {
            method: 'POST',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
            body: JSON.stringify(taskData),
        }).then((response) => response.json()),

    getAllTasks: (page, size, sortBy, token) =>
        fetch(`${BASE_URL}/allTasks?page=${page}&size=${size}&sortBy=${sortBy}`, {
            method: 'GET',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
        }).then((response) => response.json()),

    getAllUserTasks: (token) =>
        fetch(`${BASE_URL}/tasks`, {
            method: 'GET',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
        }).then((response) => response.json()),

    updateTask: (taskId, updatedTaskData, token) =>
        fetch(`${BASE_URL}/updateTask/${taskId}`, {
            method: 'PUT',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
            body: JSON.stringify(updatedTaskData),
        }).then((response) => response.json()),

    deleteTask: (taskId, token) =>
        fetch(`${BASE_URL}/deleteTask/${taskId}`, {
            method: 'DELETE',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .catch((error) => console.error('Error:', error)),

    getUserRoles: (token) =>
        fetch(`${BASE_URL}/roles`, {
            method: 'GET',
            headers: {
                ...headers,
                'Authorization': `Bearer ${token}`,
            },
        }).then((response) => response.text()),


};

export default api;