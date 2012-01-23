package main;

import java.io.*;

import java.util.List;

import java.util.Scanner;

import java.util.TreeMap;

public class Sorter {
	protected TreeMap<Integer, Driver> register;
	private String stopFile;
	private String startFile;
	private ReadNameFile rnf;

	public Sorter(String startFileName, String stopFileName, String nameFile) {
		this.startFile = startFileName;
		this.stopFile = stopFileName;
		rnf = new ReadNameFile(this, nameFile);
		register = new TreeMap<Integer, Driver>();	
	}

	public static void main(String[] args) {
		// Choose Files
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		try {
			System.out.println("Välj startfil:");
			start = reader.readLine();
			System.out.println("Välj målfil:");
			stop = reader.readLine();
			System.out.println("Välj namnfil:");
			name = reader.readLine();
			System.out.println("Välj resultatfil:");
			result = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sorter sorter = new Sorter(start, stop, name);
		sorter.writeResultFile(result);
	}

	protected void writeResultFile(String name) {
		try {
			// Names are put in the TreeMap from the name file
			rnf.readFile();
			// Create file
			FileWriter fstream = new FileWriter(name);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("StartNr; Namn; Totaltid; Starttid; Måltid\n");
			Driver tDriver;

			for (Integer i : register.keySet()) {
				tDriver = register.get(i);
				out.write(checkError(i, tDriver.startTime(), tDriver.finishTime()));
			}
			// Close the output stream
			out.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}


	private String checkError(int i, List<String> startTime, List<String> finishTime) {

		StringBuilder sb = new StringBuilder();
		sb.append(i + "; ");
		String totalCheck = "";
		if (register.get(i).getName()==null){
			sb.append("Namn?; ");
		}else{
			sb.append(register.get(i).getName());
		}
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		} else {
			sb.append(register.get(i).totalTime() + "; ");
			if(register.get(i).totalTime().compareTo("0.15.00")<0){
				totalCheck = "; Omöjlig Totaltid?";
			}
		}
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
		if (finishTime.size()==0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
		if (startTime.size() > 1) {
			sb.append("; Flera starttider?");
			for (int j = 1; j <= (startTime.size() - 1); j++) {
				sb.append(" " + startTime.get(j));
			}
		}
		if (finishTime.size() > 1) {
			sb.append("; Flera måltider?");
			for (int j = 1; j <= (finishTime.size() - 1); j++) {
				sb.append(" " + finishTime.get(j));
			}
		}
		sb.append(totalCheck);
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * Inserts a new start time for the specified start number The current start
	 * time is replaced by the new start time (time)
	 * 
	 * @param startNumber
	 *            The start number of the driver
	 * @param time
	 *            The start time
	 */
	public void addStartTime(Integer startNumber, String time) {
		Driver driver = getDriver(startNumber);
		driver.addStartTime(time);
		register.put(startNumber, driver);
	}

	/**
	 * Inserts a new finish time for the specified start number The current
	 * finish time is replaced by the new finish time (time)
	 * 
	 * @param startNumber
	 *            The start number of the driver
	 * @param time
	 *            The finish time
	 */
	public void addFinishTime(Integer startNumber, String time) {
		Driver driver = getDriver(startNumber);
		driver.addFinishTime(time);
		register.put(startNumber, driver);
	}

	private Driver getDriver(Integer key) {
		return register.containsKey(key) ? register.get(key) : new Driver();
	}

	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		driver.setName(name);
		register.put(startNumber, driver);	
	}
	
	public int size() {
		return register.size();
	}

}
