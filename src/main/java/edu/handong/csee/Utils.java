package edu.handong.csee;

import java.io.File;
import java.util.ArrayList;
//import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	public static void writeAFile(Object[][] contents, String targetFileName) {	
		final String FILE_NAME = targetFileName;

	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Data by students");
	     
	        int rowNum = 0;
	        System.out.println(contents[0][0]);
	    
        	for(int i= 0; i<contents.length; i++) {	   
        		Row row = sheet.createRow(rowNum++); 
	            int colNum = 0; 
	        	for(int j=0; j<contents[i].length; j++) {		 
	        		Cell cell = row.createCell(colNum++);		            
		            cell.setCellValue((String)contents[i][j]);
	        	}
	        }
//	            Row row = sheet.createRow(rowNum++);
//	            int colNum = 0;
//	            for(Object data: field) {
//	            	  Cell cell = row.createCell(colNum++);		            
//			            cell.setCellValue((String)data);
//	            }		          	          		       		            
//            }
	        
	        try {
	            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	            workbook.write(outputStream);
	            workbook.close();

	        }catch(FileNotFoundException e) {
	        	File tmp = new File(FILE_NAME);
				try{
					tmp.getParentFile().mkdirs();
					tmp.createNewFile();
				}
				catch(IOException err) {
					System.out.println(err);
					System.exit(0);
				} 
			}catch(IOException e) {
				System.out.println(e);
			}
	    }
	}