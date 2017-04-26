package com.willyweather.historicaldataparser.dataparsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataParser;
import com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData;
import com.willyweather.historicaldataparser.appcore.models.WeatherDataContentFieldsEnum;
import com.willyweather.historicaldataparser.util.FileOperationsUtil;

/**
 * This class parses historical weather data from a given location
 */
public class WeatherDataParserImpl implements IWeatherDataParser {

	/* (non-Javadoc)
	 * @see com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataParser#parseWeatherData(java.lang.String)
	 */
	@Override
	public List<HistoricalWeatherData> parseWeatherData(String weatherDataLoaction) throws IOException {

		List<HistoricalWeatherData> weatherDataList = new ArrayList<>();
		
		String ignoredLineStarter = "STN";
		
		FileOperationsUtil.parseFileByLine(new File(weatherDataLoaction), ignoredLineStarter, (fileLine) -> {
			weatherDataList.add(this.createWeatherDataFromLine(fileLine));
		}); 
		
		return weatherDataList;
		
	} 
	
	/**
	 * Creates instance of HistoricalWeatherData given a line parsed from the historical weather data file 
	 * @param line line parsed from the historical weather data file representing one entry of historical weather data
	 * @return newly created instance of HistoricalWeatherData
	 * @throws IOException 
	 */
	private HistoricalWeatherData createWeatherDataFromLine(String line) throws IOException {
		
		HistoricalWeatherData weatherData = new HistoricalWeatherData();
	
    	String[] content = line.split("\\s+");
    	
    	if( content.length != WeatherDataContentFieldsEnum.values().length ) {
    		StringBuilder errorBuilder = new StringBuilder("Number of parsed field content ");
    		errorBuilder.append("(").append(content.length).append(" fields)");
    		errorBuilder.append(" differs from this implementation's expected number ");
    		errorBuilder.append("(").append(WeatherDataContentFieldsEnum.values().length).append(" fields).\n");
    		errorBuilder.append("Data may be corrupted or for another version.");
    		throw new IOException(errorBuilder.toString());
    	}

    	weatherData.setStn(content[WeatherDataContentFieldsEnum.STN.ordinal()]);
    	weatherData.setWban(content[WeatherDataContentFieldsEnum.WBAN.ordinal()]);
    	weatherData.setYearmoda(content[WeatherDataContentFieldsEnum.YEARMODA.ordinal()]);
    	weatherData.setTemp(content[WeatherDataContentFieldsEnum.TEMP.ordinal()]);
    	weatherData.setDewp(content[WeatherDataContentFieldsEnum.DEWP.ordinal()]);
    	weatherData.setSlp(content[WeatherDataContentFieldsEnum.SLP.ordinal()]);
    	weatherData.setStp(content[WeatherDataContentFieldsEnum.STP.ordinal()]);
    	weatherData.setVisib(content[WeatherDataContentFieldsEnum.VISIB.ordinal()]);
    	weatherData.setWdsp(content[WeatherDataContentFieldsEnum.WDSP.ordinal()]);
    	weatherData.setMxspd(content[WeatherDataContentFieldsEnum.MXSPD.ordinal()]);
    	weatherData.setGust(content[WeatherDataContentFieldsEnum.GUST.ordinal()]);
    	weatherData.setMax(content[WeatherDataContentFieldsEnum.MAX.ordinal()]);
    	weatherData.setMin(content[WeatherDataContentFieldsEnum.MIN.ordinal()]);
    	weatherData.setPrcp(content[WeatherDataContentFieldsEnum.PRCP.ordinal()]);
    	weatherData.setSndp(content[WeatherDataContentFieldsEnum.SNDP.ordinal()]);
    	weatherData.setFrshtt(content[WeatherDataContentFieldsEnum.FRSHTT.ordinal()]);
    	
    	return weatherData;
	}
	

	
	

}
