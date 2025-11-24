package com.OHRM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	
	
    public LoginPage(WebDriver driver)
	
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Page factory
	@FindBy(name="username") private WebElement user;
	@FindBy(name="password") private WebElement pwd;
	@FindBy(xpath="//button[@type='submit']") private WebElement login;
	
	public String testlogin(String name,String pass)
	{
		user.sendKeys(name);
		pwd.sendKeys(pass);
		login.click();
		
		return driver.getCurrentUrl();
	}

}
