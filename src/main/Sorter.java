package main;

import java.io.*;

public class Sorter {

	private String stopFile;
	private String startFile;

	public Sorter(String startFileName, String stopFileName) {
		this.startFile = startFileName;
		this.stopFile = stopFileName;
	}

	public static void main(String[] args) {
		// Choose Files

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String start = new String();
		String stop = new String();
		try {
			System.out.println("Välj startfil:");
			start = reader.readLine();
			System.out.println("Välj målfil:");
			stop = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sorter sorter = new Sorter(start, stop);
		sorter.writeResultFile();
	}

	private boolean writeResultFile() {
		try {
			// Create file
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Välj resultatfil:");
			String result = reader.readLine();
			FileWriter fstream = new FileWriter(result);
			BufferedWriter out = new BufferedWriter(fstream);

			out.write("This is the results");
			// Close the output stream
			out.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		return true;
	}
}
