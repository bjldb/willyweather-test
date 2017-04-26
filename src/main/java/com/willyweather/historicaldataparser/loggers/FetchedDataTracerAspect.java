package com.willyweather.historicaldataparser.loggers;

import java.io.File;

import org.aspectj.lang.ProceedingJoinPoint;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;

/**
 * This class is an ASPECT for logging fetched data
 */
public class FetchedDataTracerAspect {
	
	private ILogger logger;
	
	/**
	 * Constructor
	 * @param logger logger for this ASPECT
	 */
	public FetchedDataTracerAspect(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * (Advice) Logs weather data fetching events
	 * @param joinPoint join point for this aspect's advice
	 * @param result captured return result of target method
	 * @throws Throwable
	 */
	public File logDataFetch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StringBuilder logBuilder = new StringBuilder("FETCH EVENT:\n\tCLASS: ");
		logBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
		logBuilder.append("\n\tSOURCE: ").append(joinPoint.getArgs()[0]);
		
		File fetchedFile = (File) joinPoint.proceed();
		logBuilder.append("\n\tFETCHED: File(").append(fetchedFile.getPath()).append(")");
		logger.logDebug(logBuilder.toString());
		
		return fetchedFile;
		
	}
		
	

}
