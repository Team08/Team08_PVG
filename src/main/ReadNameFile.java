package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	public void readFile() throws FileNotFoundException {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;
				if(scanner.hasNextLine()) {
					scanner.nextLine();
				}
				String currClass = "";
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
												
					String[] str = line.split("; ");
					if(str.length == 1) {
						currClass = str[0];
					} else {
						name = str[1];
						riderID = Integer.parseInt(str[0]);
						add();
						sorter.addClass(riderID, currClass);
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

			} catch (FileNotFoundException e) {// Catch exception if any
				throw new FileNotFoundException();
			}
		}
	}
	
	protected void add() {
		sorter.addName(riderID, name);
		
	}



}

