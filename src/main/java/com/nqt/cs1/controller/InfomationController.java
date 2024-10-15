package com.nqt.cs1.controller;


import com.nqt.cs1.domain.Employee;
import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.service.EmployeeService;
import com.nqt.cs1.service.InfomationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class InfomationController {
    @Autowired
    private InfomationService infomationService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/infomation/create")
    public String createInformation(){
        return "infomation/find";
    }

    @PostMapping("/infomation/find")
    public String postCreateInformation(@RequestParam("employeeId") String employeeId, Model model){
        Employee employee = this.employeeService.findByEmployeeId(employeeId);
        model.addAttribute("newEmployee", employee);
        model.addAttribute("information", new Infomation());
        return "infomation/create";
    }

    @PostMapping("/infomation/create")
    public String postSaveInformation(@ModelAttribute("information") @Valid Infomation information,
                                      BindingResult bindingResult,
                                      @RequestParam("employeeId") String employeeId,
                                      Model model){
        Employee employee = this.employeeService.findByEmployeeId(employeeId);
        if(bindingResult.hasErrors()){
            model.addAttribute("newEmployee", employee);
            return "infomation/create";
        }
        Infomation information1 = new Infomation();
        information1.setReason(information.getReason());
        information1.setType(information.getType());
        information1.setDate(information.getDate());
        information1.setEmployee(employee);
        this.infomationService.saveInformation(information1);
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
    public String postUpdateInformation(@ModelAttribute("information") @Valid Infomation newInformation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "infomation/update";
        }
        Infomation information = this.infomationService.getById(newInformation.getId());
        information.setId(newInformation.getId());
        information.setType(newInformation.getType());
        information.setDate(newInformation.getDate());
        information.setReason(newInformation.getReason());
        this.infomationService.saveInformation(information);
        return "redirect:/infomation";
    }

    @GetMapping("/infomation/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id,Model model){
        Infomation infomation = this.infomationService.findById(id);
        model.addAttribute("infomation", infomation);
        return "infomation/delete";
    }

    @PostMapping("/infomation/delete")
    public String postDeleteEmployee(@ModelAttribute Infomation infomation){
        this.infomationService.deleteById(infomation.getId());
        return "redirect:/infomation";
    }
}
