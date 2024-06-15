package com.tci.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePostDTO {

    private String empName;
    private String department;
    private Integer amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
}
