package com.willyweather.historicaldataparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class handles generic File related operations
 */
public class FileOperationsUtil {
	
	/**
	 * This interface represents capability to handle parse line from file
	 */
	public interface FileLineHandler {
		
		/**
		 * Handles parsed line from file
		 * @param fileLine parsed line from file
		 * @throws IOException if handling the line results in an IO-related exception, or line may have data related to a IO-related issue 
		 */
		public void handleLine(String fileLine) throws IOException;
		
	}
	
	/**
	 * Parses target file by line
	 * @param file file to parse
	 * @param ignoredLineStarter String indicating if a line does not need handling
	 * @param lineHandler responsible for handling a parsed line from file
	 * @throws IOException if an IO-related exception occurred
	 */
	public static void parseFileByLine(File file,String ignoredLineStarter,FileLineHandler lineHandler) throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
		    for (String fileLine = null; (fileLine = br.readLine()) != null; /* no step */) {

		    	if(fileLine.startsWith(ignoredLineStarter)) {
		    		continue;
		    	}
		    	
		    	lineHandler.handleLine(fileLine);
		    
		    }
		    
		}
	}

}
