package com.project.Task.Managment.controller;

import com.project.Task.Managment.DTO.TaskDto;
import com.project.Task.Managment.controller.taskController;
import com.project.Task.Managment.entity.Task;
import com.project.Task.Managment.entity.UserInfo;
import com.project.Task.Managment.service.UserInfoService;
import com.project.Task.Managment.service.taskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class taskControllerTest {

    @Mock
    private taskService taskService;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private taskController taskController;


    @Test
    void getAllTasks_ShouldReturnOkResponse() {

        UserDetails userDetails = new User("adminUser", "password", Collections.emptyList());

        ResponseEntity<Page<TaskDto>> responseEntity = taskController.getAllTasks(0, 10, "id", userDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllTaskForUser_ShouldReturnOkResponse() {

        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());
        when(taskService.findByUserName("testUser")).thenReturn(Collections.emptyList());

        ResponseEntity<List<TaskDto>> responseEntity = taskController.getAllTaskForUser(userDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateTask_ShouldReturnOkResponse() {
        Long taskId = 1L;
        TaskDto updatedTaskDto = new TaskDto();
        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());
        when(taskService.findById(taskId)).thenReturn(Optional.of(new Task()));
        when(taskService.saveTask(any(Task.class))).thenReturn(new Task());

        ResponseEntity<TaskDto> responseEntity = taskController.updateTask(taskId, updatedTaskDto, userDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteTask_ShouldReturnOkResponse() {
        Long taskId = 1L;
        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());
        when(taskService.findById(taskId)).thenReturn(Optional.of(new Task()));

        ResponseEntity<String> responseEntity = taskController.deleteTask(taskId, userDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
