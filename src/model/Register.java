package model;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Driver;
import main.Time;

public class Register {

	public Register() {
		
	}

	private void writeToFile(String name, String hours, String minutes,
			String seconds) {
		try {
			// Create file
			FileWriter fstream = new FileWriter("Register.txt", true);
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

	public String[] registerDriver(String name) {
		GregorianCalendar calendar = new GregorianCalendar();

		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String[] times = new String[3];

		String stringMinutes = Time.addZero(minutes);
		String stringSeconds = Time.addZero(seconds);

		times[0] = Integer.toString(hours);
		times[1] = stringMinutes;
		times[2] = stringSeconds;

		writeToFile(name, times[0], times[1], times[2]);
		return times;
	}

}