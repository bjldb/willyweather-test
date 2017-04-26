package com.willyweather.historicaldataparser.loggers;

import org.aspectj.lang.JoinPoint;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;

/**
 * This class is an ASPECT for tracing weather data processing events
 */
public class DataProcessingTracerAspect {

	private ILogger logger;
	
	public DataProcessingTracerAspect(ILogger logger) {
		this.logger = logger;
	}
	
	public void logProcessingEvent(JoinPoint joinPoint) {
		StringBuilder logBuilder = new StringBuilder("DATA PROCESS EVENT >>> ");
		logBuilder.append("Processor: ").append(joinPoint.getTarget().getClass().getSimpleName());
		logger.logTrace(logBuilder.toString());
	}
	
}
