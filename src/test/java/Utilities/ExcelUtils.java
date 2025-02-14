package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
           
	
	
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	
	
	
	public ExcelUtils(String path){
		this.path=path;
	}
	
	public static int getrow(String path, String xlsheet) throws IOException
	{
		     fi =new  FileInputStream(path);
		       wb=new XSSFWorkbook(fi);
		      ws= wb.getSheet(xlsheet);
		     int rowcount = ws.getLastRowNum();
		     
		     
		     wb.close();
		     fi.close();
		     return rowcount;
		       
		       
		       
		
	
	}
	
	
	public static int getcell(String xlfile, String xlsheet, int rownum) throws IOException
	{
		     fi =new  FileInputStream(xlfile);
		       wb=new XSSFWorkbook(fi);
		      ws= wb.getSheet(xlsheet);
		     row = ws.getRow(rownum);
		     short cellcount = row.getLastCellNum();
		     
		     
		     wb.close();
		     fi.close();
		     return cellcount;
		       
	
	

}
	
	public static String getdata(String xlfile, String xlsheet, int rownum, int cellnum) throws IOException
	{
		     fi =new  FileInputStream(xlfile);
		       wb=new XSSFWorkbook(fi);
		      ws= wb.getSheet(xlsheet);
		     row = ws.getRow(rownum);
		    cell= row.getCell(cellnum);
		    String value;
		    try {
		    	  value = cell.toString();
		    }
		                 catch (Exception e) {
							value=" ";
						}
		                        return value;
		       
	
	

}
	public static void setdata(String xlfile, String xlsheet, int rownum, int cellnum, String data) throws IOException
	{
		     fi =new  FileInputStream(xlfile);
		       wb=new XSSFWorkbook(fi);
		      ws= wb.getSheet(xlsheet);
		     row = ws.getRow(rownum);
		   cell= row.createCell(cellnum);
		 cell.setCellValue(data);
		 fo =new  FileOutputStream(xlfile);
		 wb.close();
		 fi.close();
		 fo.close();
		 
		   
		                        
		       
	
	

}
	
	
}
	
	
