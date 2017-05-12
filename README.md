This project was created and submitted as a solution for the Willy Weather Technical Exercise.

# PROJECT CONTENT

## A. Exercise Specification

The aim of this project is to download open source historical weather data and parse the data out of the file and put it in a
Java bean. The Deliverable will be a jar that can be launched from the command line. The project will be judged by the
code delivered and the design implemented. 

### Steps

All the steps below need to be accomplished via java code.

  1. Download source file: **https://drive.google.com/open?id=0B0QtYwzM6bVAZGNzUEhjeU9XRjg&authuser=0**
  2. Unzip the file using any Java API.
  3. This is how the file looks:
STN--- WBAN   YEARMODA    TEMP       DEWP      SLP        STP       VISIB      WDSP     MXSPD   GUST    MAX     MIN   PRCP   SNDP   FRSHTT
007034 99999  20121104    85.5  7    38.7  7  9999.9  0  9999.9  0  999.9  0    1.2  7    5.1  999.9    91.4*   78.8*  0.00I 999.9  000000
007034 99999  20121105    84.7  4    34.7  4  9999.9  0  9999.9  0  999.9  0    1.5  4    1.9  999.9    91.4*   75.2*  0.00I 999.9  000000
  4. Create a bean that stores all the info from the above file. The fields of the bean are the columns in the file above. Each row in the file is one Pojo.
  5. The user should be able to run the code from the command line using "java jar TestProject.jar"
  6. The user specifies a parameter called "field" from the command line. (e.g. java jar TestProject.jar Dfield="TEMP"). The field can be  EMP or DEWP. Depending on the field the code needs to call a processor. (so if field is TEMP then the processor is TempProcessor). Each processor has a method called process. For the test project, the process method prints the field values from all Pojo’s. So the TempProcessor would print 86,85 (values rounded, see 7 below)
  7. The processor needs to round up the values to the nearest integer. However, the method to round the values needs to be abstracted to a common class from which all processors can extend.
  8. Use **Spring** to implement the project. All steps (unzip, processing) should be registered as beans. Log all methods using **Aspects**. The processing of input and output streams needs to be handle in a fail safe manner. Also, emphasis is placed on well formatted, clean and commented code.

### 2. Special Offers and Promotions

  * A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month.
  * The Unlimited 5GB Sim will have a bulk discount applied; whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.
  * We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.
  * Adding the promo code 'I<3AMAYSIM' will apply a 10% discount across the board.

## B. Implementation

This initial implementation sticks to the specified interface and may lack other functionality such as item removal, quantity indication, etc.

### 1. Specification:

* cart = ShoppingCart.new(pricingRules)
* cart.add(item1)
* cart.add(item2, promo_code)
* cart.total
* cart.items

### 2. Implementation:

* public class ShoppingCart
  - public ShoppingCart(PricingRules pricingRules) {...}
  - public void add(CatalogueProduct item) {...}
  - public void add(CatalogueProduct item, String promoCode) {...}
  - public Double total() {...}
  - public Map<CatalogueProduct,Integer> items() {...}

### 3. Pricing Rules:

- Pricing Rules are used to compute total price and modify cart based on bundling rules. 
- Pricing Rule instances are created independently and may be added to or excluded from the rule set independently as well.

- Pricing Rules are categorized as:

  * Bundling Rule - rules modifying cart based on bundling-related offers and promos
  * Computation Rule - rules modifying computation of prices based on offers and promos
  * Finalization Rules - rules modifying final cart total based on offers and promos 

## C. Limitations

1. As stated above, initial implementation sticks to the specified interface.
2. This project is created to be imported into a bigger project incorporating the shopping cart; thus, this is NOT a runnable project. Nevertheless, tests have been created by scenarios and may be run.
3. Due to time constraints, tests used for TDD are mostly coming from the exercise specification.   

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
  - $ git clone https://github.com/bjldb/amaysim-test.git
