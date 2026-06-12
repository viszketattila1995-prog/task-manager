package com.attila.taskmanager.controller;

import com.attila.taskmanager.dto.request.CreateTaskCommand;
import com.attila.taskmanager.dto.request.UpdateTaskCommand;
import com.attila.taskmanager.dto.response.TaskListItem;
import com.attila.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateTaskCommand createTaskCommand) {
        Long entityId = taskService.createNewTask(createTaskCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entityId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<TaskListItem>> getAllTask() {
        List<TaskListItem> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<TaskListItem> getTaskById(@PathVariable Long id) {
        TaskListItem item = taskService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @PatchMapping("/{id:\\d+}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskCommand updateTaskCommand) {
        taskService.updateTask(id, updateTaskCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
