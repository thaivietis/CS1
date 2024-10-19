package com.nqt.cs1.service.imp;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CaptureService {
    public String CaptureImg(WebDriver driver) throws IOException {
        // Capture lại hình ảnh sau khi gợi ý hiển thị
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String rootPath = Paths.get("src", "main", "resources", "static", "images", "capture").toFile().getAbsolutePath();
        String finalName = System.currentTimeMillis() + "-capture.jpg";
        Files.copy(sourceFile.toPath(), Paths.get(rootPath + File.separator + finalName));
        return finalName;
    }
}
