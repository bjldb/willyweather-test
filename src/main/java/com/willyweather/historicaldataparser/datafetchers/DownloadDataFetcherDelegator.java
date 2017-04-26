package com.willyweather.historicaldataparser.datafetchers;

/**
 * This class is a delegator for downloader type of fetchers
 */
public class DownloadDataFetcherDelegator extends AbstractDataFetcherDelegator {

	{
		this.fetchers.add(new WebUrlDataFetcherImpl());
		// add other downloader data fetchers here
	}

}
