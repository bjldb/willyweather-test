package com.willyweather.historicaldataparser.appcore.models;

/**
 * This enum represents the fields within weather data
 */
public enum WeatherDataContentFieldsEnum {

	STN,	
	WBAN,	
	YEARMODA,	
	TEMP,	_TEMP,	
	DEWP,	_DEWP,
	SLP,	_SLP,	
	STP,	_STP,	
	VISIB,	_VISIB,	
	WDSP, 	_WDSP,
	MXSPD,	
	GUST,	
	MAX,		
	MIN,	
	PRCP,	
	SNDP,	
	FRSHTT;

	/**
	 * Generates a String of this enum's values 
	 * @return generated String of this enum's values 
	 */
	public static String getValuesForDisplay() {
		
		StringBuilder valuesString = new StringBuilder();
		
		for( WeatherDataContentFieldsEnum field : values() ) {
			
			if(!field.name().contains("_")) {
				valuesString.append(field.name()).append("\n");
			}
			
		}
		
		return valuesString.toString(); 
	}

}
