package com.project.Task.Managment.repository;

import com.project.Task.Managment.DTO.TaskDto;
import com.project.Task.Managment.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface taskRepository extends JpaRepository<Task,Long> {

    Page<TaskDto> findAllProjectedBy(Pageable pageable);
    List<Task> findByUserInfoName(String username);
}
