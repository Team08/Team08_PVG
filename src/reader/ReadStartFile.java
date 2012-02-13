package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import race.Race;
import util.Time;


public class ReadStartFile extends FileIO {
	/**
	 * The constructor which takes the file name of the start file as argument
	 * 
	 * @param sorter
	 *            target sorter
	 * @param fileName
	 * 			  the name of start file
	 */
	public ReadStartFile(Race race, String fileName) {
		super(race, fileName);
	}
	
	
	
	protected void add() {
		race.addStartTime(riderID, time);
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
					time = new Time(str[1]);
					add();
				}

			} catch (FileNotFoundException e) {// Catch exception if any
				System.out.println("Hittade inte filen");
			}
		}
		
	}

}