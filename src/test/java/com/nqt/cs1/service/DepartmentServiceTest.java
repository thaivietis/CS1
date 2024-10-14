package com.nqt.cs1.service;


import com.nqt.cs1.domain.Department;
import com.nqt.cs1.dto.DepartmentInformationDTO;
import com.nqt.cs1.repository.DepartmentRepository;
import com.nqt.cs1.service.imp.DepartmentServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentServiceImp departmentServiceImp;

    @Test
    void testDepartmentById (){
        Department department = new Department();
        department.setId(1);
        department.setName("San xuat 1");
        when(departmentRepository.findById(1)).thenReturn(department);
        Department result = departmentServiceImp.getDepartmentById(1);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetDepartmentAll(){
        List<Department> departments = new ArrayList<>();
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> results = this.departmentServiceImp.getAll();
        assertEquals(departments, results);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testSaveDepartment (){
        Department department = new Department();
        department.setName("San xuat 1");
        department.setId(1);
        department.setDepertmentId("SX1");
        when(departmentRepository.save(department)).thenReturn(department);
        Department result = departmentServiceImp.saveDepartment(department);
        assertEquals(department, result);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testUpdateDepartment (){
        Department department = new Department();
        department.setName("San xuat 1");
        department.setId(1);
        department.setDepertmentId("SX1");
        when(departmentRepository.findById(1)).thenReturn(department);
        department.setName("San xuat 2");
        department.setDepertmentId("SX2");
        when(departmentRepository.save(department)).thenReturn(department);
        Department result = departmentServiceImp.getDepartmentById(1);
        Department updateResult = departmentServiceImp.saveDepartment(department);
        assertEquals(department, result);
        assertEquals(department, updateResult);
        verify(departmentRepository, times(1)).findById(1);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testDeleteDepartment (){
        int id = 1;
        departmentServiceImp.deleteById(1);
        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetDepartmentInformation(){
        DepartmentInformationDTO dept1 = new DepartmentInformationDTO("San xuat 1", 10, 2, 85);
        DepartmentInformationDTO dept2 = new DepartmentInformationDTO("San xuat 2", 15, 3, 90);
        List<DepartmentInformationDTO> mockDepartmentList = Arrays.asList(dept1, dept2);
        when(departmentRepository.getDepartmentInfomation()).thenReturn(mockDepartmentList);
        List<DepartmentInformationDTO> result = departmentServiceImp.getDepartmentInfomation();
        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).getDepartmentInfomation();
    }
}
