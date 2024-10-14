package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Department;
import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.domain.User;
import com.nqt.cs1.dto.DepartmentInformationDTO;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import com.nqt.cs1.service.DepartmentService;
import com.nqt.cs1.service.EmployeeService;
import com.nqt.cs1.service.InfomationService;
import com.nqt.cs1.service.imp.DepartmentServiceImp;
import com.nqt.cs1.service.imp.EmployeeServiceImp;
import com.nqt.cs1.service.imp.InfomationServiceImp;
import com.nqt.cs1.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomepageController {
    @Autowired
    private EmployeeServiceImp employeeService;

    @Autowired
    private DepartmentServiceImp departmentService;

    @Autowired
    private InfomationServiceImp infomationService;

    @Autowired
    private UserServiceImp userService;

    @GetMapping(value = "/")
    public String getClien(Model model) {
        List<EmployeeInfomationDTO> employeeInfomation = this.employeeService.getEmployeeInfomation();
        List<EmployeeInfomationDTO> ei = employeeInfomation.stream().sorted(Comparator.comparing(EmployeeInfomationDTO::getTotal).reversed()).limit(10).collect(Collectors.toList());
        model.addAttribute("employeeInfomation", ei);
        return "index";
    }

    @GetMapping(value = "/admin")
    public String getReportUSer(Model model) {
        List<EmployeeInfomationDTO> employeeInfomation = this.employeeService.getEmployeeInfomation();
        model.addAttribute("employeeInfomation", employeeInfomation);
        return "admin/dashboard";
    }

    @GetMapping(value = "/admin/department")
    public String getReportDepartment(Model model) {
        List<DepartmentInformationDTO> departmentInformation = this.departmentService.getDepartmentInfomation();
        model.addAttribute("departmentInformation", departmentInformation);
        return "admin/department";
    }

    @GetMapping(value = "/user")
    public String getUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/show";
    }

    @GetMapping(value = "/employee")
    public String getEmployee(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee/show";
    }

    @GetMapping(value = "/department")
    public String getDepartment(Model model) {
        List<Department> departmentList = this.departmentService.getAll();
        model.addAttribute("departments", departmentList);
        return "department/show";
    }

    @GetMapping(value = "/infomation")
    public String getInfomation(Model model) {
        List<Infomation> informationList = this.infomationService.getAll();
        model.addAttribute("informations", informationList);
        return "infomation/show";
    }

    @GetMapping(value = "/login")
    public String getLogin(){
        return "auth/login";
    }

    @GetMapping(value = "/erorr")
    public String postLogin(){
        return "auth/erorr";
    }

    @GetMapping(value = "/send-mail")
    public String sendMail(){
        return "mail/sendMail";
    }

    @GetMapping(value = "/send")
    public String send(){
        return "mail/send";
    }
}
