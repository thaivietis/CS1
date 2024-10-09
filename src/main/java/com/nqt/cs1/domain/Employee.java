package com.nqt.cs1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String employeeId;
    private String fullName;
    private Boolean gender;
    private String avatar;
    private LocalDate dateOfBirth;
    private Double salary;
    private int level;
    private String email;
    private String phone;
    private String description;
    private String passWord;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}