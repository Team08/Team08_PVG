package model;

import java.io.*;


import util.Time2;



public class Register {

	public Register() {
		
	}
	/**
	 * Writes registered time and starnumber to file
	 * 
	 * @param startnumber
	 *            The registered startnumber
	 * @param hours
	 *            The registered hours
	 * @param minutes
	 *            The registered minutes
	 * @param seconds
	 *            The registered seconds
	 */

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
	
	
	/**
	 * Writes driver to file
	 * 
	 * @param name
	 * 				name of driver
	 */			
	public void registerDriver(String name) {
		String[] times = Time2.makeTimeList(); 
		writeToFile(name, times[0], times[1], times[2]);
	}

}