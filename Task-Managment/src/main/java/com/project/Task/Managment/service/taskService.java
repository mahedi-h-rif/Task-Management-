package com.project.Task.Managment.service;

import com.project.Task.Managment.repository.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class taskService {
    private final taskRepository taskRepository;


    public taskService(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

}
