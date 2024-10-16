package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/department")
    public String getDepartment(Model model) {
        List<Department> departmentList = this.departmentService.getAll();
        model.addAttribute("departments", departmentList);
        return "department/show";
    }

    @GetMapping("/department/create")
    public String createDepartment(Model model){
        model.addAttribute("newDepartment", new Department());
        return "department/create";
    }

    @PostMapping("/department/create")
    public String postCreateDepartment(@ModelAttribute("newDepartment") @Valid Department newDepartment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "department/create";
        }
        this.departmentService.saveDepartment(newDepartment);
        return "redirect:/department";
    }

    @GetMapping("/department/update/{id}")
    public String updateDepartment(Model model, @PathVariable("id") int id){
        Department department = this.departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department/update";
    }

    @PostMapping("/department/update")
    public String postUpdateDepartment(@ModelAttribute("department") @Valid Department newDepartment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "department/update";
        }
        Department department = this.departmentService.getDepartmentById(newDepartment.getId());
        department.setId(newDepartment.getId());
        department.setName(newDepartment.getName());
        department.setDepertmentId(newDepartment.getDepertmentId());
        this.departmentService.saveDepartment(department);
        return "redirect:/department";
    }

    @GetMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int id){
        this.departmentService.deleteById(id);
        return "redirect:/department";
    }
}
