package com.project.Task.Managment.repository;

import com.project.Task.Managment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface taskRepository extends JpaRepository<Task,Long> {

}
