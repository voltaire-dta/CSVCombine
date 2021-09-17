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


## Setup / Usage

## Examples

## Unit Tests / Error Logging

## Final Thoughts 
