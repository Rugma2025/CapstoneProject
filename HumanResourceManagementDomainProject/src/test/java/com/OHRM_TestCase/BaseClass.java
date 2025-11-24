package com.OHRM_TestCase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.OHRM_Pages.AdminPage;
import com.OHRM_Pages.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AdminPage ap;
	
	@BeforeTest
	public void setUp()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		lp=new LoginPage(driver);
		ap=new AdminPage(driver);
	}
	
	@AfterTest
	public void endSession()
	{
		driver.close();
	}

}
