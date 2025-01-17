# roadmap
Automation repo for Roadmap ERP Website

Prerequisites:
Ensure you have Eclipse installed (any version) with JDK version greater than 20, Maven and latest Chrome.

About framework:
For Roadmap ERP's Automation we used our Galaxy automation framework(EzyAutomate) using Java. We have used leading open source tools like Selenium, RestAssured, Extent Report and TestNG

Import the project in Ecplise as Maven project.
Right click on the project and Select run as option from the list
Select Maven build(choose 4th option)
To run it from eclipse use the command as "clean test -Dplatform=headless."
Note:
To execute the browser in Headful mode use "-Dplatform=chrome"