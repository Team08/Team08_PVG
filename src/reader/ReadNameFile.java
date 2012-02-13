package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;

/**
 * This class can read a name file and add the name + driverId to the race
 * object, which contains the database where all information is stored.
 * 
 * @author Team08
 */
public class ReadNameFile extends FileIO {
	String name;

	/**
	 * Creates the ReadNameFile.
	 * 
	 * @param race
	 *            The race that the files belongs to
	 * @param fileName
	 *            The name of the file
	 */
	public ReadNameFile(Race race, String fileName) {
		super(race, fileName);
	}

	/**
	 * Method is called by readFile() and adds the name and riderID to race
	 * object.
	 */
	protected void add() {
		race.addName(riderID, name);
	}

	/**
	 * Reads a name file and add the name and driverID to the race object,
	 * which contains the database where all information is stored.
	 * 
	 * @throws FileNotFoundException if file is not found
	 */
	public void readFile() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;
				if (scanner.hasNextLine()) {
					scanner.nextLine();
				}
				String currClass = "";
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();

					String[] str = line.split("; ");
					if (str.length == 1) {
						currClass = str[0];
					} else {
						name = str[1];
						riderID = Integer.parseInt(str[0]);
						add();
						race.addClass(riderID, currClass);
					}
				}

			} catch (FileNotFoundException e) {
				printErrorText();
				throw new FileNotFoundException();
			}
		}
	}


	/**
	 * Prints the error text if file not found.
	 */
	protected void printErrorText() {
		System.err.println("Hittade inte namnfilen.");
		
	}

}