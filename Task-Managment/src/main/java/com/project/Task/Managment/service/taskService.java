package com.project.Task.Managment.service;

import com.project.Task.Managment.entity.Task;
import com.project.Task.Managment.repository.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class taskService {
    private final taskRepository taskRepository;


    public taskService(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findByUserName(String username) {
        return taskRepository.findByUserInfoName(username);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void delete(Task existingTask) {
        taskRepository.delete(existingTask);
    }
}
