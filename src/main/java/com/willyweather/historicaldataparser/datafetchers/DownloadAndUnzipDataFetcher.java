package com.willyweather.historicaldataparser.datafetchers;

import java.io.File;
import java.io.IOException;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher;

/**
 * This class downloads and unzips data
 */
public class DownloadAndUnzipDataFetcher implements IWeatherDataFetcher {
	
	private String intermediateFileDestination;
	private IWeatherDataFetcher downloader;
	private IWeatherDataFetcher unzipper;
	
	/**
	 * Constructor
	 * @param intermediateFileDestination location where downloaded data is placed before it is unzipped
	 * @param downloader fetcher responsible for downloading data
	 * @param unzipper fetcher responsible for unzipping data
	 */
	public DownloadAndUnzipDataFetcher(String intermediateFileDestination,IWeatherDataFetcher downloader,IWeatherDataFetcher unzipper) {
		this.intermediateFileDestination = intermediateFileDestination;
		this.downloader = downloader;
		this.unzipper = unzipper;
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#fetchData(java.lang.String, java.lang.String)
	 */
	@Override
	public File fetchData(String source, String destination) throws IOException {
		
		String zippedDataDestination = this.intermediateFileDestination;
				
		File zippedData = this.downloader.fetchData(source, zippedDataDestination);
		File fetchedData = this.unzipper.fetchData(zippedData.getPath(), destination);
		
		return fetchedData;
				
	}

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataFetcher#canFetch(java.lang.String)
	 */
	@Override
	public boolean canFetch(String source) {
		return this.downloader.canFetch(source) && this.unzipper.canFetch(intermediateFileDestination);
	}

}
