package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Sorter {
	protected TreeMap<Integer, Driver> register;
	private String stopFile;
	private String startFile;
	private String raceType;
	private int raceTime;
	private int laps;
	private String startType;
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;

	/**
	 * Create a new Sorter from its arguments
	 * 
	 * @param startFileName
	 *            The file name of the start file
	 * @param stopFileName
	 *            The file name of the stop/finish file
	 * @param nameFile
	 *            The file name of the name file
	 * @param raceType
	 *            The race type (e.g. "varv")
	 * @param raceTime
	 *            ?
	 * @param laps
	 *            The maximum number of laps
	 */
	public Sorter(String startFileName, String stopFileName, String nameFile, String raceType, int raceTime, int laps, String startType) {
		this.startFile = startFileName;
		this.stopFile = stopFileName;
		this.raceType = raceType;
		this.raceTime = raceTime;
		this.laps = laps;
		this.startType = startType;
		rnf = new ReadNameFile(this, nameFile);
		rsf = new ReadStartFile(this, startFile);
		rff = new ReadFinishFile(this, stopFile);
		register = new TreeMap<Integer, Driver>();
	}

	/**
	 * The main method for the Sorter program
	 * 
	 * @param args
	 *            The arguments to the sorter
	 */
	public static void main(String[] args) {

		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		int raceTime = 0;
		String raceType = "";
		int laps = 0;
		String startType = "";

		try {
			start = args[0];
			stop = args[1];
			name = args[2];
			result = args[3];
			raceType = args[4];
			startType = args[5];
			raceType = raceType.toLowerCase();
			if(raceType.equals("varv")){
				raceTime = Integer.parseInt(args[6]);
				laps = Integer.parseInt(args[7]);
			}
		} catch (Exception e) {
			System.out.println("Error: Fel argument");
		}
		Sorter sorter = new Sorter(start, stop, name, raceType, raceTime, laps, startType);
		sorter.writeResultFile(result);
	}

	protected void writeResultFile(String name) {
		try {
			// Names are put in the TreeMap from the name file
			rnf.readFile();
			// Start file is read, and start times are put in the register
			
			//1 = MassStart
			if(startType.equals("Masstart")){
			rsf.readFileMassStart();
			}else{
			rsf.readFile();
			}
			// Finish file is read, and finish times are put in the register
			rff.readFile();
			// Create file

			FileWriter fstream = new FileWriter(name);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("StartNr; Namn; ");
			if (raceType.equals("varv")) {
				out.write("#Varv; ");
			}
			out.write("Totaltid; ");
			if (raceType.equals("varv")) {
				for (int i = 0; i < laps; i++) {
					out.write("Varv" + (i + 1) + "; ");
				}
			}
			out.write("Start; ");
			if (raceType.equals("varv")) {
				for (int i = 0; i < laps - 1; i++) {
					out.write("Varvning" + (i + 1) + "; ");
				}
			}
			out.write("Mål\n");
			Driver tDriver;

			
			for (Integer i : register.keySet()) {
				tDriver = register.get(i);
				out.write(checkError(i, tDriver.startTime(), 
						tDriver.finishTime()));
			}
			// Close the output stream
			out.close();

		} catch (Exception e) {
		}
	}

	private String checkError(int i, List<String> startTime,
			List<String> finishTime) {
		StringBuilder sb = new StringBuilder();
		String totalTimeCheck = "";
		sb.append(i + "; ");
		checkName(sb, register.get(i).getName());
		if (raceType.equals("varv")) {
			sb.append(register.get(i).getNumberOfLaps() + "; ");
		}
		totalTimeCheck = checkTotaltime(startTime, finishTime, sb,
				totalTimeCheck);
		if (raceType.equals("varv") && (laps != 0)) {
			if (!(startTime.size() == 0 || finishTime.size() == 0)) {
				for (int j = 0; j < laps; j++) {
					sb.append(register.get(i).getLapTime(j) + "; ");
				}
			}
		}
		checkStartTime(startTime, sb);
		if (raceType.equals("varv") && finishTime.size() != 0) {
			int check = finishTime.size() - 1;
			if (check > laps - 1) {
				check = laps - 1;
			}
			for (int e = 0; e < check; e++) {
				sb.append(finishTime.get(e) + "; ");
			}
			for (int b = finishTime.size() - 1; b < laps - 1; b++) {
				sb.append("; ");
			}

			if (Time.timeDiff(startTime.get(0),
					finishTime.get(finishTime.size() - 1)).compareTo(
							raceTime + ".00.00") >= 0) {
				sb.append(finishTime.get(finishTime.size() - 1));

			} else {
				sb.append("Slut?");
			}

		} else {
			checkFinishTime(finishTime, sb);
		}
		checkIfManyStartTime(startTime, sb);
		if (raceType.equals("maraton")) {
			checkIfManyFinishTime(finishTime, sb);
		}
		sb.append(totalTimeCheck);
		sb.append("\n");
		return sb.toString();
	}

	private void checkIfManyFinishTime(List<String> finishTime, StringBuilder sb) {
		if (finishTime.size() > 1) {
			sb.append("; Flera måltider?");
			for (int j = 1; j <= (finishTime.size() - 1); j++) {
				sb.append(" " + finishTime.get(j));
			}
		}
	}

	private void checkIfManyStartTime(List<String> startTime, StringBuilder sb) {
		if (startTime.size() > 1) {
			sb.append("; Flera starttider?");
			for (int j = 1; j <= (startTime.size() - 1); j++) {
				sb.append(" " + startTime.get(j));
			}
		}

	}

	private void checkFinishTime(List<String> finishTime, StringBuilder sb) {
		if (finishTime.size() == 0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
	}

	private void checkStartTime(List<String> startTime, StringBuilder sb) {
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
	}

	private String checkTotaltime(List<String> startTime,
			List<String> finishTime, StringBuilder sb, String totalCheck) {
		String totalTime = "";
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		} else {
			if (raceType.equals("varv")) {
				totalTime = Time.timeDiff(startTime.get(0), finishTime
						.get(finishTime.size() - 1));
			} else {
				totalTime = Time.timeDiff(startTime.get(0), finishTime.get(0));
			}
			sb.append(totalTime + "; ");
			if (totalTime.compareTo("0.15.00") < 0) {
				totalCheck = "; Omöjlig Totaltid?";
			}
		}
		return totalCheck;
	}

	private void checkName(StringBuilder sb, String name) {
		if (name == null) {
			sb.append("Namn?; ");
		} else {
			sb.append(name + "; ");
		}
	}

	/**
	 * Adds a new start time for the specified start number
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
	 * Adds a new finish time for the specified start number
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

	/**
	 * Add a name for the specified start number. The current name is replaced.
	 * 
	 * @param startNumber
	 *            Start number
	 * @param name
	 *            Name of the driver
	 */
	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		driver.setName(name);
		register.put(startNumber, driver);
	}

	/**
	 * Add a class for the specified start number
	 * 
	 * @param startNumber
	 *            The start number
	 * @param c
	 *            The class
	 */
	public void addClass(Integer startNumber, String c) {
		Driver driver = getDriver(startNumber);
		driver.addClass(c);
		register.put(startNumber, driver);
	}

	/**
	 * Returns the driver corresponding to the specified start number. If no
	 * driver exists with the specified start number a new Driver is returned.
	 * 
	 * @param key
	 *            The start number
	 * @return A driver
	 */
	public Driver getDriver(Integer key) {
		return register.containsKey(key) ? register.get(key) : new Driver();
	}

	/**
	 * Returns the number of drivers
	 * 
	 * @return The number of drivers
	 */
	public int size() {
		return register.size();
	}

}