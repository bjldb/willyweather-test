package com.willyweather.historicaldataparser.loggers;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;
import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;

/**
 * This class is an ASPECT for tracing weather data parsing events
 */
public class ParsedWeatherDataTracerAspect {
	
	private ILogger logger;
	
	/**
	 * Constructor
	 * @param logger logger for this ASPECT
	 */
	public ParsedWeatherDataTracerAspect(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * (Advice) Logs weather data parsing events
	 * @param joinPoint join point for this aspect's advice
	 * @param result captured return result of target method
	 */
	public void logParsedWeatherData(JoinPoint joinPoint, Object result) {
		StringBuilder logBuilder = new StringBuilder("PARSED WEATHER DATA:");
		
		@SuppressWarnings("unchecked")
		List<HistoricalWeatherData> fetchedData = (List<HistoricalWeatherData>) result;
		
		for(int i = 0; i < fetchedData.size(); i++) {
			logBuilder.append("\n\t#").append(i).append(": ");
			logBuilder.append("STN(").append(fetchedData.get(i).getStn()).append(") | ");
			logBuilder.append("WBAN(").append(fetchedData.get(i).getWban()).append(") | ");
			logBuilder.append("YEARMODA(").append(fetchedData.get(i).getYearmoda()).append(")");
		}
		
		logger.logDebug(logBuilder.toString());
	}

}
