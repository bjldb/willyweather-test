package com.willyweather.historicaldataparser;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.willyweather.historicaldataparser.appcore.interfaces.IWeatherDataProcessor;
import com.willyweather.historicaldataparser.appcore.models.WeatherDataContentFieldsEnum;
import com.willyweather.historicaldataparser.appcore.procedure.HistoricalDataParsingProcedure;

/**
 * This class is the application starting point
 */
public class App 
{
    public static int MIN_ARGUMENTS = 1;
	public static int FIELD_ARGUMENT_INDEX = 0;
    
    /**
     * Initiates this application
     * @param args passed arguments to the program
     */
    public static void main( String[] args )
    {   
  
    	try( ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml")) {

    		Resource x =context.getResource("https://drive.google.com/uc?export=download&amp;id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg");
    		System.out.println(x.getFilename());
    		
        	String fieldName = getUserProvidedFieldNameFromArgs(args);
        	
        	// get processor based as specified by user on -Dfield=<fieldname>
			String processorBeanName = fieldName.toLowerCase();
			IWeatherDataProcessor processor = context.getBean(processorBeanName, IWeatherDataProcessor.class);
			   
			// instantiate procedure
			HistoricalDataParsingProcedure proc = context.getBean("procedure", HistoricalDataParsingProcedure.class);
			
			// collect and process weather data using retrieved processor
			proc.processWeatherData(processor);
            
        } catch (BeansException bex) {
        	
        	System.err.println("Configuration error: " + bex.getMessage());
        	
        } catch (IllegalArgumentException argex) {
        	
        	System.err.println(argex.getMessage());
        	
        } 
		
    }	
    
    /**
     * Extracts user-provided field name to process from program arguments
     * @param args program arguments where field name shall be extracted
     * @return the extracted user provided field name
     * @throws IllegalArgumentException if user-provided field name does not exist or is not provided
     */
    private static String getUserProvidedFieldNameFromArgs(String[] args) throws IllegalArgumentException {
    	
       	if(args.length < MIN_ARGUMENTS) {
			throw new IllegalArgumentException(getProgramUsageString());
    	}
       	
    	String fieldname = args[FIELD_ARGUMENT_INDEX];
    	validateProvidedField(fieldname);
    	
		return fieldname;
	}

	/**
     * Validates provided field name if it exists in this version of historical weather data or not
     * @param fieldname field name to validate
     * @throws IllegalArgumentException if field name is invalid
     */
    private static void validateProvidedField(String fieldname) throws IllegalArgumentException {
    	
    	try {
    		
	    	WeatherDataContentFieldsEnum enumConst = WeatherDataContentFieldsEnum.valueOf(fieldname.toUpperCase());
	
	    	if(enumConst.name().startsWith("_")) {
	    		throw new IllegalArgumentException();
	    	}
	    	
    	} catch (NullPointerException | IllegalArgumentException ex) {
    		
			StringBuilder exceptionBuilder = new StringBuilder("Error fetching processor for field:\n");
			exceptionBuilder.append("'").append(fieldname).append("' field does NOT exist in this implementation.\n");
			exceptionBuilder.append(getProgramUsageString());
			throw new IllegalArgumentException(exceptionBuilder.toString());
    		
    	}
    }
    
    /**
     * Generates the program usage ("help") string
     * @return generated the program usage ("help") string
     */
    private static String getProgramUsageString() {
    	
    	StringBuilder usageStringBuilder = new StringBuilder("USAGE: java -jar TestProject.jar <field name>\n");

		usageStringBuilder.append("See possible field names:\n");
		usageStringBuilder.append(WeatherDataContentFieldsEnum.getValuesForDisplay());
		
		return usageStringBuilder.toString();
    }
    
}
