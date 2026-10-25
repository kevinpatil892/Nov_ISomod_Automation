package com.isomod.testclass;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.isomod.utilitypackage.Utilityclass;

public class TC01_loginfeature extends Base_testclass
{
	@Test(groups= {"sanity, regression"})
	public void login_testcase() throws IOException
	{
		String acturl=driver.getCurrentUrl();
		String expurl="https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(acturl, expurl, "TC Failed");
//		Utilityclass.SS_method(driver);
	}

}
