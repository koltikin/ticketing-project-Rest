package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager"})
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
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO projectDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .success(true)
                        .message("project is successfully created")
                        .code(HttpStatus.CREATED.value())
                        .data(projectService.save(projectDTO)).build()
        );
    }

    @PutMapping()
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project){
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("project is successfully updated",HttpStatus.OK));
    }

    @DeleteMapping ("/projectCode")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String code){
        projectService.delete(code);
        return ResponseEntity.ok(new ResponseWrapper("project is successfully deleted",HttpStatus.OK));
    }

    @GetMapping("/manager/project-status")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjectByManager() {
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("projects are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(projectService.listAllProjectDetails()).build()
        );
    }
    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String code) {
        projectService.complete(code);
        return ResponseEntity.ok(new ResponseWrapper("project is successfully completed",HttpStatus.OK));
    }
}
