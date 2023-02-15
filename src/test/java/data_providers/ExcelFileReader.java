package data_providers;
import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
	private XSSFWorkbook wb=null;
	private FileInputStream fis=null;

	public ExcelFileReader()
	{
		try 
		{
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/testData/TechFiosAdmin.xlsx"));
			wb = new XSSFWorkbook(fis);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public int getSheetIndexByName(String name)
	{
		return wb.getSheetIndex(name);
	}
	
	public List<Map<String,String>> getExcelFileContents(String sheetName)
	{
		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
		
		//Get WorkSheet
		int sheetIndex = this.getSheetIndexByName(sheetName);
		
		//Get corresponding sheet
		XSSFSheet sheet = wb.getSheetAt(sheetIndex);
		
		//Get the headers in the sheet
		List<String> headers = new ArrayList<String>();
		//Get the first row which contains headers
		XSSFRow row = sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			headers.add(i,row.getCell(i).getStringCellValue().trim());
		}
		//System.out.println("Excel File Header:"+headers);
		
		row=sheet.getRow(1);
		int maxRows = sheet.getLastRowNum();
		int maxCols = row.getLastCellNum();
		for(int i=1;i<=maxRows;i++)
		{
			Map<String,String> map = new HashMap<String,String>();
		
			for(int j=0;j<maxCols;j++)
			{
				Cell c = row.getCell(j);
				System.out.println(c.getCellType());
				
				switch (c.getCellType()) 
                {
                	case STRING: //demo@techfios.com
                		map.put(headers.get(j),c.getRichStringCellValue().getString());
                		System.out.println(c.getRichStringCellValue().getString());
                		break;
                    
                	case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(c)) 
	                    {
	                    	map.put(headers.get(j),c.getDateCellValue().toString());
	                        System.out.println(c.getDateCellValue());
	                    } 
	                    else 
	                    {
	                    	map.put(headers.get(j),String.valueOf(Math.round(c.getNumericCellValue())));
	                        System.out.println(c.getNumericCellValue());
	                    }
	                    break;
                
                	case BOOLEAN:
                    	map.put(headers.get(j),String.valueOf(c.getBooleanCellValue()));
	                    System.out.println(c.getBooleanCellValue());
	                    break;
                    
                	case FORMULA:
	                	map.put(headers.get(j),String.valueOf(c.getCellFormula()));
	                	System.out.println(c.getCellFormula());
	                    break;
                	
                	default:
                		break;
			}	
		}
			l.add(map);
	}
		//System.out.println(l);
		return l;
	}
	
	/*
	public static void main(String args[])
	{
		ExcelFileReader el = new ExcelFileReader();
		el.getExcelFileContents();
	}
	*/
}
