package com.willyweather.historicaldataparser.dataprocessors;

import java.util.List;
import java.util.stream.Collectors;

import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This class process STP values of historical weather data
 */
public class StpProcessor extends AbstractValueProcessor {

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.dataprocessors.AbstractValueProcessor#process(java.util.List)
	 */
	@Override
	public void process(List<HistoricalWeatherData> weatherDataList) {

		String joinedStnValueString = weatherDataList
										.stream()
										.map((HistoricalWeatherData data) -> this.valueRoundUp(this.parseDoubleValue(data.getStp())))
										.map(Object::toString)
										.collect(Collectors.joining(","));
		
		System.out.println("\nSTP: "+joinedStnValueString+"\n");
		
	}
	
}