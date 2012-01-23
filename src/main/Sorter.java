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
			for (Integer i : register.keySet()) {
				out.write(i + "; " + register.get(i).totalTime() + "; " + register.get(i).startTime()
						+ "; " + register.get(i).finishTime() + "\n");
			}
			// Close the output stream
			out.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		return true;
	}

	public void readFile(String filename) {
		try {
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

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

}
