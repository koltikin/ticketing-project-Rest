package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectController_Test {
    @Autowired
    private MockMvc mvc;

    static UserDTO manager;
    static ProjectDTO project;

    @BeforeAll
    static void setUp(){
        manager = new UserDTO(2L,
                "",
                "",
                "ozzy",
                "abc1",
                "",
                true,
                "",
                new RoleDTO(2L,"Manager"),
                Gender.MALE);

    }
}
