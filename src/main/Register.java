package main;

import java.io.*;
import java.util.Date;

public class Register {
	private Driver driver;

	public Register(Driver driver) {
		this.driver = driver;
	}

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

	public Date startRace() {
		return writeToFile("Start.txt");
	}
	
	public Date stopRace() {
		return writeToFile("Stop.txt");
	}
}