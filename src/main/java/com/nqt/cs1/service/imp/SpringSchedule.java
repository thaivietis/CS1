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

    @Autowired
    private CaptureService captureService;

    @Autowired
    private SearchKeywordService searchKeywordService;

    @Scheduled(cron = "0 0 9 ? * *")
    public void searchSchedule() throws IOException, InterruptedException {
        List<Keyword> keywordList = this.keywordService.findAllKeywords();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = null;
        for (Keyword keyword : keywordList) {
            if(keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("PC")){
                driver = this.searchKeywordService.searchWithPC();
                this.searchKeywordService.search(driver, "https://www.google.com/", "q", keyword);
            }else if(keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("Smartphone")){
                driver = this.searchKeywordService.searchWithSm();
                this.searchKeywordService.search(driver, "https://www.google.com/", "q", keyword);
            }else if (keyword.getPlatform().equals("YAHOO") && keyword.getDevice().equals("PC")) {
                driver = this.searchKeywordService.searchWithPC();
                this.searchKeywordService.search(driver, "https://www.yahoo.com/", "p", keyword);
            }else {
                driver = this.searchKeywordService.searchWithSm();
                this.searchKeywordService.search(driver, "https://www.yahoo.com/", "p", keyword);
            }
        }
        Thread.sleep(2000);
        assert driver != null;
        driver.close();
    }
}
