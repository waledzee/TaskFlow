package com.waled.TaskFlow.Repository;


import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List <Task> findByStatus(TaskStatus status);
}
