package edu.handong.csee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ExcelReader {
	
//	public ArrayList<String> getData(String path) {
//		ArrayList<String> values = new ArrayList<String>();
//		
//		System.out.println(path);
//		
//		try (InputStream inp = new FileInputStream(path)) {   // 파일 읽기
//		    //InputStream inp = new FileInputStream("workbook.xlsx");
//		    
//		        Workbook wb = WorkbookFactory.create(inp);
//		        Sheet sheet = wb.getSheetAt(0);  //시트가져오기 0은 첫번째 시트
//		        int rows = sheet.getPhysicalNumberOfRows(); //시트에서 총 행수
//		       // int columns = sheet.getRow(0).getPhysicalNumberOfCells(); // 시트에서 총 열수 
//		        
//		        Iterator<Row> iterator = sheet.iterator();
//
//	            while (iterator.hasNext()) {
//
//	                Row currentRow = iterator.next();
//	                Iterator<Cell> cellIterator = currentRow.iterator();
//
//	                while (cellIterator.hasNext()) {
//
//	                    Cell currentCell = cellIterator.next();
//	                    if (currentCell.getCellType() == CellType.STRING) {
//	                        System.out.print(currentCell.getStringCellValue() + "--");
//	                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
//	                        System.out.print(currentCell.getNumericCellValue() + "--");
//	                    }
//
//	                }
//	                System.out.println(); 
//	                
//			        Row row = sheet.getRow(2);
//			        Cell cell = row.getCell(1);
//			        if (cell == null)
//			            cell = row.createCell(3);
//			        
//			        values.add(cell.getStringCellValue());
//	            }
//		    } catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		        
//		return values;
//	}
	
	private Object[][] datatypes;

	public Object[][] getData(InputStream is) {
		ArrayList<String> values = new ArrayList<String>();
		//ArrayList<ArrayList> info = new ArrayList<ArrayList>();
		String rowCell;
		//String rowLine= "";
		boolean flag = true;
		
		try (InputStream inp = is) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        int rows = sheet.getPhysicalNumberOfRows(); //시트에서 총 행수
		        int cols= sheet.getRow(0).getPhysicalNumberOfCells();
		        
		        Iterator<Row> iterator = sheet.iterator();
		        int i=0; 
		        int j=0;
		        Object[][] datatypes = new Object[rows+10000][cols+10000];
		        while (iterator.hasNext()) {
		        	String rowLine= "";
	                Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                Map<String, Object[]> data = new TreeMap<String, Object[]>();
	                
	                while (cellIterator.hasNext()) {
	                	
	                    Cell currentCell = cellIterator.next();
	                    if (currentCell.getCellType() == CellType.STRING) { 
	                   
	                    	datatypes[i][j] = currentCell.getStringCellValue();
//	                    	rowCell = currentCell.getStringCellValue();
//	                    	rowLine= rowLine + rowCell;
	                    	j++;
	                    	
	                    	
	                    } //else if (currentCell.getCellType() == CellType.NUMERIC) {
//	                    	rowCell = Double.toString(currentCell.getNumericCellValue());
//	                    	rowLine = rowLine + "," + rowCell;
//	                    }
	                    //System.out.println();
	                }	
	                // values.add(rowLine);
	                i++;
	                if(flag) {
	                	System.out.println(rowLine);
	                	flag = false;
	                }
		        }
		        System.out.println(datatypes[0][0]);
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return datatypes;
	}
}
