package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getTasks(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Tasks are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(taskService.listAllTasks()).build()
        );
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable("taskId") Long id){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(taskService.findById(id)).build()
        );
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO){
        taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully created")
                        .code(HttpStatus.OK.value()).build()
        );
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long id){
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully deleted")
                        .code(HttpStatus.CREATED.value()).build()
        );
    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully updated")
                        .code(HttpStatus.ACCEPTED.value()).build()
        );

    }
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){}
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(){}
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks(){}

}
