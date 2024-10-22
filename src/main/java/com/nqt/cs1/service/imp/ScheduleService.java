package com.nqt.cs1.service.imp;

import com.nqt.cs1.constant.UrlConstant;
import com.nqt.cs1.domain.Keyword;
import com.nqt.cs1.service.ScheduleServiceWithDevice;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ScheduleService {
    @Autowired
    private KeywordServiceImp keywordService;

    @Autowired
    private SearchKeywordService searchKeywordService;

    @Autowired
    private ScheduleServiceWithPCImp scheduleServiceWithPCImp;

    @Scheduled(cron = "0 0 9 ? * *")
    public void searchSchedule() throws IOException, InterruptedException {
        List<Keyword> keywordList = this.keywordService.findAllKeywords();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = null;
        for (Keyword keyword : keywordList) {
            if(keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("PC")){
                driver = this.scheduleServiceWithPCImp.searchWithDevice();
                this.searchKeywordService.search(driver, UrlConstant.URL_GOOGLE, "q", keyword.getKeywordSearch());
                Thread.sleep(2000);
                driver.close();
            }else if(keyword.getPlatform().equals("GOOGLE") && keyword.getDevice().equals("Smartphone")){
                driver = this.searchKeywordService.searchWithSm();
                this.searchKeywordService.search(driver, UrlConstant.URL_GOOGLE, "q", keyword.getKeywordSearch());
                Thread.sleep(2000);
                driver.close();
            }else if (keyword.getPlatform().equals("YAHOO") && keyword.getDevice().equals("PC")) {
                driver = this.searchKeywordService.searchWithPC();
                this.searchKeywordService.search(driver, UrlConstant.URL_YAHOO, "p", keyword.getKeywordSearch());
                Thread.sleep(2000);
                driver.close();
            }else {
                driver = this.searchKeywordService.searchWithSm();
                this.searchKeywordService.search(driver, UrlConstant.URL_YAHOO, "p", keyword.getKeywordSearch());
                Thread.sleep(2000);
                driver.close();
            }
        }
    }
}
