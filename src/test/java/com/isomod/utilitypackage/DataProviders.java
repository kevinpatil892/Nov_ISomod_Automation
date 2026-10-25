package com.isomod.utilitypackage;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="Logintest")
	public String[][] getdata() throws IOException
	{
		String path=".//Testdata//Book1.xlsx";
		
		ExcelUtility util= new ExcelUtility(path);
		
		int totalrows=util.getrowcount("Sheet1");
		int totalcells=util.getcellcount("Sheet1", 1);
		
		String exceldata[][]= new String[totalrows] [totalcells];
		
		for(int i=1;i<totalrows;i++)
		{
			for(int j=0;j<totalcells;j++)
			{
				exceldata[i-1][j]= util.getcelldata("Sheet1", i, j);
			}
		}
		return exceldata;
	}
	

}
