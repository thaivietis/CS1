package com.nqt.cs1.service;

import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testFindById(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFullName("Nguyen Quang Thai");
        when(employeeRepository.findById(1)).thenReturn(employee);
        Employee employeeList1 = this.employeeService.findById(1);
        assertEquals(1, employeeList1.getId());

//        verify(employeeRepository, times(1)).findAll();
    }
}
