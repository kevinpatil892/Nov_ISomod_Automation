package com.isomod.utilitypackage;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public FileInputStream file;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public ExcelUtility(String path)    //constructor to initialize path
	{
		this.path=path;
		
	}
	public int getrowcount(String sheetname) throws IOException
	{ 
		file= new FileInputStream(path);
		book= new XSSFWorkbook(file);
		sheet= book.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		book.close();
		file.close();
		return rowcount;
	}

	public int getcellcount(String sheetname, int rownum) throws IOException
	{
		file= new FileInputStream(path);
		book= new XSSFWorkbook(file);
		sheet= book.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		book.close();
		file.close();
		return cellcount;
	}
	
	public String getcelldata(String sheetname, int rownum, int cellnum) throws IOException
	{
		file= new FileInputStream(path);
		book= new XSSFWorkbook(file);
		sheet= book.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		
		String data=cell.getStringCellValue();
		book.close();
		file.close();
		return data;
		
		
	}
}
