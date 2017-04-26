package com.willyweather.historicaldataparser.appcore.interfaces;

import java.io.File;
import java.io.IOException;

/**
 * This interface represents capability to fetch weather data from a source and output to a destination
 */
public interface IWeatherDataFetcher {
	
	/**
	 * Fetches data from a given source and shall put fetched data to specified destination
	 * @param source source of weather data
	 * @param destination target output destination for fetched data
	 * @return File object representing fetched data
	 * @throws IOException if an IO-related exception occurred while fetching weather data
	 */
	public abstract File fetchData(String source,String destination) throws IOException;
	
	/**
	 * Determines if the fetcher can fetch given the weather data source
	 * @param source the given weather data source
	 * @return <code>true</code> if the fetcher can fetch given the weather data source; <code>false</code>, if otherwise 
	 */
	public abstract boolean canFetch(String source);

}
