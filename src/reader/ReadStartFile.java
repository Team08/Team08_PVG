package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;
import util.Time;

/**
 * This class can read a start file and add the start time + driverId to the
 * race object, which contains the database where all information is stored.
 */
public class ReadStartFile extends FileIO {

	/**
	 * Creates the ReadStartFile.
	 * 
	 * @param race
	 *            The race that the files belongs to
	 * @param fileName
	 *            The name of the file
	 */
	public ReadStartFile(Race race, String fileName) {
		super(race, fileName);
	}

	/**
	 * Method is called by readFile() or readFileMassStart() and adds the start
	 * time and driverID to race object, which contains the database where all
	 * information is stored.
	 */
	protected void add() {
		race.addStartTime(riderID, time);
	}

	/**
	 * Reads a file and add the time and driverID to the race object,
	 * which contains the database where all information is stored.
	 * 		This method doesn't support STAGERACE!
	 * 
	 * @throws FileNotFoundException
	 *             If file not found
	 */
	
	public void readFileMassStart() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;
				line = scanner.nextLine();
				String[] str = line.split("; ");
				int index = Integer.parseInt(str[0]);
				for (int i = 1; i <= index; i++) {
					riderID = i;
					time = new Time(str[1]);
					add();
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
		System.err.println("Hittade inte startfilen.");
	}

}