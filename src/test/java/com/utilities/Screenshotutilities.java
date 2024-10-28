package com.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshotutilities {

    // Method to capture screenshot
    public static void captureScreenshot(WebDriver driver, String fileName) throws IOException {
        // Getting current timestamp to make filename unique
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
//        String filePath = "C:\\Users\\DELL\\eclipse-workspace\\Project1\\Apiautomation\\screenshots" + fileName + "_" + timestamp + ".png";
        String filePath = "C:\\Users\\DELL\\eclipse-workspace\\Project1\\Apiautomation\\path\\to\\save" + fileName + "_" + timestamp + ".png";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileUtils.copyFile(srcFile, destFile);
    }
}


