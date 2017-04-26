package com.willyweather.historicaldataparser.appcore.procedure;

import java.io.IOException;
import java.util.List;

import com.willyweather.historicaldataparser.appcore.interfaces.ILogger;
import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher;
import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataParser;
import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataProcessor;
import com.willyweather.historicaldataparser.appcore.loggers.DefaultLogger;
import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;
import com.willyweather.historicaldataparser.appcore.models.SourceDestination;

/**
 * This class executes the steps in fetching, parsing, and processing historical weather data 
 */
public class HistoricalDataParsingProcedure {
	
	private String fetchDataSource;
	private String fetchedDataLocation;
	private IWeatherDataFetcher dataFetcher;
	private IWeatherDataParser weatherDataParser;
	private ILogger logger;
	
	private boolean isInitialized;
	private	List<HistoricalWeatherData> weatherDataList;
	
	/**
	 * Constructor
	 * @param srcDest source and destination information pair for fetching historical weather data
	 * @param dataFetcher object responsible for fetching historical weather data
	 * @param weatherDataParser object responsible for parsing historical weather data
	 */
	public HistoricalDataParsingProcedure(SourceDestination srcDest,IWeatherDataFetcher dataFetcher,IWeatherDataParser weatherDataParser) {
		this.fetchDataSource = srcDest.getSource();
		this.fetchedDataLocation = srcDest.getDestination();
		this.dataFetcher = dataFetcher;
		this.weatherDataParser = weatherDataParser;
		this.logger = new DefaultLogger();
	}
	
	/**
	 * Sets logger for this HistoricalDataParsingProcedure instance
	 * @param logger logger for this HistoricalDataParsingProcedure instance
	 */
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
	/**
	 * Processes fetched historical weather data given the processor 
	 * @param processor processor to process historical weather data
	 */
	public void processWeatherData(IWeatherDataProcessor processor) {
		
		try {
			
			if( !this.isInitialized ) {
				this.initialize();
			}
			
			processor.process(this.weatherDataList);
			
		} catch (IOException ex) {

			logger.logError("Error: " + ex.toString());
			
		}

	}
	
	/**
	 * Prepares this procedure for processing by fetching and parsing historical weather data
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		
		dataFetcher.fetchData(this.fetchDataSource,this.fetchedDataLocation);
        this.weatherDataList = weatherDataParser.parseWeatherData(this.fetchedDataLocation);
        
        this.isInitialized = true;
	        
	}
	

}
