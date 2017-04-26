package com.willyweather.historicaldataparser.datafetchers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher;

/**
 * This class downloads data from the web given a URL as the source
 */
public class WebUrlDataFetcherImpl  implements IWeatherDataFetcher {

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#fetchData(java.lang.String, java.lang.String)
	 */
	@Override
	public File fetchData(String source, String destination) throws IOException {
		
		File destinationFile = new File(destination);
    	
		if( this.canFetch(source) ) {
			
			FileUtils.copyURLToFile(new URL(source), destinationFile, 10_000, 20_000);
			
		} else {
			
			StringBuilder errorBuilder = new StringBuilder("Can not fetch this source: ");
			errorBuilder.append(source).append("\n");
			throw new IOException(errorBuilder.toString());
			
		}
		
		return destinationFile;
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#canFetch(java.lang.String)
	 */
	@Override
	public boolean canFetch(String source) {
		
		try {
			
			new URL(source);
			return true;
			
		} catch(MalformedURLException ex) {
			
			return false;
			
		}
		
	}
}
