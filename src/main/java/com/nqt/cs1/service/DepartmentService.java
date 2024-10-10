package com.nqt.cs1.service;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.dto.DepartmentInformationDTO;
import com.nqt.cs1.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(int id);

    Department saveDepartment(Department department);

    List<Department> getAll();

    void deleteById(int id);

    List<DepartmentInformationDTO> getDepartmentInfomation();
}
