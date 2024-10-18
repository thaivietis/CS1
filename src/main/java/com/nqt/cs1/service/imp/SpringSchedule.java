package com.nqt.cs1.service.imp;

import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.service.ResultService;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpringSchedule {
    @Autowired
    private KeywordServiceImp keywordService;

    @Autowired
    private ResultServiceImp resultService;
    @Scheduled(cron = "0 * * ? * *")
    public void searchSchedule() throws IOException, InterruptedException {
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
            String  suggestionList = suggestions.stream().map(suggestion -> suggestion.getText()+"\n"). collect(Collectors.joining());
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
