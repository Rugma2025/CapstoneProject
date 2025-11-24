package com.OrangeHRM;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

import com.OrangeHRM.util.Utility;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginModule {
	
	String fpath="src/test/resources/Excel/LoginData.xlsx";
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int index=1;
	
	WebDriver driver;
	
  @Test(dataProvider = "getLoginData")
  public void testLogin(String un, String ps) throws IOException, InterruptedException {
	  driver.findElement(By.name("username")).sendKeys(un);
	  driver.findElement(By.name("password")).sendKeys(ps);
	  
	  Utility.getScreenshot(driver, "login_"+un+"_"+ps);
	  
	  
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  
      Thread.sleep(2000);
	  Utility.getScreenshot(driver, "afterlogin_"+un+"_"+ps);
	  
	    
	  Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	  System.out.println("Logged in successfully");
	  
	  
	  
  }
  
  

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  
	  row=sheet.getRow(index);
	  cell=row.createCell(3);
	  String msg;
	  if(driver.getCurrentUrl().contains("dashboard"))
	  {
		 
		  driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		  driver.findElement(By.linkText("Logout")).click();
		  System.out.println("Logged in successfully..Test case pass!");
		  
		  cell.setCellValue("pass");
		  
		  
	  }
	  else
	  {
		  msg = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		  System.out.println("Invalid credentials\nTest Case fail");
			
		  cell.setCellValue("Fail");
	  }
	  
	  index++;
	  
  }


  @DataProvider
  public Object[][] getLoginData() {
    //return new Object[][] {
      //new Object[] { 1, "a" },
      //new Object[] { 2, "b" },
    //};
	  int rows=sheet.getPhysicalNumberOfRows();
	  String loginData[][]=new String[rows-1][2];
	  
	  for(int i=0;i<rows-1;i++)
	  {
		  for(int j=0;j<2;j++)
		  {
			  loginData[i][j]= sheet.getRow(i+1).getCell(j+1).getStringCellValue();
		  }
		 
	  }
	  return loginData;
	  
  }
  @BeforeTest
  public void beforeTest() throws IOException {
	  file=new File(fpath);
	  fis =new FileInputStream(file);
	  wb=new XSSFWorkbook(fis);
	  sheet=wb.getSheet("Data");
	  fos=new FileOutputStream(file);
	  
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  
  }

  @AfterTest
  public void afterTest() throws IOException {
	  wb.write(fos);
	  wb.close();
	  fis.close();
	  
	  driver.close();
  }

}
