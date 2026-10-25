package com.isomod.testclass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.isomod.pomclass.HomePage;
import com.isomod.pomclass.LoginPage;
import com.isomod.utilitypackage.DriverManager;
 
public class Base_testclass 
{
	public WebDriver driver;
	public Logger log;
	public Properties p;
	@Parameters("browsername")
	
   @BeforeClass(groups= {"sanity, regression"})
   public void setupmethod() throws IOException
   {
	   log=LogManager.getLogger(this.getClass());
	   FileReader path= new FileReader("./src//test//resources//config.properties");
	   p= new Properties();
	   p.load(path);
	   
	   //cross browser setup---->
	   
//	   if(browsername.equals("chrome"))
//	   {
//		   driver= new ChromeDriver();
//	   }
//	   else if(browsername.equals("firefox"))
//	   {
//		   driver= new FirefoxDriver();
//	   }
//	   else
//	   {
//		   System.out.println("No browser found");
//	   }
	   
	   //to run simple script on single browser
	   
	   driver= new ChromeDriver();
	   DriverManager.setDriver(driver); // ← Important for parallel
	   
	   driver.manage().deleteAllCookies();
	   driver.manage().window().maximize();
	   log.info("browser opened");
	   
	   driver.get(p.getProperty("appurl"));
	   log.info("URL Opened");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  
    }
	
	@BeforeMethod(groups= {"sanity, regression"})
	public void loginsetup() 
	{
		WebDriver driver = DriverManager.getDriver();
		LoginPage lp= new LoginPage(driver);
		lp.usermethod(p.getProperty("userid"));
		lp.passmethod(p.getProperty("password"));
		lp.loginmethod();
		log.info("User loged in Successfully");
	}
	
	@AfterMethod(groups= {"sanity, regression"})
	public void logoutsetup()
	{
		WebDriver driver = DriverManager.getDriver();
		HomePage hp= new HomePage(driver);
		hp.menumethod();
		hp.logoutmethod();
		log.info("User loged out Successfully");
	}
	
	@AfterClass(groups= {"sanity, regression"})
	public void teardownmethod()
	{
		WebDriver driver = DriverManager.getDriver();
		if (driver != null) {
            driver.quit();
        }
        DriverManager.unload();
        log.info("Browser closed");
	}
	
	public String capture_ss(String tname) throws IOException
	{
		WebDriver driver = DriverManager.getDriver();
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File dest= new File(path);
		FileHandler.copy(source, dest);
		return path;
	
	}
}
