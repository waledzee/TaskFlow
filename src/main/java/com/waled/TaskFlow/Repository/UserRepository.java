package com.waled.TaskFlow.Repository;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
