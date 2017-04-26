package com.willyweather.historicaldataparser.loggers;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;

/**
 * This class is an ASPECT for method tracing
 */
public class MethodTracerAspect {
	
	private ILogger logger;
	
	/**
	 * Constructor
	 * @param logger logger for this ASPECT
	 */
	public MethodTracerAspect(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * (Advice) Logs method invocation information for tracing purposes
	 * @param joinPoint join point for this aspect's advice
	 */
	public void trace(JoinPoint joinPoint) {
		StringBuilder logBuilder = new StringBuilder("TRACE: ");
		logBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
		logBuilder.append(".").append(joinPoint.getSignature().getName());
		logBuilder.append("() --> args: ").append(Arrays.toString(joinPoint.getArgs()));
		logger.logTrace(logBuilder.toString());
	}


}
