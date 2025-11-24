package com.OHRM_TestCase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.OrangeHRM.util.Utility;

public class LoginPageTest extends BaseClass{
  @Test
  public void testLogin() {
	  String url=lp.testlogin("Admin","admin123");
	  Assert.assertTrue(url.contains("dashboard"));
	  
	  Utility.getScreenshot(driver, "LoginTest");
	  
  }
}
