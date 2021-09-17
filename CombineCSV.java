import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * @author Daniel Athey <daniel.t.athey@gmail.com>
 * @version 1.0, last modified 9/16/21
 */

public class CombineCSV {

	// HEADERS values
	private static String PROVIDER_NAME = "Provider Name";
	private static String CAMPAIGN_ID = "CampaignID";
	private static String COST_PER_AD_CLICK = "Cost Per Ad Click";
	private static String REDIRECT_LINK = "Redirect Link";
	private static String PHONE_NUMBER = "Phone Number";
	private static String ADDRESS = "Address";
	private static String ZIPCODE = "Zipcode";

	public static void main(String[] args) {
		// ensure file names were provided
		if (args.length == 0) {
			usage();
			System.exit(0);
		}
		// we have at least one file to "merge" -- create the output file in local dir,
		// and append file headers
		try {
			PrintWriter output = new PrintWriter("output.csv");
			output.write(
					"\"Provider Name\",\"CampaignID\",\"Cost Per Ad Click\",\"Redirect Link\",\"Phone Number\",\"Address\",\"Zipcode\"\n");

			// read the provided files using Scanner object
			for (int i = 0; i < args.length; i++) {
				System.out.println("Attempting To Read File: " + args[i]);
				try {
					Scanner reader = new Scanner(new File(args[i]));
					String headers = reader.nextLine();
					while (reader.hasNextLine()) {
						String current = reader.nextLine();
						try {
							parseLine(current, output, headers);
						} catch (Exception e) {
							System.out.println("ERROR: Unable to parse line: " + current);
							System.out.println(e.getMessage());
						}
					}
					reader.close();
					System.out.println("Finished Reading File: " + args[i]);
				} catch (FileNotFoundException fnfe) {
					System.out.println("Sorry, that file (" + args[i] + ") doesn't exist. Please ensure the file "
							+ "is in the same directory as the program, or provide the correct path to the file. ");
				}

			}
			output.close();
		} catch (IOException ioe) {
			System.out.println("System encountered an error while creating output file:");
			System.out.println(ioe.getMessage());
		}
	}

	/*
	 * driving method which parses individual lines of a given CSV file, and creates
	 * the CSVLine Object which will then be printed to the merged file
	 * 
	 * @param input --> the unparsed, unsanitized line from the partner's raw data
	 * file
	 * 
	 * @param output --> the PrintWriter object which is used to write to the
	 * output.csv file
	 * 
	 * @param headers --> the headers of the file being processed
	 * 
	 * @throws NullPointerException
	 */
	private static void parseLine(String input, PrintWriter output, String headers) throws Exception {
		// read the values for a line into array
		String[] heads = splitCsvLineToArray(headers);
		String[] vals = splitCsvLineToArray(input);

		// Create & Fill CSVLine Object
		CSVLine currentLine = new CSVLine();

		for (int i = 0; i < vals.length; i++) {
			// trim whitespace to start
			String curVal = vals[i].trim();

			if (heads[i].equals(PROVIDER_NAME)) {
				currentLine.setProviderName(sanitizeNonNullableString(curVal));
			} else if (heads[i].equals(CAMPAIGN_ID)) {
				currentLine.setCampaignId(sanitizeNonNullableString(curVal));
			} else if (heads[i].equals(REDIRECT_LINK)) {
				currentLine.setRedirect(sanitizeNonNullableString(curVal));
			} else if (heads[i].equals(ADDRESS)) {
				currentLine.setAddress(sanitizeNonNullableString(curVal));
			} else if (heads[i].equals(ZIPCODE)) {
				currentLine.setZip(sanitizeNonNullableString(curVal));
			} else if (heads[i].equals(COST_PER_AD_CLICK)) {
				currentLine.setCpac(sanitizeFloat(curVal));
			} else if (heads[i].equals(PHONE_NUMBER)) {
				currentLine.setPhone(curVal);
			} else {
				 System.out.println("An unrecognized field: " + heads[i] + " and it's value: "
				 + curVal + " was encountered. Skipping since it is not included in the specified schema.");
			}
		}
		// finally, print our newly sanitized & formatted line into merged, output file
		if (currentLine.isValidLine()) {
			output.write(currentLine.toString());
			output.write("\n");
		} else {
			System.out.println("ERROR: Unable to parse line: " + input);
		}

	}

	protected static String[] splitCsvLineToArray(String s) {
		return s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	}

	/*
	 * helper method to prepare non-nullable fields for storage in the CSVLine
	 * Object
	 * 
	 * @param s --> the field value being stored
	 * 
	 * @return s --> the sanitized String object
	 * 
	 * @throws NullPointerException
	 */
	protected static String sanitizeNonNullableString(String s) {
		if (s.isEmpty()) {
			throw new NullPointerException("value cannot be null");
		}
		// remove lead/trail quotes for parsing
		s = s.replaceAll("^\"+|\"+$", "");
		return s;
	}

	/*
	 * helper method to prepare float fields for storage in the CSVLine Object
	 * 
	 * @param s --> the field value being stored
	 * 
	 * @return s --> the sanitized Float object
	 * 
	 * @throws NullPointerException, NumberFormatException
	 */
	protected static Float sanitizeFloat(String s) throws Exception {
		// remove quotes for parsing
		s = s.replace("\"", "");
		Float f = Float.parseFloat(s);
		if (f < 0) {
			throw new IllegalArgumentException("No Negative Numbers!");
		}
		return f;
	}

	/*
	 * Usage function to illustrate proper command line execution
	 */
	private static void usage() {
		System.out.println(
				"Program failed to execute because no files were provided. Please review usage syntax and try again:\n");
		System.out.println("USAGE: 'java CombineCSV file1.csv file2.csv filen.csv'");
		System.out.println("EXAMPLE: 'java CombineCSV Auto_Insurance.csv Home_Insurance.csv'");
	}

}
