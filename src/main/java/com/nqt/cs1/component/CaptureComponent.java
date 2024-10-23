package com.nqt.cs1.component;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class CaptureComponent {
    public String CaptureImg(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String rootPath = Paths.get("src", "main", "resources", "static", "images", "capture").toFile().getAbsolutePath();
        String finalName = System.currentTimeMillis() + "-capture.jpg";
        Files.copy(sourceFile.toPath(), Paths.get(rootPath + File.separator + finalName));
        return finalName;
    }
}
