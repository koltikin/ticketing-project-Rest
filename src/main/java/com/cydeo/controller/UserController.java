package com.cydeo.controller;

import com.cydeo.annotation.ExecutionTime;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.exception.TicketingProjectException;
import com.cydeo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;
    @GetMapping
    @RolesAllowed({"Manager","Admin"})
    @Operation(summary = "get all the users")
    @ExecutionTime
    public ResponseEntity<ResponseWrapper> getAllUsers(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("users are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(userService.listAllUsers())
                        .success(true).build()
        );
    }
    @GetMapping("{username}")
    @RolesAllowed({"Admin"})
    @Operation(summary = "get user by user name")
    public ResponseEntity<ResponseWrapper> getUserByUserName(@PathVariable("username") String username){
        UserDTO userDTO = userService.findByUserName(username);
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("user is successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(userDTO)
                        .success(true).build()
        );

    }
    @PostMapping
    @RolesAllowed({"Admin"})
    @Operation(summary = "create user")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .message("user is successfully created")
                        .code(HttpStatus.CREATED.value())
                        .success(true).build()
        );

    }
    @PutMapping
    @RolesAllowed({"Admin"})
    @Operation(summary = "update user")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("user is successfully updated")
                        .code(HttpStatus.CREATED.value())
                        .success(true).build()
        );
    }
    @RolesAllowed({"Admin"})
    @DeleteMapping("{username}")
    @Operation(summary = "delete user")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String username) throws TicketingProjectException {
        userService.delete(username);
        return ResponseEntity.ok(new ResponseWrapper("user is successfully deleted",HttpStatus.OK));
    }
}
