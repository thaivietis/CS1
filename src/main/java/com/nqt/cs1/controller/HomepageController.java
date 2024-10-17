package com.nqt.cs1.controller;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.dto.DepartmentInformationDTO;
import com.nqt.cs1.dto.EmployeeInfomationDTO;
import com.nqt.cs1.service.ResultService;
import com.nqt.cs1.service.imp.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private ResultServiceImp resultService;

    @Autowired
    private KeywordServiceImp keywordService;

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

    @GetMapping(value = "/search")
    public void searchKeyWord(Model model) throws InterruptedException, IOException {
        List<Keyword> keywordList = this.keywordService.findAllKeywords();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        for (Keyword keyword : keywordList){
            driver.manage().window().maximize();
            driver.navigate().to("https://www.google.com/");
            WebElement elementSearch = driver.findElement(By.name("q"));
            elementSearch.sendKeys(keyword.getKeywordSearch());
            Thread.sleep(2000); // Chờ 2 giây cho các gợi ý hiển thị
            Result result = new Result();
            // Capture lại hình ảnh sau khi gợi ý hiển thị
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            String rootPath = Paths.get("src", "main", "resources", "static", "images", "capture").toFile().getAbsolutePath();
            String finalName = System.currentTimeMillis() + "-capture.jpg";
            Files.copy(sourceFile.toPath(), Paths.get(rootPath + File.separator + finalName));
            List<WebElement> suggestions = driver.findElements(By.cssSelector("ul[role='listbox'] li"));
            String  suggestionList = suggestions.stream().map(suggestion -> suggestion.getText()+", "). collect(Collectors.joining());
            result.setSuggestions(suggestionList);
            result.setImage(finalName);
            result.setKeyword(keyword);
            result.setTime(LocalDate.now());
            this.resultService.saveResult(result);
        }
        Thread.sleep(2000);
        driver.close();
    }
}
