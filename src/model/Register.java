package model;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Driver;
import main.Time;

public class Register {
	private Driver driver; // Behövs ej?

	public Register(Driver driver) {
		this.driver = driver; // Behövs ej?
	}

	private void writeToFile(String name, String hours, String minutes,
			String seconds) {
		try {
			// Create file
			FileWriter fstream = new FileWriter("Start.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(name + "; " + hours + "." + minutes + "." + seconds
					+ "\n");

			// Close the output stream
			out.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}


	public void registerDriver(String name) {
		String[] times = Time.makeTimeList(); 


		writeToFile(name, times[0], times[1], times[2]);
	}

}