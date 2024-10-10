package com.nqt.cs1.service;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.dto.EmployeeInfomationDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees() ;

    Employee saveEmployee(Employee employee);

    Employee findById(int id);

    void deleteEmployee(int id);

    Employee findByEmployeeId(String employeeId);

    List<EmployeeInfomationDTO> getEmployeeInfomation();
}
