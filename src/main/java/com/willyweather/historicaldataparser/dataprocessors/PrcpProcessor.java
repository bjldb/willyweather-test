package com.willyweather.historicaldataparser.dataprocessors;

import java.util.List;
import java.util.stream.Collectors;

import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This class process PRCP values of historical weather data
 */
public class PrcpProcessor extends AbstractValueProcessor {

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.dataprocessors.AbstractValueProcessor#process(java.util.List)
	 */
	@Override
	public void process(List<HistoricalWeatherData> weatherDataList) {

		String joinedStnValueString = weatherDataList
										.stream()
										.map((HistoricalWeatherData data) -> this.valueRoundUp(this.parseDoubleValue(data.getPrcp())))
										.map(Object::toString)
										.collect(Collectors.joining(","));
		
		System.out.println("\nPRCP: "+joinedStnValueString+"\n");
		
	}
	
}
