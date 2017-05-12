This project was created and submitted as a solution for the Willy Weather Technical Exercise.

# PROJECT CONTENT

## A. Exercise Specification

The aim of this project is to download open source historical weather data and parse the data out of the file and put it in a
Java bean. The Deliverable will be a jar that can be launched from the command line. The project will be judged by the
code delivered and the design implemented. 

### Steps

All the steps below need to be accomplished via java code.

  1. Download source file: **https://drive.google.com/open?id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg&authuser=0**
  2. **Unzip** the file using any Java API.
  3. This is how the file looks:
  
```  
STN--- WBAN   YEARMODA    TEMP       DEWP      SLP        STP       VISIB      WDSP     MXSPD   GUST    MAX     MIN   PRCP   SNDP   FRSHTT
007034 99999  20121104    85.5  7    38.7  7  9999.9  0  9999.9  0  999.9  0    1.2  7    5.1  999.9    91.4*   78.8*  0.00I 999.9  000000
007034 99999  20121105    84.7  4    34.7  4  9999.9  0  9999.9  0  999.9  0    1.5  4    1.9  999.9    91.4*   75.2*  0.00I 999.9  000000
```

  4. Create a bean that stores all the info from the above file. The **fields** of the bean are the **columns** in the file above. Each **row** in the file is one **Pojo**.
  5. The user should be able to run the code from the command line using **java -jar TestProject.jar**
  6. The user specifies a parameter called "field" from the command line. (e.g. java -jar TestProject.jar <**field**>). The **field** can be  EMP or DEWP. Depending on the **field** the code needs to call a **processor**. (so if field is **TEMP** then the processor is **TempProcessor**). Each processor has a method called **process**. For the test project, the process method prints the field values from all Pojo’s. So the TempProcessor would print 86,85 (values rounded, see 7 below)
  7. The processor needs to **round up the values to the nearest integer**. However, the method to round the values needs to be abstracted to a common class from which all processors can extend.
  8. Use **Spring** to implement the project. All steps (unzip, processing) should be registered as beans. Log all methods using **Aspects**. The processing of input and output streams needs to be handle in a fail safe manner. Also, emphasis is placed on well formatted, clean and commented code.

## B. Implementation

### 1. The Procedure:

  - This initial implementation treats the steps as a procedure:
   
  1. **Data Fetching** - fetching of data from a specific source
  2. **Data Parsing** - parsing of fetched data
  3. **Data Processing** - processing of parsed data
  
  - This procedure object is fully configurable in a Spring XML configuration file, and may injected with desired executor objects such as data fetcher, data parser, and data processor.

### 2. The Executors:

  1. **Data Fetchers** - objects that fetch data from a specific source and may be injected to a procedure so that it is executed during the procedure's Data Fetching step
  2. **Data Parsers** - objects that parse data from a specific fetch destination location, and may be injected to a procedure so that it is executed during the procedure's Data Parsing step
  3. **Data Processors** - objects that process parsed data, andt may be injected to a procedure so that it is executed during the procedure's Data Processing step
  4. **Loggers** - objects that log information on a specific location 
  5. **Aspects** - objects used for intercepting data in methods (currently used for logging)
  
### 3. Logging Aspects:

  1. **Method Tracer** - logs method execution flow
  2. **Exception Tracer** - logs thrown exceptions
  3. **Fetched Data Tracer** - logs data fetching information: source, destination, and target data
  4. **Parsed Weather Data Tracer** - logs parsed data (may be part or full)
  5. **Data Processing Tracer** - logs processor involved in the data processing step
  
### 4. Limitations:

  - This initial implementation sticks to the specification such that:
   
  1. fetching is limited to downloading the specified target file;
  2. parsing is limited to the given format
  3. logging is limited to the system output (console)
  
  - Nevertheless, implementation is created such that the program can be configurable with new data fetchers, parsers, and processors.  

# TESTING THIS PROJECT

## A. Pre-requisites

### 1. Java (JDK 7 or higher, JDK 8 preferred) 
  - Installation instructions found [here](http://docs.oracle.com/javase/7/docs/webnotes/install/windows/jdk-installation-windows.html).
### 2. Maven 
  - Installation instructions found [here](https://maven.apache.org/install.html).
### 3. Git 
  - Installation instructions found [here](https://www.atlassian.com/git/tutorials/install-git).
	
NOTE: In order to use command line for this project, make sure that environment variables Path, JAVA_HOME, M2_HOME, etc are configured correctly

## B. Running the tests (Command Line or Bash (e.g. GitBash))

### 1. Clone this project
  - $ git clone https://github.com/bjldb/willyweather-test.git
### 2. Move to the project directory (willyweather-test) and run maven install
  - $ cd willyweather-test
  - $ mvn clean install
### 2. Move to the target directory (willyweather-test/target) and run jar with desired field e.g.
  - $ java -jar TestProject.jar **<field>**
  
  - Sample output:
```
$ java -jar TestProject.jar MXSPD

TRACE: SourceDestination.getSource() --> args: []
TRACE: SourceDestination.getDestination() --> args: []
uc?export=download&amp;id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg
TRACE: MxspdProcessor.toString() --> args: []
TRACE: HistoricalDataParsingProcedure.processWeatherData() --> args: [com.willyweather.historicaldataparser.dataprocessors.MxspdProcessor@171beb3]
TRACE: DownloadAndUnzipDataFetcher.fetchData() --> args: [https://drive.google.com/uc?export=download&id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg, target/data/historicaldata.dat]
TRACE: DownloadDataFetcherDelegator.fetchData() --> args: [https://drive.google.com/uc?export=download&id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg, target/historicaldata.gz]
FETCH EVENT:
        CLASS: DownloadDataFetcherDelegator
        SOURCE: https://drive.google.com/uc?export=download&id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg
        FETCHED: File(target\historicaldata.gz)
TRACE: UnzipDataFetcherDelegator.fetchData() --> args: [target\historicaldata.gz, target/data/historicaldata.dat]
FETCH EVENT:
        CLASS: UnzipDataFetcherDelegator
        SOURCE: target\historicaldata.gz
        FETCHED: File(target\data\historicaldata.dat)
FETCH EVENT:
        CLASS: DownloadAndUnzipDataFetcher
        SOURCE: https://drive.google.com/uc?export=download&id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg
        FETCHED: File(target\data\historicaldata.dat)
TRACE: WeatherDataParserImpl.parseWeatherData() --> args: [target/data/historicaldata.dat]
PARSED WEATHER DATA:
        #0: STN(007034) | WBAN(99999) | YEARMODA(20121104)
        #1: STN(007034) | WBAN(99999) | YEARMODA(20121105)
TRACE: MxspdProcessor.process() --> args: [[com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData@1bb5034, com.willyweather.historicaldataparser.appcore.models.HistoricalWeatherData@1741d6d]]
DATA PROCESS EVENT >>> Processor: MxspdProcessor

MXSPD: 6,2
```
  
# ISSUES REGARDING THIS PROJECT

- Kindly contact me via bjl.deborja@yahoo.com.