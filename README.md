## Required software
* Java JDK 11+
* Maven installed and in your classpath

## How to execute the tests
You can open each test class on `src\test\java` and execute all of them, but I recommend you run it by the
command line. It enables us to run in different test execution environments

### Running the test suites

The test suites can be run directly by your IDE or by command line.
If you run `mvn test` all the tests will execute because it's the regular Maven lifecycle to run all the tests.

To run different suites based on the groups defined for each test you must inform the property `-Dgroups` and the group names.
The example below shows how to run the test for each pipeline stage:

|           command           |
|-----------------------------|
| mvn test -Dgroups="Playlist"|

### Generating the test report

This project uses Allure Report to automatically generate the test report.
There are some configuration to make it happen:
* aspectj configuration on `pom.xml` file
* `allure.properties` file on `src/test/resources`

You can use the command line to generate it in two ways:
* `mvn allure:serve`: will open the HTML report into the browser
* `mvn allure:report`: will generate the HTML port at `target/site/allure-maven-plugin` folder

## About the Project Structure
    .
    ├── src/main/java
    |   ├──api/client
    |   ├──api/Pojos
    |   ├──api/Routes
    |   ├──utils/manager
    |   ├──utils/configurations 
    |   ├──utils/fakers  
    ├── src/main/resources
    |   ├──.properties
    ├── src/test/java
    └   ├──tests

### src/main/java
#### api/client
Classes that do some actions in specific endpoints.

#### api/Pojos
POJOs classes can be used to simplify testing. By creating POJOs to represent the request or response json

#### api/Routes
Contains classes for each endpoint which has any hardcoded routes

#### utils/manager
Classes to Handle Generic Actions needed like (TokenCreation or renewing , setting HTTP requests "post,get,update" , request and response builder)

#### utils/configurations
Handle property configuration files used

#### utils/fakers
used to create fakedata "normally it shouldn't be used cause in testing u need to know what you are passing to test and not using fake ones but u can use it for unit testing to test functionality only

### src/main/resources
has all properties files needed like (environment conf,allure)

### src/test/java

#### tests
Classes need to be tested

## Libraries
* [RestAssured](http://rest-assured.io/) library to test REST APIs
* [java-faker](https://github.com/DiUS/java-faker) to generate fake data
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging strategy
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy

## Patterns applied
* singleton design pattern
* Builder
* Request and Response Specification
