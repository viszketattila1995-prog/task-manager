package com.attila.taskmanager.controller;

import com.attila.taskmanager.dto.request.CreateTaskCommand;
import com.attila.taskmanager.dto.response.TaskListItem;
import com.attila.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateTaskCommand createTaskCommand) {
        taskService.createNewTask(createTaskCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskListItem>> getAllTask() {
        List<TaskListItem> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }
}
