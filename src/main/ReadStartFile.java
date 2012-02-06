package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadStartFile extends FileIO {
	/**
	 * The constructor which takes the file name of the startfile as argument
	 * 
	 * @param sorter
	 *            target sorter
	 * @param fileName
	 * 			  the name of startfile
	 */
	public ReadStartFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	
	
	protected void add() {
		sorter.addStartTime(riderID, name);
	}


	public void readFileMassStart() {
		if (fileName != null) {
			File file = new File(fileName);
			Scanner scanner;
			try {
				scanner = new Scanner(file);
				String line;
					line = scanner.nextLine();
					String[] str = line.split("; ");
				int index = Integer.parseInt(str[0]);
				for (int i = 1; i<= index; i++){
					riderID = i;
					name = str[1];
					add();
				}

			} catch (FileNotFoundException e) {// Catch exception if any
				System.out.println("Hittade inte filen");
			}
		}
		
	}

}