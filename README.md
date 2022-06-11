TestNG with Selenium - Assessment
Details of the Project:
The Project uses JAVA, Selenium and TestNG framework.
Created a Simple Maven Projects
Downloaded the following dependencies
Selenium-Java
Seleium-Test-NG
Maven Compiler Plugin
Maven-surefire plugin
Update the POM.xml to download all the mentioned plugins above.*
Under src/test/java - created the test cases package
Under that package created three test classes for the asked scenarios
In each test case class, updated the Username and Accesskey to run the test cases in remote WebDriver and set the desired capabilities.
To run the test classes in parallel provide the below command in terminal
mvn test -D suite=parallel.xml
Gitpod config
With Gitpod, you can open any repository by prefixing its URL with gitpod.io/# - no configuration necessary. This is great to review code or quickly browse an open source project in a familiar environment where your favourite keyboard shortcuts are available.
Here is the Gitpod url for my repo - gitpod.io/#https://github.com/anitanitturi/LambdatestCertification/tree/master
Then open Workspace if not already opened.
Then go to Terminal and run the command mvn install -Dsuite=parallel.xml
