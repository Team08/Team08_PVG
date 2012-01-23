package main;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.TreeMap;


public class Sorter {
	protected TreeMap<Integer, Driver> register;
	private String stopFile;
	private String startFile;

	public Sorter(String startFileName, String stopFileName) {
		this.startFile = startFileName;
		this.stopFile = startFileName;
		register = new TreeMap<Integer, Driver>();
	}
	
	public static void main(String[] args) {
		Sorter sorter = new Sorter("Start.txt","Stop.txt");
		sorter.writeResultFile();
	}

	protected boolean writeResultFile() {
		try {
			// Create file
			FileWriter fstream = new FileWriter("Result.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			

			out.write("StartNr; Totaltid; Starttid; Måltid\n");
			for (Integer i: register.keySet()){
				out.write(i + "; --.--.--; "+ register.get(i).startTime() +"; " + register.get(i).finishTime() + "\n");
			}
			// Close the output stream
			out.close();
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		return true;
	}

	public void addStartTime(Integer startNumber, String time) {
			Driver driver = new Driver();
			driver.addStartTime(time);
			register.put(startNumber, driver);
	}

	public void addFinishTime(Integer startNumber, String time) {
			Driver driver = new Driver();
			driver.addFinishTime(time);
			register.put(startNumber, driver);	
	}
	
	
}
