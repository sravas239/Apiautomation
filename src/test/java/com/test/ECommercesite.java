package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pages.Loginpage;
import com.utilities.Excelutilities;
import com.utilities.Screenshotutilities;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ECommercesite {
	WebDriver driver;
	Loginpage loginPage;
   private ExtentReports extent;
	private ExtentTest test;

	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("firefox") String browser) throws Exception {
		// Hardcode "firefox" or "chrome" to test the setup
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}

		// Configure WebDriver
		extent = Excelutilities.getExtentReports();
		test = extent.createTest("Cross-browser UI Test - " + browser);
     	driver.manage().window().maximize();

		// Open the target website
		driver.get("https://www.saucedemo.com/");
		loginPage = new Loginpage(driver);
	}

	@Test
	public void checkoutFlowTest() throws Exception {
		// Login

		loginPage.login("standard_user", "secret_sauce");
		// capture the screenshot
		Screenshotutilities.captureScreenshot(driver, "Testpassed");


	}


	@AfterMethod
	public void tearDown() {

		// Close the driver after the test
//		test = Excelutilities.createTest("SaucedemoApplication ");

		Excelutilities.flushReports();
		if (driver != null) {
			driver.quit();
		}
	}

}
