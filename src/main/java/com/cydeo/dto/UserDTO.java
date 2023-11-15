package com.cydeo.dto;

import com.cydeo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;

    @NotBlank
    @Size(max = 20,min = 2)
    private String firstName,lastName;

    @NotBlank(message = "email is required.")
    @Email(message = "please enter a valid email.")
    private String userName;

    private String OldPassWord;

    @NotBlank(message = "Password is required.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password does not meet the requirements.")
    private String passWord;

    private String passWordConfirm;

    @NotNull(message = "User status is required.")
    private boolean enabled;

    @NotBlank(message = "phone number cannot be blank.")
    @Pattern(regexp = "\\d{11}", message = "please enter a valid phone number.")
    private String phone;

    @NotNull(message = "role is required, please choose a role.")
    private RoleDTO role;

    @NotNull(message = "gender is required please choose a gender.")
    private Gender gender;


}
