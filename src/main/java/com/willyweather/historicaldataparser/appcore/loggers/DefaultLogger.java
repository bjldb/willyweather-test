package com.willyweather.historicaldataparser.appcore.loggers;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;

/**
 * This class is the default logger of the core historical weather data parser
 */
public class DefaultLogger implements ILogger {

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logFatal(java.lang.String)
	 */
	@Override
	public void logFatal(String message) {
		this.logError(message);
	}
	
	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logError(java.lang.String)
	 */
	@Override
	public void logError(String message) {
		System.out.println(message);
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logWarn(java.lang.String)
	 */
	@Override
	public void logWarn(String message) {
		this.logError(message);
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logInfo(java.lang.String)
	 */
	@Override
	public void logInfo(String message) {
		System.out.println(message);
			
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logDebug(java.lang.String)
	 */
	@Override
	public void logDebug(String message) {
		this.logInfo(message);
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.ILogger#logTrace(java.lang.String)
	 */
	@Override
	public void logTrace(String message) {
		this.logInfo(message);
	}


}
