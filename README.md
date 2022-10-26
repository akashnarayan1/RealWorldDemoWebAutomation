# RealWorldDemoWebAutomation
Test plan doc - https://docs.google.com/document/d/1jSRcDBTEdsHrdrN9htFB9KJ01S045LDgmdGAaCR4oRo/edit?usp=sharing (Work in progress)

# Selenium Cucumber Framework

---

## Project Purpose
This project aims to give an inspiring or sample of automation test framework that uses Selenium and Cucumber with Java as the programming language.
This project uses a medium clone web application https://demo.realworld.io/#/


## Tools and Libraries
This project using 2 main tools, Selenium and Cucumber.
Other tools include - Maven, Log4J, Extent Reports, Faker, etc.
On the other hand, I using some of the tools that support this great framework.
The complete list of tools, you can see in the `pom.xml` file.

## Requirements
* Java Development Kit
* Maven
* WebDriver, using ChromeDriver

## Running Tests
* Clone the repository from your fork to this directory
* Open the project using any Java IDE
* Run the tests with the script below
```shell
$ mvn clean install
```
* If you want to run the specific test, use the cucumber tags like this
```shell
$ mvn clean install -Dcucumber.filter.tags="@REPLACE_WITH_ANY_TAGS_THAT_YOU_WANT"
```

## Test Results
* Test report automatically generated on `reports` folder after finished the test execution
* See test report from `reports/ExtentReportResults.html`
* You can also share your Cucumber Report with another person at https://reports.cucumber.io, just go to `src/test/resources/cucumber.properties` then change the value to be `true`
```properties
cucumber.publish.enabled=true
```
* For more information about reports cucumber you can go to https://reports.cucumber.io/docs/cucumber-jvm

---

### References
* https://cucumber.io/docs/installation/java/
* https://www.selenium.dev/documentation/en/
* https://www.toolsqa.com/cucumber-automation-framework/
* https://www.w3schools.com/java/
* https://www.oracle.com/java/technologies/javase/codeconventions-introduction.html
* https://fakerjs.dev/
