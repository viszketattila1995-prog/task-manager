package com.attila.taskmanager.controller;

import com.attila.taskmanager.dto.request.CreateTaskCommand;
import com.attila.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
