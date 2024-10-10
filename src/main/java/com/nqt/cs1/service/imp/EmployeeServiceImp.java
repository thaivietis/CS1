package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import com.nqt.cs1.repository.EmployeeRepository;
import com.nqt.cs1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();

            // Kiểm tra nếu danh sách trả về bị null
            if (employees == null) {
                throw new RuntimeException("No employees found.");
            }

            return employees;
        } catch (DataAccessException e) {
            // Ném lại ngoại lệ truy vấn cơ sở dữ liệu
            throw new RuntimeException("Error accessing the database: " + e.getMessage(), e);
        } catch (Exception e) {
            // Ném lại các ngoại lệ chung khác
            throw new RuntimeException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tồn tại id = " + id + " cần tìm kiếm"));
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
