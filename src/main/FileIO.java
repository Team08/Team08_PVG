package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class FileIO {
	protected Sorter sorter;
	protected String fileName;

	protected FileIO(Sorter sorter, String fileName) {
		this.sorter = sorter;
		this.fileName = fileName;
	}

	/**
	 * Reads file specified in constructor and updates the Driver-database. If
	 * the driver doesn't exist it is added to the database
	 */
	public void read() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scan;
			try {
				scan = new Scanner(file);
				String line;
				while (scan.hasNextLine()) {
					line = scan.nextLine();
					String[] data = line.split("; ");
					Integer startNumber = Integer.parseInt(data[0]);

					// What happens if more than 1 start time?
					// A for-loop should be added to make sure that the entire
					// vector is added
					add(startNumber, data[1]);
				}
			} catch (FileNotFoundException e) {// Catch exception if any
				throw new FileNotFoundException();
			}
		}
	}

	protected abstract void add(Integer startNbr, String value);
}