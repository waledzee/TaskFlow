package com.waled.TaskFlow.Repository;


import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Override
    Page<Task> findAll(Pageable pageable);

    List <Task> findByStatus(TaskStatus status);
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    Task updateTaskStatus(@Param("id") Long id, @Param("status") TaskStatus status);
}
