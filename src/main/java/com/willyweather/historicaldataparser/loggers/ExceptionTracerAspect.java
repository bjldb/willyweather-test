package com.willyweather.historicaldataparser.loggers;

import org.aspectj.lang.JoinPoint;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;

/**
 * This class is an ASPECT for tracing exceptions thrown within the program
 */
public class ExceptionTracerAspect {
	
	private ILogger logger;
	
	/**
	 * Constructor
	 * @param logger logger for this ASPECT
	 */
	public ExceptionTracerAspect(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * (Advice) Logs exceptions thrown within the program
	 * @param joinPoint join point for this aspect's advice
	 * @param error Throwable thrown within the program
	 */
	public void logThrownException(JoinPoint joinPoint, Throwable error) {
		StringBuilder logBuilder = new StringBuilder("EXCEPTION THROWN: ");
		logBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
		logBuilder.append(".").append(joinPoint.getSignature().getName());
		logBuilder.append("() --> exception:\n").append(error.toString());
		logger.logDebug(logBuilder.toString());
	}

}
