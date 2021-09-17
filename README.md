# CSVCombine

## Overview

 CSVCombine is a Java program which is designed to accept n number of [CSV-formatted files](https://datatracker.ietf.org/doc/html/rfc4180.html), and merge them into a single, unified CSV file. 
 
## File Requirements 
There are several requirements for a file (and it's records) to be accurately merged into the output file: 

* The file should be of a standard CSV type, and can optionally leverage quoted values. 
* Headers -- Each file should, at a minimum, contain the following headers cased and spelled exactly as follows: **Provider Name**, __CampaignID__, **Cost Per Ad Click**, **Redirect Link**, **Phone Number**, **Address**, **Zipcode**. Additional headers/fields can be included, but will be excluded from the merge. 
* Each field should follow the below formatting requirements:
1. Provider Name - Type: String, Non Nullable
2. CampaignID - Type: String, Non Nullable
3. Cost Per Ad Click - Type: Float, Non-Nullable
4. Redirect Link - Type: String, Non Nullable
5. Phone Number - Type: String, Nullable
6. Address - Type: String, Non Nullable
7. Zipcode - Type: String, Non Nullable

## Solution
CSVCombine merges files by reading files one a time and validating each row in the file as a successful candidate for merging. As part of the validation process, it attempts to sanitize/reformat any records which may be close to the correct schema, but aren't quite correct (Example -- A float object with trailing/leading whitespace). Once a row is validated, it is appended to the output file in CSV format (output.csv) in the directory from which the program was executed. 

#### Detailed Solution
Specifically, CSVCombine reads file names as specified at the command line when it is run:

`java CombineCSV file1.csv file2.csv filen.csv`

Files are read in one at a time through a Scanner Object, which then reads each record of a given file, and passes them to the parseLine() method for further processing. parseLine() acts as a sort of traffic controller, splitting the values of a given record (using the splitCsvLineToArray() method) into it's individual fields, and then sending those field values off to the correct sanitization method based on it's formatting/schema requirements. The sanitization methods in question are sanitizeNonNullableString() & sanitizeFloat(). If a field is successfully sanitized, it is then set as an instance variable of a CSVLine Object, which is a class dedicated to representing a record and it's data specifically for this program's CSV output. Once the CSVLine Object is created and all it's possible instance variables set, it leverages an internal method called isValidLine() as a final validation to ensure it meets the proper specifications for merging into the output file. Assuming the record is validated, it's toString() method is invoked as an argument to a PrintWriter Object's write() method, which physically writes the record to the output file. 

The above process is conducted for each file, until complete. Error checking & testing is detailed in the **Unit Tests / Error Logging** section.  

## Setup / Usage

#### Prerequisites / Dependencies
To run CSVCombine, we must be able to compile and run our code from the command line. As such, a version of the JRE & JDK installed on one's unix or linux machine is required:

`sudo apt install openjdk-16-jre-headless`

`sudo apt install openjdk-16-jdk-headless`

Additionally, to ensure testing can be run from the command line, the JUnit Console Launcher standalone jar file should be downloaded. One has been included in this repository, but an executable junit-platform-console-standalone-1.8.0.jar with all dependencies included is also published in the Maven Central repository under the [junit-platform-console-standalone directory.](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/)

#### Compilation / Execution
Ensure the files are compiled & .class files are created: 

`javac javac CombineCSV.java CSVLine.java`

Using the file names as arguments, execute CombineCSV with the 'java' command: 

`java CombineCSV file1.csv file2.csv filen.csv`

#### Output 

The merged CSV file will be output to the directory from which the program was run, named **output.csv**

## Examples

## Unit Tests / Logging
#### Logging
Many unit tests & system loggings are made available through the program. Error logging is printed to the terminal here for visibility when running the program. The following errors are reported to the terminal: 

1. Missing File Arguments -- If no files are provided to the app as arguments
2. File Not Found -- If a file doesn't exist or can't be located using the provided path
3. Null Values -- If null values are provided for a field which cannot be null. 
4. Non-Convertible value -- If a value provided for a field can't be properly parsed according to its type.
5. Missing Fields -- If a file is missing one of the required fields. 

Some general logging is also provided through the terminal, when the program is attempting to, or finishes reading a file. 

#### Unit Test Execution 
The simplest way to view the test results without the need for an IDE is through the JUnit Console Launcher. The Console Launcher can be run directly from the command line, and will print out results to the terminal. To ensure the Console Launcher runs smmoothly, __*ensure that the .jar file, as well as all the .class & .java for this project are under the same directory.*__ This eliminates the need for using relative paths, though the .jar file could be stored in a lib/ directory if desired. Run the following command from the directory under which the files are stored:  

`java -jar junit-platform-console-standalone-1.8.0.jar -cp ./ --scan-class-path`

![JUnit Output](/JUnit.png)

## Final Thoughts / Reflections / Challenges

Overall, I think this program does a fair job of accomplishing the required task. The use of an Object-Oriented language like Java allows for the creation of a class like CSVLine which allows the program to dyamically interpret files with various arrangements of the required headers. The updated Scanner class also allows for the easy progression through lines of a file using the nextLine() method. That said, Java specifically seems to be a bit antiquated when it comes to some of the processes of File I/O -- The PrintWriter class feels a bit obtuse / clunky to use, and also required manually crafting the CSV format through the use of a StringBuilder. 

The (potentially) quoted nature of the CSV (and possible presence of embedded quotes) created some difficulties. Since the requirements of program necessitated that it be able to examine individual fields of individual rows, I had to separate out fields through the use of some regex which could account for embedded quotations. This part of the program feels particularly brittle to me, and I think another programming language such as Python might be better equipped to handle such an operation due to it's emphasis on processing large datasets, as well as it's extensive libraries/frameworks. Additionally, Java's strict type definitions and try/catch exception checking made validation and type conversion unecessarily bulky in my opinion. Again, I think another language might be slightly better-suited for the task here. It is worth noting that this entire program could be easily achieved in bash (with significantly less code) through the use of awk/gawk. 

Future enhancements for this program might include a cleaner way of reading individual fields of a given record, perhaps through the use of a Java-based framework like OpenCSV. Additionally, I think including unit tests for the parseLine() method could enhance the resilience of the program. Due to the way it is currently written (primarily outsourcing tasks), there wasn't a great way to introduce testing that intrinsically made sense. That said, I think introducing a return value which accurately tracks the success of the method would simplify testing in the long run. Lastly, I think further sanitization of the record values could be a great value add to the program, as it would allow allow for greater flexibility when it comes to the formatting of the raw data files. 

