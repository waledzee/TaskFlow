package com.waled.TaskFlow.Repository;

import com.waled.TaskFlow.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByUsername(String username);


}
