package com.isomod.testclass;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class TC02_logoutfunctionality extends Base_testclass
{
	@Test(groups= {"sanity, regression"})
	public void logoutmethod()
	{
		log.info("User loged out check after Afterclass method");
	}

}
