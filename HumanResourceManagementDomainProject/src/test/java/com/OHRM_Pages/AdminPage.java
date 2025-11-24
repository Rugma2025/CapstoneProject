package com.OHRM_Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

public class AdminPage {
	
	private WebDriver driver;
	
   public AdminPage(WebDriver driver)
	
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//page factory
	@FindBy(xpath="//ul[@class='oxd-main-menu']/li") private List<WebElement> allOptions;
	@FindBy(xpath="//span[text()='Admin']") private WebElement admin;
	@FindBy(xpath="(//input[@class='oxd-input oxd-input--active'])[2]") private WebElement empname;
	@FindBy(xpath="//button[@type='submit']") private WebElement search;
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span']") private WebElement records;
	@FindBy(xpath="(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]") private WebElement rolearrow;
	@FindBy(xpath="//div[@role='listbox']//span[text()='Admin']") private WebElement role;
	@FindBy(xpath="(//div[@class='oxd-select-text-input'])[2]") private WebElement selectstatus;
	@FindBy(xpath="//div[@role='option']//span[text()='Enabled']") private WebElement enab;
	@FindBy(xpath="//div[@role='option']//span[text()='Disabled']") private WebElement dis;
	@FindBy(xpath="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-level']") private WebElement adminpage;
	
	
	
	
	public int getAllOptions()
	{
		
		
		
		System.out.println("The options are: ");
	 for (WebElement option: allOptions)
	 {
		 System.out.println(option.getText());
		 
	 }
	 return allOptions.size();
	}
	
	public String openAdminPage()
	{
		admin.click();
		return adminpage.getText();
	}
	
	
	 
	public void searchByUsername(String us)
	{
		empname.sendKeys(us);
	}
	
	public void searchByUserRole()
	{
		rolearrow.click();
		role.click();
		
	}
	public void clickSearch()
	{
		search.click();
	}
	public void displayTotalRecords()
	
	{
		System.out.println(records.getText());
	}
	public void selectStatus(String status)
	{
		selectstatus.click();
		if (status.equals("Enable"))
		{
		enab.click();
		}
		else if(status.equals("Disable"))
		{
			dis.click();
						
		}
			
	}
	
	
	public void refresh()
	{
		driver.navigate().refresh();
	}
	
	

}
