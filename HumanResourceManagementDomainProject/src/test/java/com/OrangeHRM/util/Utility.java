package com.OrangeHRM.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	
	public static void getScreenshot(WebDriver driver,String fname)
	{
		
		
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File temp=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File(System.getProperty("user.dir")+"//Screenshots//"+fname+".png");
			try {
				FileHandler.copy(temp,dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
