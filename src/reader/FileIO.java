package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;
import util.Time;

/**
 * An abstract class which has a method for reading a file.
 * Uses the Template Method architecture. Appropriate sub classes will be 
 * called. 
 * 
 * Reads a file and adds the driverID and Time to the database.
 * 
 * @author Team08
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
	 * Reads a file and add the time and driverID via the race object,
	 * which contains the database where all information is stored.
	 * 
	 * @throws FileNotFoundException
	 *             If file not found
	 */
	public void readFile() throws FileNotFoundException {
		String[] thefile = fileName.split(", ");
		for(int i = 0; i < thefile.length; i++){
		if (thefile[i] != null) {
			File file = new File(thefile[i].trim());
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;

				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String[] str = line.split(";");
					time = new Time(str[1].trim());
					riderID = Integer.parseInt(str[0].trim());
					add();
				}

			} catch (FileNotFoundException e) {// Catch exception if any
				printErrorText();
				throw new FileNotFoundException();
			}
		}
		}
	}


	/**
	 * Prints the error text if file not found.
	 */
	protected abstract void printErrorText();
	
	
	/**
	 * Method is called by readFile() and used to add the time to the race
	 * object.
	 */
	protected abstract void add();

}