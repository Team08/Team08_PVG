package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;

/**
 * This class can read a name file and add the name + driverId to the race
 * object.
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
	 * Reads a name file and add the name and riderID to the race.
	 * 
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			System.out.println(fileName);
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

			} catch (FileNotFoundException e) {
				// Ska vi vara konsekventa och skriva ut SYSO som på andra
				// filer?
				throw new FileNotFoundException();
			}
		}
	}

}