package com.nqt.cs1.service;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import com.nqt.cs1.repository.EmployeeRepository;
import com.nqt.cs1.service.imp.EmployeeServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImp employeeServiceImp;

    @BeforeEach
    @Test
    void testFindById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFullName("Nguyen Quang Thai");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        Employee employeeList1 = this.employeeServiceImp.findById(1);
        assertEquals(1, employeeList1.getId());
    }

    @Test
    void saveEmployee_shouldSaveAndReturnEmployee() {
        Employee employee = new Employee();
        employee.setFullName("Nguyen Quang Thai");
        employee.setEmail("thainq@vietis.com");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeServiceImp.saveEmployee(employee);

        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getFullName(), result.getFullName());
    }

    @Test
    void deleteEmployee_shouldDeleteEmployeeById() {
        int employeeId = 1;

        employeeServiceImp.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void findByEmployeeId_shouldReturnEmployee_whenEmployeeIdExists() {
        Employee employee = new Employee();
        employee.setFullName("Nguyen Quang Thai");
        employee.setEmail("thainq@vietis.com");
        employee.setId(1);
        employee.setEmployeeId("2051063464");

        when(employeeRepository.findByEmployeeId("2051063464")).thenReturn(employee);

        Employee result = employeeServiceImp.findByEmployeeId("2051063464");

        Assertions.assertNotNull(result);
        assertEquals("2051063464", result.getEmployeeId());
    }

    @Test
    void findByEmployeeId_shouldReturnNull_whenEmployeeIdDoesNotExist() {
        when(employeeRepository.findByEmployeeId("A123")).thenReturn(null);

        Employee result = employeeServiceImp.findByEmployeeId("A123");

        Assertions.assertNull(result);
    }

    @Test
    void getEmployeeInfomation_shouldReturnListOfEmployeeInfomationDTO() {
        List<EmployeeInfomationDTO> employeeInfo = new ArrayList<>();
        EmployeeInfomationDTO employeeInfomation1 = new EmployeeInfomationDTO();
        employeeInfomation1.setFullName("Hoang Hoa Tham");
        employeeInfomation1.setPointAchievement(3);
        employeeInfomation1.setPointDisciplinary(2);
        employeeInfo.add(employeeInfomation1);
        EmployeeInfomationDTO employeeInfomation2 = new EmployeeInfomationDTO();
        employeeInfomation1.setFullName("Nguyen Minh Triet");
        employeeInfomation1.setPointAchievement(8);
        employeeInfomation1.setPointDisciplinary(2);
        employeeInfo.add(employeeInfomation2);
        when(employeeRepository.getEmployeeInfomation()).thenReturn(employeeInfo);
        List<EmployeeInfomationDTO> result = employeeServiceImp.getEmployeeInfomation();
        assertEquals(2, result.size());
        assertEquals("Nguyen Minh Triet", result.get(0).getFullName());
    }

    @Test
    void testFindAll(){
        List<Employee> employeeList = employeeRepository.findAll();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employeeList1 = this.employeeServiceImp.getAllEmployees();
        assertEquals(employeeList, employeeList1);
        verify(employeeRepository, times(2)).findAll();
    }

    @Test
    void testFailFindAll(){
        when(employeeRepository.findAll()).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeServiceImp.getAllEmployees();
        });
        assertEquals("No employees found.", exception.getMessage());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getAllEmployees_shouldThrowRuntimeException_whenDataAccessExceptionOccurs() {
        when(employeeRepository.findAll()).thenThrow(new DataAccessException("Database error") {});
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeServiceImp.getAllEmployees();
        });
        assertEquals("Error accessing the database: Database error", exception.getMessage());
    }
}
