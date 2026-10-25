package com.isomod.utilitypackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilityclass 
{
	public WebDriver driver;
	
	public static void SS_method(WebDriver driver) throws IOException 
	{
		SimpleDateFormat d= new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date date= new Date();
	    String newdate= d.format(date);
	    
	    TakesScreenshot ts=(TakesScreenshot)driver;
	    File Source=ts.getScreenshotAs(OutputType.FILE);
	    File dest= new File(".\\screenshots\\Image-" + newdate + ".png");
	    FileHandler.copy(Source, dest);
	    
	}
	
    
    

}
