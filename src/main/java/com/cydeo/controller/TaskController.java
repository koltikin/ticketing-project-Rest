package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task",description = "Task API")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @RolesAllowed({"Manager"})
    @Operation(summary = "get all tasks")
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
    @Operation(summary = "get task by id")
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
    @Operation(summary = "create task")
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
    @Operation(summary = "delete task by id")
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
    @Operation(summary = "update task")
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
    @Operation(summary = "get all pending tasks")
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
    @Operation(summary = "update task status")
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
    @Operation(summary = "get all completed tasks")
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
