package com.nqt.cs1.component;

import com.nqt.cs1.constant.UrlConstant;
import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.domain.Result;
import com.nqt.cs1.service.imp.KeywordServiceImp;
import com.nqt.cs1.service.imp.ResultServiceImp;
import com.nqt.cs1.service.imp.SearchKeywordServiceImp;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class ScheduleComponent {
    @Autowired
    private KeywordServiceImp keywordService;

    @Autowired
    private SearchKeywordServiceImp searchKeywordServiceImp;

    @Autowired
    private CaptureComponent captureComponent;

    @Autowired
    private ResultServiceImp resultService;

    @Scheduled(cron = "0 0 9 ? * *")
    public void searchSchedule() throws IOException, InterruptedException {
        List<Keyword> keywordList = this.keywordService.findAllKeywords();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = null;
        String suggestions;
        for (Keyword keyword : keywordList) {
            Result result;
            if (keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("PC")) {
                driver = this.searchKeywordServiceImp.searchWithPc();
                suggestions = this.searchKeywordServiceImp.search(driver, UrlConstant.URL_GOOGLE, "q", keyword.getKeywordSearch());
            } else if (keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("Smartphone")) {
                driver = this.searchKeywordServiceImp.searchWithSm();
                suggestions = this.searchKeywordServiceImp.search(driver, UrlConstant.URL_GOOGLE, "q", keyword.getKeywordSearch());
            } else if (keyword.getPlatform().equals("YAHOO") && keyword.getDevice().equals("PC")) {
                driver = this.searchKeywordServiceImp.searchWithPc();
                suggestions = this.searchKeywordServiceImp.search(driver, UrlConstant.URL_YAHOO, "p", keyword.getKeywordSearch());
            } else {
                driver = this.searchKeywordServiceImp.searchWithSm();
                suggestions = this.searchKeywordServiceImp.search(driver, UrlConstant.URL_YAHOO, "p", keyword.getKeywordSearch());
            }
            result = Result.builder()
                    .image(this.captureComponent.CaptureImg(driver))
                    .suggestions(suggestions)
                    .time(LocalDate.now())
                    .build();
            this.resultService.saveResult(result);
            driver.close();
        }
    }
}
