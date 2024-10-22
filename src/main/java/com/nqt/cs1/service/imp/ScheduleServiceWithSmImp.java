package com.nqt.cs1.service.imp;

import com.nqt.cs1.service.ScheduleServiceWithDevice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ScheduleServiceWithSmImp implements ScheduleServiceWithDevice {
    @Override
    public WebDriver searchWithDevice() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}