### 2. Move to the project directory (amaysim-test) and run maven test
  - $ mvn clean test
  - Sample output:
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.amaysim.shoppingcart.ShoppingCartTest
Scenario 1: A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, you will pay the price of 2 only for the first month.
==============================
ITEMS ADDED:
==============================
3 x Unlimited 1GB
1 x Unlimited 5GB
==============================
RESULTING CART ITEMS:
==============================
3 x Unlimited 1GB
1 x Unlimited 5GB
==============================
CART TOTAL: $94.70
==============================

Scenario 2: The Unlimited 5GB Sim will have a bulk discount applied; whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.
==============================
ITEMS ADDED:
==============================
2 x Unlimited 1GB
4 x Unlimited 5GB
==============================
RESULTING CART ITEMS:
==============================
2 x Unlimited 1GB
4 x Unlimited 5GB
==============================
CART TOTAL: $209.40
==============================

Scenario 3: We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.
==============================
ITEMS ADDED:
==============================
1 x Unlimited 1GB
2 x Unlimited 2GB
==============================
RESULTING CART ITEMS:
==============================
1 x Unlimited 1GB
2 x Unlimited 2GB
2 x 1 GB Data-pack
==============================
CART TOTAL: $84.70
==============================

Scenario 4: Adding the promo code 'I<3AMAYSIM' will apply a 10% discount across the board.
==============================
ITEMS ADDED:
==============================
1 x Unlimited 1GB
1 x 1 GB Data-pack
'I<3AMAYSIM' Promo Applied
==============================
RESULTING CART ITEMS:
==============================
1 x Unlimited 1GB
1 x 1 GB Data-pack
==============================
CART TOTAL: $31.32
==============================

Scenario 5: All 1 GB Data-pack paired with any ordered Unlimited 2GB are free.
==============================
ITEMS ADDED:
==============================
1 x Unlimited 1GB
1 x Unlimited 2GB
1 x 1 GB Data-pack
==============================
RESULTING CART ITEMS:
==============================
1 x Unlimited 1GB
1 x Unlimited 2GB
1 x 1 GB Data-pack
==============================
CART TOTAL: $54.80
==============================

Scenario 6: Pay only for 1 GB Data-pack not paired with any ordered Unlimited 2GB.
==============================
ITEMS ADDED:
==============================
1 x Unlimited 1GB
1 x Unlimited 2GB
2 x 1 GB Data-pack
==============================
RESULTING CART ITEMS:
==============================
1 x Unlimited 1GB
1 x Unlimited 2GB
2 x 1 GB Data-pack
==============================
CART TOTAL: $64.70
==============================

Scenario 7: Promo Code not existing.
==============================
ITEMS ADDED:
==============================
1 x Unlimited 1GB
1 x 1 GB Data-pack
'I<3AMAYSING' Promo Applied
==============================
RESULTING CART ITEMS:
==============================
1 x Unlimited 1GB
1 x 1 GB Data-pack
==============================
CART TOTAL: $34.80
==============================

Scenario 8: No Offers or Promotions
==============================
ITEMS ADDED:
==============================
3 x Unlimited 1GB
2 x Unlimited 2GB
4 x Unlimited 5GB
2 x 1 GB Data-pack
==============================
RESULTING CART ITEMS:
==============================
3 x Unlimited 1GB
2 x Unlimited 2GB
4 x Unlimited 5GB
2 x 1 GB Data-pack
==============================
CART TOTAL: $333.90
==============================

Scenario 9: Empty Cart
==============================
ITEMS ADDED:
==============================
==============================
RESULTING CART ITEMS:
==============================
==============================
CART TOTAL: $0.00
==============================

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.094 sec

Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.067 s
[INFO] Finished at: 2017-04-26T12:43:39+08:00
[INFO] Final Memory: 13M/32M
[INFO] ------------------------------------------------------------------------
```
  
# ISSUES REGARDING THIS PROJECT

- Kindly contact me via bjl.deborja@yahoo.com.