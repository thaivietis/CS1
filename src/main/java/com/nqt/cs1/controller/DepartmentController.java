package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/create")
    public String CreateDepartment(){
        return "department/create";
    }

    @PostMapping("/department/create")
    public String PostCreateDepartment(@ModelAttribute @Valid Department newDepartment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "department/create";
        }
        this.departmentService.saveDepartment(newDepartment);
        return "redirect:/department";
    }

    @GetMapping("/department/update/{id}")
    public String UpdateDepartment(Model model, @PathVariable("id") int id){
        Department department = this.departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department/update";
    }

    @PostMapping("/department/update")
    public String PostUpdateDepartment(@ModelAttribute Department newDepartment){
        Department department = this.departmentService.getDepartmentById(newDepartment.getId());
        department.setId(newDepartment.getId());
        department.setName(newDepartment.getName());
        department.setDepertmentId(newDepartment.getDepertmentId());
        this.departmentService.saveDepartment(department);
        return "redirect:/department";
    }

    @GetMapping("/department/delete/{id}")
    public String DeleteDepartment(@PathVariable("id") int id){
        this.departmentService.deleteById(id);
        return "department/delete";
    }
}
