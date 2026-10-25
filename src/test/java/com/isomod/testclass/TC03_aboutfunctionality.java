package com.isomod.testclass;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.isomod.pomclass.HomePage;

public class TC03_aboutfunctionality extends Base_testclass
{
	@Test(groups="regression")
	public void aboutmethod()
	{
	
		HomePage hp= new HomePage(driver);
		hp.menumethod();
		log.info("Clicked on Menu");
		
		hp.aboutmethod();
		log.info("Clicked on about");
		log.info("moved to about page");
		
		String aboutacturl=driver.getCurrentUrl();
		String aboutexpurl="https://saucelabs.com/";
		Assert.assertEquals(aboutacturl, aboutexpurl,"test case failed");
		
		driver.navigate().back();
		log.info("moved to home page");
		
	
	}

}
