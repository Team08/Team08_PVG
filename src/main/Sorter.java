package main;


import java.io.*;
import java.util.TreeMap;


public class Sorter {
	protected TreeMap<Integer, Driver> register;
	private String stopFile;
	private String startFile;

	public Sorter(String startFileName, String stopFileName) {
		this.startFile = startFileName;
		this.stopFile = stopFileName;
		register = new TreeMap<Integer, Driver>();
	}

	public static void main(String[] args) {
		// Choose Files

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String start = "defaultStart";
		String stop = "defaultStop";
		String result = "defaultResult";
		try {
			System.out.println("Välj startfil:");
			start = reader.readLine();
			System.out.println("Välj målfil:");
			stop = reader.readLine();
			System.out.println("Välj resultatfil:");
			result = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sorter sorter = new Sorter(start, stop);
		sorter.writeResultFile(result);
	}

	protected boolean writeResultFile(String name) {
		try {
			// Create file
			
			FileWriter fstream = new FileWriter(name);
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
			if (register.containsKey(startNumber)) {
				Driver driver = register.get(startNumber);
				driver.addStartTime(time);
			} else {
				Driver driver = new Driver();
				driver.addStartTime(time);
				register.put(startNumber, driver);
			}
	}

	public void addFinishTime(Integer startNumber, String time) {
		if(register.containsKey(startNumber)) {
			Driver driver = register.get(startNumber);
			driver.addFinishTime(time);
		} else {
			Driver driver = new Driver();
			driver.addFinishTime(time);
			register.put(startNumber, driver);
		}
			
	}
	
	
}
