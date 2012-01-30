package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class FileIO {
	protected Sorter sorter;
	protected String fileName;
	protected int riderID;
	protected String name;

	public FileIO(Sorter sorter, String fileName) {
		this.sorter = sorter;
		this.fileName = fileName;
	}
	
	protected abstract void add();

	

	/**
     * Reads file specified in constructor and puts the name in the specified
     * TreeMap If Driver doesn't exist in TreeMap the Driver is added
     *
     * @param tm The TreeMap with Drivers to put names in
     * @throws IOException
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
					name = str[1];
					riderID = Integer.parseInt(str[0]);
					add(); // What happens
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
				throw new FileNotFoundException();
			}
		}
	}

}