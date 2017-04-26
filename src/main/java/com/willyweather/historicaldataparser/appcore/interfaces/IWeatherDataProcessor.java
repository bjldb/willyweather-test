package com.willyweather.historicaldataparser.appcore.interfaces;

import java.util.List;

import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This interface represents capability to process a set weather data
 */
public interface IWeatherDataProcessor {
	
	/**
	 * Processes a set of weather data
	 * @param weatherDataList set of weather data to process 
	 */
	public abstract void process(List<HistoricalWeatherData> weatherDataList);

}
