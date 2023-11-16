package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping
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
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("user is successfully updated")
                        .code(HttpStatus.CREATED.value())
                        .success(true).build()
        );
    }
    @DeleteMapping("{username}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String username){
        userService.delete(username);
        return ResponseEntity.ok(new ResponseWrapper("user is successfully deleted",HttpStatus.OK));
    }
}
