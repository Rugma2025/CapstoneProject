package com.OHRM_TestCase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.OrangeHRM.util.Utility;

public class AdminTestCase extends BaseClass {
  
	@BeforeClass
	public void startUp()
	{
		lp.testlogin("Admin","admin123");
	}
	
	@Test(priority=1)
  public void testAdminPage() {
		//Get the list of options from the left side menu & the count.
	  int count=ap.getAllOptions();
	  Assert.assertEquals(count,12);
	  System.out.println("Total options are: "+count);
	  
	  Utility.getScreenshot(driver, "DashboardPage");
	  
	  String heading=ap.openAdminPage();
	  Assert.assertEquals(heading,"User Management");
	  System.out.println("Admin page is opened");
	  
	  Utility.getScreenshot(driver, "AdminPage");
	  
	  	  	  
  }
	
	@Test(priority=2)

	public void searchByUsername()
	{
		//send username to username textbox
		 ap.searchByUsername("Admin");
		 
		
		//click on the search button
		 ap.clickSearch();
		
		//display total record found
		 ap.displayTotalRecords();
		 
		 //screenshot
		 Utility.getScreenshot(driver, "SearchByUser");
		
		//refresh page
		 ap.refresh();
	}
	
	@Test(priority=3)
	public void searchByRole() throws InterruptedException
	{
		//select username by user role 
		ap.searchByUserRole();
		
		//click on the search button
		 ap.clickSearch();
		
		//display total record found
		 ap.displayTotalRecords();
		 
		 
		//Screenshot
		 Utility.getScreenshot(driver,"SearchByRole");
		 
		 Thread.sleep(3000);
		
		//refresh page
		 ap.refresh();
		
	}
	
	@Test (priority=4)
	public void searchByStatus() throws InterruptedException
	{
		//select status as Enable or Disable
		ap.selectStatus("Enable");
		
		//click on the search button
		 ap.clickSearch();
		
		//display total record found
		 ap.displayTotalRecords();
		 
		//Screenshot
		 Utility.getScreenshot(driver, "SearchByStatus");
		 
		 Thread.sleep(3000);
		
		//refresh page
		 ap.refresh();
	}
			
	
   
  
}
