package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    public ResponseEntity<ResponseWrapper> getTasks(){}
    public ResponseEntity<ResponseWrapper> getTaskById(){}
    public ResponseEntity<ResponseWrapper> createTask(){}
    public ResponseEntity<ResponseWrapper> deleteTask(){}
    public ResponseEntity<ResponseWrapper> updateTask(){}
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){}
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(){}
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks(){}

}
