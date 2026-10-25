package com.isomod.utilitypackage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.isomod.testclass.Base_testclass;

public class Listeners_class implements ITestListener
{
	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
	String repname;
	
	public void onStart(ITestContext context)
	{
		System.out.println("Test Execution Started");  
		
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname="Test-reportnov-" + timestamp + ".html";
		
		sparkreport= new ExtentSparkReporter(".\\reports\\" + repname);
		
		sparkreport.config().setDocumentTitle("Project Report");
		sparkreport.config().setReportName("Automation_Project");
		sparkreport.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkreport);
		
		extent.setSystemInfo("Application", "Auto");
		extent.setSystemInfo("Domain", "E-commerce");
		extent.setSystemInfo("Module", "Shipping");
		extent.setSystemInfo("User", "KP");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser", "Chrome");
		
		List<String> includegroups= context.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includegroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());    //To add groups in report
		test.log(Status.PASS, result.getName() + "Test passed");
		System.out.println("Test case passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + "Test Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		System.out.println("Test case Failed");
		
		try 
		{
			//Take SS when TC failed---
			String imgpath= new Base_testclass().capture_ss(result.getName());
		    test.addScreenCaptureFromPath(imgpath);
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	    
	}
	
	public void onTestSkip(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "Test Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		System.out.println("Test case skipped");
	}

	public void onFinish(ITestContext context)
	{
		extent.flush();
		String pathofreport= System.getProperty("user.dir") + "\\reports\\" + repname;
		File extentreport= new File(pathofreport);
		
		try
		{
			Desktop.getDesktop().browse(extentreport.toURI());
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Test Execution Finished");
	}
}
