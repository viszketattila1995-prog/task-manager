package com.attila.taskmanager.repository;

import com.attila.taskmanager.domain.AppUser;
import com.attila.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByNameAndAppUser(String name, AppUser appUser);

    List<Task> findAllByAppUser(AppUser appUser);

}
