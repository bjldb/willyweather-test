package com.willyweather.historicaldataparser.appcore.models;

/**
 * This class encapsulates a general-purpose source-destination information pair
 */
public class SourceDestination {
	
	private String source;
	private String destination;
	
	/**
	 * Constructor
	 * @param source source information
	 * @param destination destination information
	 */
	public SourceDestination(String source,String destination) {
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Gets source information
	 * @return source information
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Gets destination information
	 * @return destination information
	 */
	public String getDestination() {
		return destination;
	}

}
