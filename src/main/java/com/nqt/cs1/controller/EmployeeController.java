package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.service.DepartmentService;
import com.nqt.cs1.service.EmployeeService;
import com.nqt.cs1.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UploadService uploadService;

    @GetMapping("/employee/create")
    public String CreateEmployee(Model model){
        List<Department> departmentList = this.departmentService.getAll();
        model.addAttribute("departmentList", departmentList);
        return "employee/create";
    }

    @PostMapping("/employee/create")
    public String PostCreateEmployee(@ModelAttribute Employee employee, @RequestParam("gender") String gender,
                                     @RequestParam("avatarFile") MultipartFile file){
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        employee.setGender(checkGenDer(gender));
        employee.setAvatar(avatar);
        this.employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/detail/{id}")
    public String DetailEmployee(@PathVariable int id, Model model){
        Employee employee = this.employeeService.findById(id);
        String gender;
        if(employee.getGender()==true){
            gender = "Nam";
        }else gender = "Nữ";
        model.addAttribute("employee", employee);
        model.addAttribute("gender", gender);
        return "employee/detail";
    }

    @GetMapping("/employee/update/{id}")
    public String UpdateEmployee(@PathVariable int id, Model model){
        List<Department> departmentList = this.departmentService.getAll();
        Employee employee = this.employeeService.findById(id);
        String gender = null;
        if(employee.getGender() == true){
            gender = "1";
        }else{
            gender = "2";
        }
        model.addAttribute("employee", employee);
        model.addAttribute("gender1", gender);
        model.addAttribute("departmentList", departmentList);
        return "employee/update";
    }

    @PostMapping("/employee/update")
    public String PostUpdateEmployee(@ModelAttribute Employee employee, @RequestParam("gender") String gender
            , @RequestParam("avatarFile") MultipartFile file){
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        Employee employee1 = this.employeeService.findById(employee.getId());
        employee1.setId(employee.getId());
        employee1.setFullName(employee.getFullName());
        employee1.setAvatar(avatar);
        employee1.setEmployeeId(employee.getEmployeeId());
        employee1.setDescription(employee.getDescription());
        employee1.setEmail(employee.getEmail());
        employee1.setPhone(employee.getPhone());
        employee1.setLevel(employee.getLevel());
        employee1.setSalary(employee.getSalary());
        employee1.setDateOfBirth(employee.getDateOfBirth());
        employee1.setGender(checkGenDer(gender));
        employee1.setDepartment(employee.getDepartment());
        this.employeeService.saveEmployee(employee1);
        return "redirect:/employee";
    }

    @GetMapping("/employee/delete/{id}")
    public String DeleteEmployee(@PathVariable int id, Model model){
        Employee employee = this.employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee/delete";
    }

    @PostMapping("/employee/delete")
    public String PostDeleteEmployee(@ModelAttribute Employee employee){
        this.employeeService.deleteEmployee(employee.getId());
        return "redirect:/employee";
    }

    public boolean checkGenDer(String gender){
        if (gender.equals("1")){
            return true;
        }
        return false;
    }
}
