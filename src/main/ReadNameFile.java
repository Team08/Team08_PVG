package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadNameFile extends FileIO {

	/**
	 * The constructor which takes the file name of the name file as argument
	 * 
	 * @param file
	 *            The file with the start numbers and names
	 */
	public ReadNameFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}

	/**
	 * Reads file specified in constructor and puts the name in the specified
	 * TreeMap If Driver doesn't exist in TreeMap the Driver is added
	 * 
	 * @param tm
	 *            The TreeMap with Drivers to put names in
	 */
	public void readFile() throws IOException{
		if (fileName != null) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			if (scanner.hasNextLine()) {
				scanner.nextLine();
				String line;
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String[] strArr = line.split("; ");
					Integer startNumber = Integer.parseInt(strArr[0]);
					String name = strArr[1];
					sorter.addName(startNumber, name);
					}
				}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			throw new IOException();
		
	}
		}
	}
}

