package com.willyweather.historicaldataparser.dataprocessors;

import java.util.List;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataProcessor;
import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This abstract class represents weather data processors
 */
public abstract class AbstractValueProcessor implements IWeatherDataProcessor {
	
	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataProcessor#process(java.util.List)
	 */
	@Override
	public abstract void process(List<HistoricalWeatherData> weatherDataList);
	
	/**
	 * Rounds up value to the nearest integer
	 * @param doubleValue
	 * @return
	 */
	protected int valueRoundUp(double doubleValue) {
		return (int) Math.ceil(doubleValue);
	}
	
	/**
	 * Extracts and parses double value from a value string
	 * @param valueString value string to parse
	 * @return extracted double value
	 */
	protected double parseDoubleValue(String valueString) {
		return Double.parseDouble(valueString.replaceAll("[^\\d.]", ""));
	}
	
}
