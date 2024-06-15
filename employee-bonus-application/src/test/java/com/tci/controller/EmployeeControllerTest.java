package com.tci.controller;

import com.tci.model.dto.CurrencyEmployeeDTO;
import com.tci.model.dto.EmployeeDTO;
import com.tci.model.dto.EmployeePostDTO;
import com.tci.model.dto.ResponseDTO;
import com.tci.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Saving employee details should return success message")
    public void testSaveEmployeeHandler() {

        // Arrange
        EmployeePostDTO employee1 = new EmployeePostDTO("raj singh", "accounts", 5000, "INR", "may-20-2022", "may-20-2023");
        EmployeePostDTO employee2 = new EmployeePostDTO("pratap m", "accounts", 3000, "INR", "jan-01-2021", "may-20-2023");
        List<EmployeePostDTO> employees = Arrays.asList(employee1, employee2);

        Map<String, List<EmployeePostDTO>> request = new HashMap<>();
        request.put("employees", employees);

        when(employeeService.saveEmployeeDetails(anyList())).thenReturn("Data saved successfully");

        // Act
        ResponseEntity<String> response = employeeController.addEmployee(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not 200");
        assertEquals("Data saved successfully", response.getBody(), "Response message is not as expected");
        verify(employeeService, times(1)).saveEmployeeDetails(anyList());

    }

    @Test
    @DisplayName("Eligible employees are returned for the given date")
    public void testGetEligibleEmployees() {

        // Arrange
        String date = "may-27-2022";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMM-dd-yyyy").toFormatter(Locale.ENGLISH);
        LocalDate filterDate = LocalDate.parse(date, formatter);

        EmployeeDTO employee1 = new EmployeeDTO("pratap m", 3000, "INR");
        EmployeeDTO employee2 = new EmployeeDTO("raj singh", 5000, "INR");
        EmployeeDTO employee3 = new EmployeeDTO("sam", 2500, "USD");
        EmployeeDTO employee4 = new EmployeeDTO("susan", 700, "USD");

        CurrencyEmployeeDTO inrCurrency = new CurrencyEmployeeDTO("INR", Arrays.asList(employee1, employee2));
        CurrencyEmployeeDTO usdCurrency = new CurrencyEmployeeDTO("USD", Arrays.asList(employee3, employee4));

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setErrorMessage("");
        responseDTO.setData(Arrays.asList(inrCurrency, usdCurrency));

        when(employeeService.findEligibleEmployees(filterDate)).thenReturn(responseDTO);

        // Act
        ResponseEntity<ResponseDTO> response = employeeController.getEmployeeBonus(date);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code is not 200");
        assertEquals(responseDTO, response.getBody(), "Response body is not as expected");
        verify(employeeService, times(1)).findEligibleEmployees(filterDate);

    }
}
