package main;

import java.io.*;
import java.util.Date;

public class Register {
	private Driver driver;
	/**
	 * Creates a registration of the designat
	 * @param driver The driver that is registrated
	 */
	public Register(Driver driver) {
		this.driver = driver;
	}
	/**
	 * Saves a starting time to a file
	 * @param name Name of the file you are saving
	 * @return The starting time as a Date-object
	 */
	private Date writeToFile(String name) {
		try {
			// Create file
			FileWriter fstream = new FileWriter(name);
			BufferedWriter out = new BufferedWriter(fstream);
			Date startTime = new Date();

			out.write(startTime.toString());
			// Close the output stream
			out.close();
			return (startTime);
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return (new Date(0));
		}

	}
	/**
	 * Returns a file with the starting times (Eller?)
	 * @return Returns a Date-object with the starting times
	 */
	public Date startRace() {
		return writeToFile("Start.txt");
	}
	/**
	 * Returns a file with the stopping times (Eller?)
	 * @return Returns a Date-object with the stopping times
	 */	
	public Date stopRace() {
		return writeToFile("Stop.txt");
	}
}
