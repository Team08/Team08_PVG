package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;
import util.Time;

/**
 * A file reader that can add riderID and time to the race object.
 */
public abstract class FileIO {
	protected Race race;
	protected String fileName;
	protected int riderID;
	protected Time time;

	/**
	 * Creates the FileIO that reads a file.
	 * 
	 * @param race
	 *            The race that the files belongs to
	 * @param fileName
	 *            The name of the file
	 */
	protected FileIO(Race race, String fileName) {
		this.race = race;
		this.fileName = fileName;
	}

	/**
	 * Reads a file and add the time and riderID to the race.
	 * 
	 * @throws FileNotFoundException
	 *             If file not found
	 */
	public void readFile() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;

				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String[] str = line.split("; ");
					time = new Time(str[1]);
					riderID = Integer.parseInt(str[0]);
					add();

					// What happens
					// if more than
					// one finish
					// time?
					// A for-loop
					// should be
					// added to make
					// sure that the
					// entire
					// vector is
					// added.
				}

			} catch (FileNotFoundException e) {// Catch exception if any
				System.err.print("Fel filnamn");
				throw new FileNotFoundException();
			}
		}
	}

	/**
	 * Method is called by readFile() and used to add the time to the race
	 * object.
	 */
	protected abstract void add();

}