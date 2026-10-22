package com.example.taskadministration.controller;


import com.example.taskadministration.dto.request.TaskRequest;
import com.example.taskadministration.dto.response.TaskResponse;
import com.example.taskadministration.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskAdminService){
        this.taskService = taskAdminService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
            TaskResponse taskResponse = this.taskService.createTask(taskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping("/getAllTask")
    public ResponseEntity<List<TaskResponse>> getAllTask(){
        return ResponseEntity.ok(this.taskService.getAllTask());
    }

    @GetMapping("/getTask")
    public ResponseEntity<TaskResponse> getById(@RequestParam(required = true, name = "value") Long taskValue){
        return ResponseEntity.ok(this.taskService.getTaskById(taskValue));
    }

    @PutMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@Valid @RequestBody TaskRequest taskRequest)
    {
        return ResponseEntity.ok(this.taskService.updateTask(taskRequest));
    }
}
