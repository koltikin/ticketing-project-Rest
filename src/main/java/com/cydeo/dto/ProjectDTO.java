package com.cydeo.dto;
import com.cydeo.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotBlank
    private String projectName;
    @NotBlank
    private String projectCode;
    @NotNull
    private UserDTO assignedManager;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate, projectEndDate;
    @NotBlank(message = "enter project detail")
    private String projectDetail;
    private Status projectStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int unfinishedCount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int completedCount;

    public ProjectDTO(String projectName, String projectCode, UserDTO projectManager, LocalDate projectStartDate, LocalDate projectEndDate, String projectDetail, Status projectStatus) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.assignedManager = projectManager;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectDetail = projectDetail;
        this.projectStatus = projectStatus;
    }
}
