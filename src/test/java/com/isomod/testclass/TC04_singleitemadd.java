package com.isomod.testclass;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.Test;

import com.isomod.pomclass.HomePage;
import com.isomod.utilitypackage.Utilityclass;

public class TC04_singleitemadd extends Base_testclass
{
	@Test(groups= {"sanity"})
	public void singleitemmethod() throws IOException
	{
	
		HomePage hp= new HomePage(driver);
		hp.singleitemmethod();
		log.info("single item added to cart");
		
		String text=hp.getremovetext();
		System.out.println(text);	
//		Assert.assertEquals(text, "Remove");
		
//OR	
		if(text.equals("Remove"))
		{
			AssertJUnit.assertTrue(true);
			Utilityclass.SS_method(driver);
		}
		else
		{
			log.error("Test failed");
			AssertJUnit.assertTrue(false);
		}
		
		
	}

}
