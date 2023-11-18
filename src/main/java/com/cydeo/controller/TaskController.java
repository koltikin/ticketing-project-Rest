package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully updated")
                        .code(HttpStatus.ACCEPTED.value()).build()
        );

    }
    @GetMapping("/employee/pending-tasks")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Tasks are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(taskService.listAllTasksByStatusIsNot(Status.COMPLETE)).build()
        );
    }
    @PutMapping("/employee/update")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Task is successfully updated")
                        .code(HttpStatus.OK.value()).build()
        );
    }
    @GetMapping("/employee/archive")
    @RolesAllowed({"Employee"})
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Tasks are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(taskService.listAllTasksByStatus(Status.COMPLETE)).build()
        );
    }

}
