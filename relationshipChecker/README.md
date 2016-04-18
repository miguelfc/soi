# Relationship Checker

## Description
This application checks the CIRelationship table in SOI database and calculates the number of times an element will be
painted in the console tree. It will report the top 30 elements, and generate a CSV file with the number of 
repetitions for all elements.

## Prerequisites
You need to add the SQL Server JDBC driver to your local maven repository. You can do it using the following command:
```
mvn install:install-file -Dfile=sqljdbc42.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc42 -Dversion=4.2 -Dpackaging=jar
```

This assumes that the filename is named sqljdbc42.jar. This utility was tested using version 4.2 but 
it should work with other versions as well.

## Build

You will need maven (and of course Java) in order to build the application. Go into the main directory and execute 
```
mvn clean package
```
Then, in the `target` directory you will have a jar file with a name similar to `relationshipChecker-1.0.0-RELEASE.jar`. Create a `lib`
directory inside the main one and copy the jar file there.

## Installation

1. Create a directory where you will install the application. For example: `D:\CA\relationshipChecker`.
2. Copy the following directories from your build path to the destination directory:
  * lib
  * config

3. Edit the following files and substitute the example paths with the ones in your environment (e.g., Java path, 
application directory), as well as the username and log file destination.
  * config/config.properties
  * config/log4j.properties
  * bin/run.bat
  
## Usage

To execute it, run the script `bin/run.bat` from the command line.

```
$ run.bat
2016-04-18 15:12:50 INFO  Main:main: - Executing relationship checker...
2016-04-18 15:12:52 INFO  Main:main: - Connected.
2016-04-18 15:12:55 INFO  Main:main: - The Top 30 of repeated items on the tree are:
2016-04-18 15:12:55 INFO  Main:main: -  ID      Count
2016-04-18 15:12:55 INFO  Main:main: -  82632   7344
2016-04-18 15:12:55 INFO  Main:main: -  82633   7344
2016-04-18 15:12:55 INFO  Main:main: -  82634   7344
2016-04-18 15:12:55 INFO  Main:main: -  82635   7344
2016-04-18 15:12:55 INFO  Main:main: -  82636   7344
2016-04-18 15:12:55 INFO  Main:main: -  82637   7344
2016-04-18 15:12:55 INFO  Main:main: -  82638   7344
2016-04-18 15:12:55 INFO  Main:main: -  82639   7344
2016-04-18 15:12:55 INFO  Main:main: -  82640   7344
2016-04-18 15:12:55 INFO  Main:main: -  82641   7344
2016-04-18 15:12:55 INFO  Main:main: -  82642   7344
2016-04-18 15:12:55 INFO  Main:main: -  82643   7344
2016-04-18 15:12:55 INFO  Main:main: -  82644   7344
2016-04-18 15:12:55 INFO  Main:main: -  82645   7344
2016-04-18 15:12:55 INFO  Main:main: -  82646   7344
2016-04-18 15:12:55 INFO  Main:main: -  82647   7344
2016-04-18 15:12:55 INFO  Main:main: -  82648   7344
2016-04-18 15:12:55 INFO  Main:main: -  82649   7344
2016-04-18 15:12:55 INFO  Main:main: -  43017   5133
2016-04-18 15:12:55 INFO  Main:main: -  54566   5133
2016-04-18 15:12:55 INFO  Main:main: -  54567   5133
2016-04-18 15:12:55 INFO  Main:main: -  54568   5133
2016-04-18 15:12:55 INFO  Main:main: -  54569   5133
2016-04-18 15:12:55 INFO  Main:main: -  32315   3865
2016-04-18 15:12:55 INFO  Main:main: -  32316   3865
2016-04-18 15:12:55 INFO  Main:main: -  32317   3865
2016-04-18 15:12:55 INFO  Main:main: -  32318   3865
2016-04-18 15:12:55 INFO  Main:main: -  32319   3865
2016-04-18 15:12:55 INFO  Main:main: -  32320   3865
2016-04-18 15:12:55 INFO  Main:main: -  32321   3865
2016-04-18 15:12:55 INFO  Main:main: - You can find the full report in the log directory, in the report.csv file.
2016-04-18 15:12:55 INFO  Main:main: - Finished
```

## Uninstallation 

Simply remove the directory where you copied everything.



