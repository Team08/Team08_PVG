package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadFinishFile extends FileIO {

	public ReadFinishFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	public void read() throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scan;
		try {
			scan = new Scanner(file);
			String line;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String[] str = line.split("; "); 
				Integer startNumber = Integer.parseInt(str[0]);
				sorter.addFinishTime(startNumber, str[1]);
			}

		} catch (FileNotFoundException e) {// Catch exception if any
			throw new FileNotFoundException();
		}
	}
	
}
