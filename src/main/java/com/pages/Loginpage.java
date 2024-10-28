package com.pages;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class Loginpage {
	    WebDriver driver;

	    // Constructor to initialize the elements
	    public Loginpage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // Define web elements using @FindBy
	    @FindBy(id = "user-name")
	    WebElement usernameInput;

	    @FindBy(id = "password")
	    WebElement passwordInput;

	    @FindBy(id = "login-button")
	    WebElement loginButton;

	    // Actions on login page
	    public void enterUsername(String username) {
	        usernameInput.sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        passwordInput.sendKeys(password);
	    }

	    public void clickLoginButton() {
	        loginButton.click();
	    }

	    public void login(String username, String password) {
	        enterUsername(username);
	        enterPassword(password);
	        clickLoginButton();
	    }
	}


