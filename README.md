# StockBit Cucumber Selenium Testing

## Overview
Testing some features of StockBit.com using gerkhin scenarios in cucumber and using selenium for UI Automation Testing. Written in Java.

There are two feature file:
1. Login.feature
This test login features when input are valid or invalid

2. Comment.feature
This test comment features when posting or deleting comments

### Prerequisites
- Eclipse
- Maven
- Java 8
- Selenium
- Mozilla Firefox

In this project, you need to configure Eclipse and install Cucumber, JUNIT, Selenium, apache-commons depedencies.
For you to automatically install those depedency, you can use the pom.xml to build using maven and the depedency will automatically be downloaded.

### To run the tests
After the Eclipse environment is configured properly, you can run the test by doing these steps:

You need to go to Steps.java and change your username, password for it will be used in doing the login features scenario.

And lastly, to run the test you can just simply right click on the project and select run as JUNIT Test.
![image](https://user-images.githubusercontent.com/79439013/183265023-c5a16503-8bc9-4995-9908-168b3490810a.png)

Or you can just test each Features file by going into the .feature file and right click and run as Cucumber Feature.
![image](https://user-images.githubusercontent.com/79439013/183265297-c124de88-5f74-49bd-b0af-609c45896062.png)
