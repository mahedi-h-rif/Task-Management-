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
        <form className="w-full max-w-sm mx-auto px-4 py-2" onSubmit={handleCreateTask}>
            <div className="flex items-center border-b-2 border-teal-500 py-2">
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
                    placeholder="Title"
                />
            </div>
            <div className="flex items-center border-b-2 border-teal-500 py-2">
                <input
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    className="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
                    placeholder="Description"
                />
            </div>
            <div className="flex items-center border-b-2 border-teal-500 py-2">
                <input
                    type="text"
                    value={status}
                    onChange={(e) => setStatus(e.target.value)}
                    className="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
                    placeholder="Status"
                />
            </div>
            <div className="flex gap-6">
                <button
                    type="submit"
                    className="flex-shrink-0 bg-teal-500 hover:bg-teal-700 border-teal-500 hover:border-teal-700 text-sm border-4 text-white py-1 px-2 rounded"
                >
                    <a  href="/tasks">
                        Create
                    </a>
                </button>
            </div>
        </form>
    );
};

export default TaskForm;
