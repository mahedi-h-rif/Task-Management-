// /features/TaskManagement/TaskForm.js
import React, { useState } from 'react';
import { useAuth } from '../../utils/AuthContext';
import api from '../../service/Api';

const TaskForm = () => {
    const { authToken } = useAuth();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');

    const handleCreateTask = async (e) => {
        e.preventDefault();

        const newTask = { title, description, status };

        try {
            const createdTask = await api.createTask(newTask, authToken);
            // Handle successful task creation, if needed
            console.log('Task created:', createdTask);
        } catch (error) {
            // Handle task creation failure
        }
    };

    return (
        <div>
            <h2>Create Task</h2>
            <form onSubmit={handleCreateTask}>
                <label>Title:</label>
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />

                <label>Description:</label>
                <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />

                <label>Status:</label>
                <input type="text" value={status} onChange={(e) => setStatus(e.target.value)} />

                {/* <button type="submit">Create Task</button> */}
                <div class="flex gap-6">

                    <a type="submit" class="inline-block px-12 py-3 text-sm font-medium text-white bg-blue-600 border border-blue-600 rounded active:text-blue-500 hover:bg-transparent hover:text-blue-600 focus:outline-none focus:ring" href="/tasks">
                        Create
                    </a>
                </div>

            </form>
        </div>
    );
};

export default TaskForm;
