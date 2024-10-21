package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchKeywordService {

    @Autowired
    private CaptureService captureService;

    @Autowired
    private ResultServiceImp resultService;


    public String search(WebDriver driver, String http, String emlementByName, String nameKeyword) throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.navigate().to(http);
        WebElement elementSearch = driver.findElement(By.name(emlementByName));
        elementSearch.sendKeys(nameKeyword);
        Thread.sleep(2000);
        List<WebElement> suggestions = driver.findElements(By.cssSelector("ul[role='listbox'] li"));
        String suggestionList = suggestions.stream().map(suggestion -> suggestion.getText()+"\n"). collect(Collectors.joining());
        return suggestionList;
    }

    public WebDriver searchWithPC(){
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver searchWithSm(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
