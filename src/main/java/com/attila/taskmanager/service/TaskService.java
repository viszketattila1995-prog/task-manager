package com.attila.taskmanager.service;

import com.attila.taskmanager.domain.AppUser;
import com.attila.taskmanager.domain.Task;
import com.attila.taskmanager.dto.request.CreateTaskCommand;
import com.attila.taskmanager.dto.request.UpdateTaskCommand;
import com.attila.taskmanager.dto.response.TaskListItem;
import com.attila.taskmanager.exception.TaskAlreadyExistsException;
import com.attila.taskmanager.exception.TaskNotFoundException;
import com.attila.taskmanager.repository.TaskRepository;
import com.attila.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public Long createNewTask(CreateTaskCommand createTaskCommand, String username) {
        if (taskRepository.existsByName(createTaskCommand.getName())) {
            throw new TaskAlreadyExistsException("TaskService already exists");
        }

        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Task task = new Task();
        task.setName(createTaskCommand.getName());
        task.setDescription(createTaskCommand.getDescription());
        task.setAppUser(appUser);
        log.info("New task created");
        return taskRepository.save(task).getId();
    }

    @Transactional(readOnly = true)
    public List<TaskListItem> getAllTask(String username) {

        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));

        log.info("Get all tasks");
        return taskRepository.findAllByAppUser(appUser)
                .stream()
                .map(task -> new TaskListItem(task.getId(), task.getName(), task.getDescription(), task.isCompleted()))
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskListItem getItemById(Long id, String username) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("TaskService not found with id: " + id));

        if (!task.getAppUser().getUsername().equals(username)) {
            throw new TaskNotFoundException("TaskService not found with id: " + id);
        }
        return new TaskListItem(task.getId(), task.getName(), task.getDescription(), task.isCompleted());
    }

    public void updateTask(Long id, UpdateTaskCommand updateTaskCommand, String username) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("TaskService not found with id: " + id));

        if (!task.getAppUser().getUsername().equals(username)) {
            throw new TaskNotFoundException("TaskService not found with id: " + id);
        }

        if (updateTaskCommand.getName() != null) {
            task.setName(updateTaskCommand.getName());
        }
        if (updateTaskCommand.getDescription() != null) {
            task.setDescription(updateTaskCommand.getDescription());
        }
        if (updateTaskCommand.getCompleted() != null) {
            task.setCompleted(updateTaskCommand.getCompleted());
        }

        taskRepository.save(task);
    }

    public void deleteTask(Long id, String username) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("TaskService not found with id: " + id));

        if (!task.getAppUser().getUsername().equals(username)) {
            throw new TaskNotFoundException("TaskService not found with id: " + id);
        }

        taskRepository.delete(task);
    }
}
