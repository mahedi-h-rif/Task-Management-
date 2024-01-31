package com.project.Task.Managment.repository;

import com.project.Task.Managment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface taskRepository extends JpaRepository<Task,Long> {


    List<Task> findByUserInfoName(String username);
}
