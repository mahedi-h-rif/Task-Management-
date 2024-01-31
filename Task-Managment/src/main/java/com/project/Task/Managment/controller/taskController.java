package com.project.Task.Managment.controller;

import com.project.Task.Managment.DTO.TaskDto;
import com.project.Task.Managment.entity.Task;
import com.project.Task.Managment.entity.UserInfo;
import com.project.Task.Managment.service.UserInfoService;
import com.project.Task.Managment.service.taskService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class taskController {

    private final taskService  taskService;
    private final UserInfoService userInfoService;

    public taskController(com.project.Task.Managment.service.taskService taskService, UserInfoService userInfoService) {
        this.taskService = taskService;
        this.userInfoService = userInfoService;
    }

    @PostMapping("/createTask")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<TaskDto> addTasks(@RequestBody @Valid Task task,
                                            @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<UserInfo> userInfo = userInfoService.findUser(userDetails.getUsername());

        taskService.saveTask(task);
        task.setUserInfo(userInfo.get());
        TaskDto taskDto = new TaskDto(task.getId(),task.getTitle(),
                task.getDescription(),task.getStatus());
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }


    @GetMapping("/tasks")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<TaskDto>> getAllTaskForUser(
            @AuthenticationPrincipal UserDetails userDetails){

        if(userDetails == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Task> tasks = taskService.findByUserName(userDetails.getUsername());

        List<TaskDto> taskDtos = tasks.stream()
                .map(task -> new TaskDto(task.getId(),task.getTitle(),
                        task.getDescription(), task.getStatus()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(taskDtos);
    }


    @Transactional
    @PutMapping("/updateTask/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto updatedTaskDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Task> optionalTask = taskService.findById(id);

        Task existingTask = optionalTask.get();

        String userEmail = userDetails.getUsername();

        BeanUtils.copyProperties(updatedTaskDto, existingTask, "id", "userInfo");

        Task updatedTask = taskService.saveTask(existingTask);

        TaskDto updatedTaskDtoResponse = new TaskDto(updatedTask.getId(),updatedTask.getTitle(),
                updatedTask.getDescription(), updatedTask.getStatus());

        return ResponseEntity.ok(updatedTaskDtoResponse);
    }


    @DeleteMapping("deleteTask/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<Task> optionalTask = taskService.findById(id);
        Task existingTask = optionalTask.get();

        taskService.delete(existingTask);
        return ResponseEntity.ok("Task deleted successfully");

    }


}
