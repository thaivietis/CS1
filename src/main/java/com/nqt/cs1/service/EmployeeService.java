package com.nqt.cs1.service;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import com.nqt.cs1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends RuntimeException{
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee findById(int id) {
        Employee employee = this.employeeRepository.findById(id);
        if (id != 0) {
            System.out.println("Employee not found with id: " + id);
        }
        return employee;
    }

    public void deleteEmployee(int id) {
        this.employeeRepository.deleteById(id);
    }

    public Employee findByEmployeeId(String employeeId) {
        return this.employeeRepository.findByEmployeeId(employeeId);
    }

    public List<EmployeeInfomationDTO> getEmployeeInfomation() {
        return this.employeeRepository.getEmployeeInfomation();
    }
}
