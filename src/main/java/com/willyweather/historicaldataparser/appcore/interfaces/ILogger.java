package com.willyweather.historicaldataparser.appcore.interfaces;

/**
 * This interface represents logging capabilities
 */
public interface ILogger {
	
	/**
	 * Logs messages for events related to programmatic "fatal" situations
	 * @param message message to log
	 */
	public abstract void logFatal(String message);
	
	/**
	 * Logs messages for error-related events
	 * @param message message to log
	 */
	public abstract void logError(String message);
	
	/**
	 * Logs warning messages
	 * @param message message to log
	 */
	public abstract void logWarn(String message);
	
	/**
	 * Logs general informative messages
	 * @param message message to log
	 */
	public abstract void logInfo(String message);
	
	/**
	 * Logs messages used for debugging
	 * @param message message to log
	 */
	public abstract void logDebug(String message);
	
	/**
	 * Logs messages for tracing
	 * @param message message to log
	 */
	public abstract void logTrace(String message);

}
