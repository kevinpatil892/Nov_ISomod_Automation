package com.isomod.testclass;

import org.testng.annotations.Test;

import com.isomod.pomclass.HomePage;
import com.isomod.pomclass.LoginPage;
import com.isomod.utilitypackage.DataProviders;

import junit.framework.Assert;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC001_Datadriventest extends Base_testclass
{
	@Test(dataProvider="Logintest", dataProviderClass=DataProviders.class)
	public void verify_datamethod(String userid, String pass, String exp)
	{
		log.info("<----TC Execution started---->");
		try
		{
			//LoginPage
			LoginPage lp= new LoginPage(driver);
			lp.usermethod(userid);
			lp.passmethod(pass);
			lp.loginmethod();
			log.info("User loged in Successfully");
			
			//Homepage
			HomePage hp= new HomePage(driver);
			String homeurl=driver.getCurrentUrl();
			String acturl="https://www.saucedemo.com/inventory.html";
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(homeurl.equals(acturl))
				{
					hp.menumethod();
					hp.logoutmethod();
					log.info("User loged out Successfully");
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(homeurl.equals(acturl))
				{
					hp.menumethod();
					hp.logoutmethod();
					log.info("User loged out Successfully");
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		log.info("<----TC Execution finished---->");
	}

}
