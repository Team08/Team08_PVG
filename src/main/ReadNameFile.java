package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadNameFile {
	private String fileName;


	public ReadNameFile(String file) {
		fileName = file;
	}

	/**
	 * Reads file specified in constructor and puts the name in the specified TreeMap
	 * If Driver doesn't exist in TreeMap the Driver is added 
	 * @param tm The TreeMap with Drivers to put names in
	 */
	public void readFile(TreeMap<Integer, Driver> tm) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			if(scanner.hasNextLine()) {
				scanner.nextLine();
								
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] strArr = line.split("; ");
					Integer startNumber = Integer.parseInt(strArr[0]);
					String name = strArr[1];
					
					if(tm.containsKey(startNumber)) {
						tm.get(startNumber).setName(name);
					} else {
						tm.put(startNumber, new Driver(name));
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} 

	}
}
