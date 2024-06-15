package com.tci.service;

import com.tci.exception.NotFoundException;
import com.tci.model.Department;
import com.tci.model.Employee;
import com.tci.model.dto.CurrencyEmployeeDTO;
import com.tci.model.dto.EmployeeDTO;
import com.tci.model.dto.EmployeePostDTO;
import com.tci.model.dto.ResponseDTO;
import com.tci.repository.DepartmentRepository;
import com.tci.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseDTO findEligibleEmployees(LocalDate date) {
        List<Employee> employees = employeeRepository.findEmployeesForBonus(date);

        if (employees.isEmpty()) {
            throw new NotFoundException("No employee eligible for bonus");
        }

        Map<String, List<EmployeeDTO>> groupedByCurrency = employees.stream()
                .map(e -> new EmployeeDTO(e.getEmpName(), e.getAmount(), e.getCurrency()))
                .collect(Collectors.groupingBy(EmployeeDTO::getCurrency));

        List<CurrencyEmployeeDTO> currencyEmployeeDTOs = groupedByCurrency.entrySet().stream()
                .map(entry -> new CurrencyEmployeeDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new ResponseDTO("", currencyEmployeeDTOs);
    }

    @Override
    public String saveEmployeeDetails(List<EmployeePostDTO> employeePostDTOs) {

        List<Employee> employeeList = employeePostDTOs.stream().map(employeePostDTO -> {

            LocalDate joiningDate = dateConverter(employeePostDTO.getJoiningDate());
            LocalDate exitDate = dateConverter(employeePostDTO.getExitDate());

            Employee employee = new Employee();
            employee.setEmpName(employeePostDTO.getEmpName());
            employee.setJoiningDate(joiningDate);
            employee.setExitDate(exitDate);
            employee.setAmount(employeePostDTO.getAmount());
            employee.setCurrency(employeePostDTO.getCurrency());

            Department department = departmentRepository.findByName(employeePostDTO.getDepartment());
            if (department == null) {
                department = createDepartment(employeePostDTO.getDepartment());
            }
            employee.setDepartment(department);
            department.getEmployees().add(employee);

            return employee;
        }).collect(Collectors.toList());

        List<Employee> savedEmployees = employeeRepository.saveAll(employeeList);

        return "Data saved successfully";
    }

    public LocalDate dateConverter(String date) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("MMM-dd-yyyy");
        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        department.setEmployees(new ArrayList<>());

        Department savedDepartment = departmentRepository.save(department);
        return savedDepartment;
    }
}
