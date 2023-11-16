package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getProjects(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("projects are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(projectService.listAllProjects()).build()
        );
    }
    @GetMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectCode") String code){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("project is successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(projectService.getByProjectCode(code)).build()
        );
    }

    @PostMapping()
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO projectDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("project is successfully created")
                        .code(HttpStatus.CREATED.value())
                        .data(projectService.save(projectDTO)).build()
        );
    }
}
