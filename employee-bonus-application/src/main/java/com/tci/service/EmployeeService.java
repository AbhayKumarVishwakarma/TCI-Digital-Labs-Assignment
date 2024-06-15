package com.tci.service;

import com.tci.model.dto.EmployeePostDTO;
import com.tci.model.dto.ResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    public ResponseDTO findEligibleEmployees(LocalDate date);


    public String saveEmployeeDetails(List<EmployeePostDTO> employeePostDTOs);


}
