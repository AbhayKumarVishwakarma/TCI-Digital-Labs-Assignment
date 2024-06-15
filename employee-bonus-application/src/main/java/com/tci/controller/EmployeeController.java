package com.tci.controller;

import com.tci.model.dto.EmployeePostDTO;
import com.tci.model.dto.ResponseDTO;
import com.tci.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/employee-bonus")
    public ResponseEntity<String> addEmployee(@RequestBody Map<String, List<EmployeePostDTO>> request) {
        List<EmployeePostDTO> employees = request.get("employees");
        String response = employeeService.saveEmployeeDetails(employees);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/employee-bonus")
    public ResponseEntity<ResponseDTO> getEmployeeBonus(@RequestParam("date") /* @DateTimeFormat(pattern = "MMM-dd-yyyy") LocalDate  filterDate */ String date) {

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMM-dd-yyyy");
        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
        LocalDate filterDate = LocalDate.parse(date, formatter);

        ResponseDTO response = employeeService.findEligibleEmployees(filterDate);

        return ResponseEntity.ok(response);
    }

}

