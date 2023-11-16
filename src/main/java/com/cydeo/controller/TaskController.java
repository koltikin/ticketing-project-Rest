package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        .message("Tasks are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(taskService.findById(id)).build()
        );
    }
    public ResponseEntity<ResponseWrapper> createTask(){}
    public ResponseEntity<ResponseWrapper> deleteTask(){}
    public ResponseEntity<ResponseWrapper> updateTask(){}
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){}
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(){}
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks(){}

}
