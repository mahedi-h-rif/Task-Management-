package com.project.Task.Managment.repository;

import com.project.Task.Managment.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
    boolean existsByName(String name);

    boolean existsByEmail(String email);

}
