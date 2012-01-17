package main;


import java.io.BufferedWriter;
import java.io.FileWriter;


public class Sorter {

	private String stopFile;
	private String startFile;

	public Sorter(String startFileName, String stopFileName) {
		this.startFile = startFileName;
		this.stopFile = startFileName;
	}
	
	public static void main(String[] args) {
		Sorter sorter = new Sorter("Start.txt","Stop.txt");
		sorter.writeResultFile();
	}

	private boolean writeResultFile() {
		try {
			// Create file
			FileWriter fstream = new FileWriter("Result.txt");
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
