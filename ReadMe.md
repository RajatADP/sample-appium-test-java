## Downloads

* [pCloudy-connector-jar]
  =========== How to install pCloudy-Java-Connector.jar in local Maven Repository =============



Problem: This jar is not in Maven. So we need to install it locally in our Maven Repo,
so that other projects can reference it as a Maven Dependency

Steps:
----------- 

1. Download the required Jar
2. Open Terminal
3. cd to the directory which contains the Jar (Downloads Folder?)
4. Run the below command

###################################

mvn install:install-file -Dfile=pCloudy-java-connector-11.0.3-jar-with-dependencies.jar -DgroupId=pCloudy-java-connector -DartifactId=pCloudy-java-connector -Dversion=11.0.3 -Dpackaging=jar

###################################

5. Once the above step is completed and the jar is installed in your Maven, please add the following dependancy in your Maven Project

   	<dependency>
   		<groupId>pCloudy-java-connector</groupId>
   		<artifactId>pCloudy-java-connector</artifactId>
   		<version>11.0.3</version>
   	</dependency>

6. You should now be able to use the 'Connector' class in your project

## Getting Started

## Installation

Pre-equisites
- java 11
- appium 1.20.2
- android studio
- xcode
- xcode command line tools
- carthage
- https://github.com/appium/appium-inspector
- https://www.pcloudy.com/mobile-application-testing-documentation/index.php


## commands to install

- brew install openjdk@11
- npm install -g appium@1.20.2
- xcode-select --install
- brew install carthage, required for webdriver agent
- npm install -g appium-doctor

## Environment variables

- JAVA_HOME
- ANDROID_HOME
- vim ~/.zshrc
- source ~/.zshrc

## checks after installation

- appium -v
- java --version

## Run tests

- mvn test


## get simulator name
uuid for simulator -> instruments -s devices

## Kill appium server

ps -ax | grep emulator

kill PID

## Session error

adb uninstall io.appium.uiautomator2.server
adb uninstall io.appium.uiautomator2.server.test
adb uninstall io.appium.unlock
adb uninstall io.appium.settings









