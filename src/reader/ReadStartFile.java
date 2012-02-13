package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;
import util.Time;

/**
 * This class can read a start file and add the start time + driverId to the
 * race object.
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
	 * time and riderID to race object.
	 */
	protected void add() {
		race.addStartTime(riderID, time);
	}

	/**
	 * Reads a file and add the time and riderID to the race.
	 */
	public void readFileMassStart() {
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
				System.out.println("Hittade inte filen");
			}
		}

	}

}