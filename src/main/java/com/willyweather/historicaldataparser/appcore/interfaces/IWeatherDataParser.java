package com.willyweather.historicaldataparser.appcore.interfaces;

import java.io.IOException;
import java.util.List;

import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This interface represents capability to parse weather data
 */
public interface IWeatherDataParser {
	
	/**
	 * Parses weather data from a given location
	 * @param weatherDataLoaction location of weather data to parse
	 * @return List of weather data parsed
	 * @throws IOException if an IO-related exception occurred while parsing
	 */
	public List<HistoricalWeatherData> parseWeatherData(String weatherDataLoaction) throws IOException;

}
