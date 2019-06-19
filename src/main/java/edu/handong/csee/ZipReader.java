package edu.handong.csee;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.cli.Options;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;

import edu.handong.csee.Utils;

public class ZipReader {
	//ArrayList<String> contents = new ArrayList<String>();
	Object[][] contents;
	Object[][] datatypes;
	
	String inPath;
	String outPath; 
	boolean help;
	
	public static void main(String[] args) {
		ZipReader zipReader = new ZipReader();
		zipReader.run(args);
	}

	private void run(String[] args) {
		//String path = args[0];		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
		}
		readFileInZip(inPath);
		Utils.writeAFile(datatypes, outPath);
	} 

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			inPath = cmd.getOptionValue("i");
			outPath = cmd.getOptionValue("o");			
			help = cmd.hasOption("h");
		
		} catch (Exception e) {
			printHelp(options);			
			return false;
		}
		return true;
	}
	
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				//.hasArg()
				.argName("Help")
				//.required()
				.build());
		
		return options;
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
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
	
}
