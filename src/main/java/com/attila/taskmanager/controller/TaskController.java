package com.attila.taskmanager.controller;

import com.attila.taskmanager.dto.request.CreateTaskCommand;
import com.attila.taskmanager.dto.request.UpdateTaskCommand;
import com.attila.taskmanager.dto.response.TaskListItem;
import com.attila.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateTaskCommand createTaskCommand, Authentication authentication) {
        Long entityId = taskService.createNewTask(createTaskCommand, authentication.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entityId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<TaskListItem>> getAllTask(Authentication authentication) {
        List<TaskListItem> tasks = taskService.getAllTask(authentication.getName());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<TaskListItem> getTaskById(@PathVariable Long id, Authentication authentication) {
        TaskListItem item = taskService.getItemById(id, authentication.getName());
        return ResponseEntity.ok(item);
    }

    @PatchMapping("/{id:\\d+}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskCommand updateTaskCommand, Authentication authentication) {
        taskService.updateTask(id, updateTaskCommand, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Authentication authentication) {
        taskService.deleteTask(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

}
