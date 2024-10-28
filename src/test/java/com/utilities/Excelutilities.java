package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Excelutilities {
    private static ExtentReports extent;
    private static ExtentTest test;
    
  
	public static ExtentReports getExtentReports() {
        if (extent == null) {
          String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Saucedemo Automation Test Results");
            reporter.config().setDocumentTitle("Test Report");
            
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Sravani Guduru");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
    
    public static ExtentTest createTest(String testName) {
        test = getExtentReports().createTest(testName);
        return test;
    }
    
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
