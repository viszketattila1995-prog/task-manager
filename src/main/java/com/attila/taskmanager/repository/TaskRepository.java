package com.attila.taskmanager.repository;

import com.attila.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByName(String name);

}
