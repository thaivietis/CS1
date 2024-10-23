package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.dto.DepartmentInformationDTO;
import com.nqt.cs1.repository.DepartmentRepository;
import com.nqt.cs1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentById(int id){
        return this.departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department){
        return this.departmentRepository.save(department);
    }

    public List<Department> getAll(){
        return this.departmentRepository.findAll();
    }

    public void updateDepartment(Department department){
        Department currentDepartment = this.getDepartmentById(department.getId());
        currentDepartment.setId(department.getId());
        currentDepartment.setName(department.getName());
        currentDepartment.setDepertmentId(department.getDepertmentId());
        this.saveDepartment(department);
    }

    public void deleteById(int id){
        this.departmentRepository.deleteById(id);
    }

    public List<DepartmentInformationDTO> getDepartmentInfomation(){
        return this.departmentRepository.getDepartmentInfomation();
    }
}
