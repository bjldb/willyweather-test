package com.willyweather.historicaldataparser.datafetchers;

/**
 * This class is a delegator for unzipper type of fetchers
 */
public class UnzipDataFetcherDelegator extends AbstractDataFetcherDelegator {
	
	{
		this.fetchers.add(new GzipDataFetcherImpl());
		// add other unzipper data fetchers here
	}

}
