package com.nqt.cs1.controller;


import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.service.EmployeeService;
import com.nqt.cs1.service.InfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InfomationController {
    @Autowired
    private InfomationService infomationService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/infomation/create")
    public String CreateInfomation(Model model){
        return "infomation/find";
    }

    @PostMapping("/infomation/find")
    public String PostCreateInfomation(@RequestParam("employeeId") String employeeId, Model model){
        Employee employee = this.employeeService.findByEmployeeId(employeeId);
        model.addAttribute("newEmployee", employee);
        return "infomation/create";
    }

    @PostMapping("/infomation/create")
    public String PostSaveInformation(@ModelAttribute Infomation information, @RequestParam("employeeId") String employeeId){
        Employee employee = this.employeeService.findByEmployeeId(employeeId);
        Infomation infomation1 = new Infomation();
        infomation1.setReason(information.getReason());
        infomation1.setType(information.getType());
        infomation1.setDate(information.getDate());
        infomation1.setEmployee(employee);
        this.infomationService.saveInformation(infomation1);
        return "redirect:/infomation";
    }

    @GetMapping("/infomation/update/{id}")
    public String updateInformation(Model model, @PathVariable("id") int id){
        Infomation infomation = this.infomationService.getById(id);
        String rewardType = infomation.getType();
        model.addAttribute("information", infomation);
        model.addAttribute("rewardType", rewardType);
        return "infomation/update";
    }

    @PostMapping("/infomation/update")
    public String PostUpdateInformation(@ModelAttribute Infomation newInformation){
        Infomation information = this.infomationService.getById(newInformation.getId());
        information.setId(newInformation.getId());
        information.setType(newInformation.getType());
        information.setDate(newInformation.getDate());
        information.setReason(newInformation.getReason());
        this.infomationService.saveInformation(information);
        return "redirect:/infomation";
    }

    @GetMapping("/infomation/delete/{id}")
    public String DeleteEmployee(@PathVariable("id") int id,Model model){
        Infomation infomation = this.infomationService.findById(id);
        model.addAttribute("infomation", infomation);
        return "infomation/delete";
    }

    @PostMapping("/infomation/delete")
    public String PostDeleteEmployee(@ModelAttribute Infomation infomation){
        this.infomationService.deleteById(infomation.getId());
        return "redirect:/infomation";
    }
}
