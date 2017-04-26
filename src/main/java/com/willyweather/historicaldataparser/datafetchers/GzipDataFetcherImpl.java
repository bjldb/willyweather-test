package com.willyweather.historicaldataparser.datafetchers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher;

/**
 * This class unzips gzip archives 
 */
public class GzipDataFetcherImpl implements IWeatherDataFetcher {

	private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8; 

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#fetchData(java.lang.String, java.lang.String)
	 */
	@Override
	public File fetchData(String source, String destination) throws IOException {
		
		File destinationFile = new File(destination);
		destinationFile.getParentFile().mkdirs();
		
		try( 
        		FileInputStream fin = new FileInputStream(source);
        		BufferedInputStream in = new BufferedInputStream(fin);
        		FileOutputStream out = new FileOutputStream(destination);
        		GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in)) {
        	
			if( source.length() <= MAX_BUFFER_SIZE ) {
				
				int bufferSize = (int)source.length();
	            final byte[] buffer = new byte[bufferSize];
	            int n = 0;
	            while (-1 != (n = gzIn.read(buffer))) {
	                out.write(buffer, 0, n);
	            }
				
			} else {
				
				StringBuilder errorBuilder = new StringBuilder("File size too large to unzip for this implementation");
				throw new IOException(errorBuilder.toString());
				
			}
            
        }
		
		return destinationFile;
		
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#canFetch(java.lang.String)
	 */
	@Override
	public boolean canFetch(String source) {
		return source.endsWith(".gz");
	}
	
}
