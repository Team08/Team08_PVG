package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class FileIO {
	protected Sorter sorter;
	protected String fileName;
	protected int riderID;
	protected String name;

	protected FileIO(Sorter sorter, String fileName) {
		this.sorter = sorter;
		this.fileName = fileName;
	}

	/**
	 * Reads a file and add it to sorter
	 * 
	 * @throws FileNotFoundException
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
				e.printStackTrace();
				throw new FileNotFoundException();
			}
		}
	}
	
	protected abstract void add();

}