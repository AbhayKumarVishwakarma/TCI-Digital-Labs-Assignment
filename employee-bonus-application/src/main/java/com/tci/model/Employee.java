package com.tci.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String empName;
    private Integer amount;
    private String currency;

//    @JsonFormat(pattern = "MMM-dd-yyyy")
    private LocalDate joiningDate;

//    @JsonFormat(pattern = "MMM-dd-yyyy")
    private LocalDate exitDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", joiningDate=" + joiningDate +
                ", exitDate=" + exitDate +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

}

