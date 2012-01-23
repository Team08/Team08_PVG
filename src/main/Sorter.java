package main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.InputStreamReader;



import java.util.TreeMap;


public class Sorter {
	protected TreeMap<Integer, Driver> register;

	private String stopFile;
	private String startFile;

	public Sorter(String startFileName, String stopFileName) {
		this.startFile = startFileName;
		this.stopFile = startFileName;
		register = new TreeMap<Integer, Driver>();
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

	
	public void readFile(String filename){
		try{
			  FileInputStream fstream = new FileInputStream(filename);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  System.out.println (strLine);
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		
	}


	public void addStartTime(Integer startNumber, String time) {
			Driver driver = new Driver();
			driver.addStartTime(time);
			register.put(startNumber, driver);
	}

	public void addFinishTime(Integer startNumber, String time) {
			Driver driver = new Driver();
			driver.addFinishTime(time);
			register.put(startNumber, driver);	
	}
	
	

}
