package com.willyweather.historicaldataparser.datafetchers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher;

/**
 * This abstract class represents delegator fetchers which determines and delegates to 
 * which fetcher can handle fetch operation for source
 */
public class AbstractDataFetcherDelegator implements IWeatherDataFetcher {
	
	protected List<IWeatherDataFetcher> fetchers = new ArrayList<IWeatherDataFetcher>();

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#fetchData(java.lang.String, java.lang.String)
	 */
	@Override
	public File fetchData(String source, String destination) throws IOException {
		
		File fetchedData = null;
		
		IWeatherDataFetcher deletgatedFetcher = this.getFetcher(source);
		
		if(deletgatedFetcher == null) {
			StringBuilder exceptionBuilder = new StringBuilder("Fetcher for this source not yet implemented: '");
			exceptionBuilder.append(source).append("'.\n");
			throw new IOException(exceptionBuilder.toString());
		} else {
			fetchedData = deletgatedFetcher.fetchData(source, destination);
		}

		return fetchedData;
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#canFetch(java.lang.String)
	 */
	@Override
	public boolean canFetch(String source) {
		
		boolean canFetch = false;
		
		for(IWeatherDataFetcher fetcher : fetchers) {
			
			if( fetcher.canFetch(source) ) {
				canFetch = true;
				break;
			}
			
		}
		
		return canFetch;
	}
	
	/**
	 * Gets appropriate fetcher for given source
	 * @param source source of data to be fetched
	 * @return selected fetcher
	 */
	private IWeatherDataFetcher getFetcher(String source) {
		
		IWeatherDataFetcher selected = null;
		
		for(IWeatherDataFetcher fetcher : fetchers) {
			if( fetcher.canFetch(source) ) {
				selected = fetcher;
			}
			fetcher.canFetch(source);
		}
		
		return selected;
		
	}
	
	
}
