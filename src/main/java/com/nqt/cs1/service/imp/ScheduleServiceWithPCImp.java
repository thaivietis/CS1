package com.nqt.cs1.service.imp;

import com.nqt.cs1.service.ScheduleServiceWithDevice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScheduleServiceWithPCImp implements ScheduleServiceWithDevice {
    @Override
    public WebDriver searchWithDevice() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
