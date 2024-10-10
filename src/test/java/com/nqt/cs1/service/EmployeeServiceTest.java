package com.nqt.cs1.service;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.repository.EmployeeRepository;
import com.nqt.cs1.service.imp.EmployeeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImp employeeServiceImp;

    @BeforeEach
    @Test
    public void testFindById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFullName("Nguyen Quang Thai");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        Employee employeeList1 = this.employeeServiceImp.findById(1);
        assertEquals(1, employeeList1.getId());
    }

    @Test
    public void testFindById_WhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(10)).thenThrow(RuntimeException.class);
        Employee employee = employeeRepository.findById(10).orElse(null);
        assertThrows(RuntimeException.class, () -> employeeServiceImp.findById(10));
    }

//    @Test
//    public void testFindAll(){
//        List<Employee> employeeList = employeeRepository.findAll();
//        when(employeeRepository.findAll()).thenReturn(employeeList);
//        List<Employee> employeeList1 = this.employeeServiceImp.getAllEmployees();
//        assertEquals(employeeList, employeeList1);
//        verify(employeeRepository, times(2)).findAll();
//    }
}
