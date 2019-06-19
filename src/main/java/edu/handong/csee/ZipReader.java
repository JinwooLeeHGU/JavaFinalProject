package edu.handong.csee;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.cli.Options;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.csee.Utils;

public class ZipReader {
	//ArrayList<String> contents = new ArrayList<String>();
	Object[][] contents;
	String outPath;
	Object[][] datatypes;
	
	public static void main(String[] args) {
		ZipReader zipReader = new ZipReader();
		zipReader.run(args);
	}

	private void run(String[] args) {
		//String path = args[0];
		
		//Options options = createOptions();
		
		readFileInZip("0001.zip");
		outPath = args[1];
		Utils.writeAFile(datatypes, outPath);
	} 

	public void readFileInZip(String path) {
		ZipFile zipFile;
		

		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

		    while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		    
		        ExcelReader myReader = new ExcelReader();
		        
		        datatypes = deepCopy(myReader.getData(stream));
		        			
		        System.out.println(datatypes[0][0]);
		        
//		        System.out.println(datatypes.length);
//		        for(int i= 0; i<datatypes.length; i++) {
//		        	
//		        	for(int j=0; j<datatypes[i].length; j++) {		 
//						contents[i][j] = datatypes[i][j];
//		        	}
//		        }
//		        for(int i = 0; i < mArrayList.size(); i++) {
//		        	contents.add(mArrayList.get(i));
//		        }
		        //contents.add(myReader.getData(stream));
		        }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object[][] deepCopy(Object[][] original) {
	    if (original == null) {
	        return null;
	    }

	    Object[][] result = new Object[original.length][original[0].length];
	    for (int i = 0; i < original.length; i++) {
	        System.arraycopy(original[i], 0, result[i], 0, original[i].length);
	    }
	    return result;
	}
	
}
