package main;

import java.io.*;
import java.util.List;
import java.util.TreeMap;

public class Sorter {
	protected TreeMap<Integer, Driver> register;
	private String stopFile;
	private String startFile;
	private int raceType;
	private int raceTime;
	private int laps;
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;

	public Sorter(String startFileName, String stopFileName, String nameFile,
			int raceType, int raceTime, int laps) {
		this.startFile = startFileName;
		this.stopFile = stopFileName;
		this.raceType = raceType;
		this.raceTime = raceTime;
		this.laps = laps;
		rnf = new ReadNameFile(this, nameFile);
		rsf = new ReadStartFile(this, startFile);
		rff = new ReadFinishFile(this, stopFile);
		register = new TreeMap<Integer, Driver>();
	}

	public static void main(String[] args) {
		// Choose Files
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		int raceTime = 0;
		int raceType = 0;
		int laps = 0;

		try {
			System.out.println("Ange startfilens namn:");
			start = reader.readLine();
			System.out.println("Ange målfilens namn:");
			stop = reader.readLine();
			System.out.println("Ange personuppgiftsfilens namn:");
			name = reader.readLine();
			System.out.println("Ange resultatfilens namn:");
			result = reader.readLine();
			System.out.println("Välj typ av lopp (1 = maraton, 2 = varvlopp):");
			raceType = reader.read();
			if (raceType == 2) {
				System.out.println("Skriv in lopptid i timmar:");
				raceTime = reader.read();
				System.out.println("Skriv in antal varv som ska visas:");
				laps = reader.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sorter sorter = new Sorter(start, stop, name, raceType, raceTime, laps);
		sorter.writeResultFile(result);
	}

	protected void writeResultFile(String name) {
		try {
			// Names are put in the TreeMap from the name file
			rnf.readFile();
			// Start file is read, and start times are put in the register
			rsf.readFile();
			// Finish file is read, and finish times are put in the register
			rff.readFile();
			// Create file
			FileWriter fstream = new FileWriter(name);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("StartNr; Namn; ");
			if (raceType == 2) {
				out.write("#Varv; ");
			}
			out.write("Totaltid; ");
			if (raceType == 2) {
				for (int i = 0; i < laps; i++) {
					out.write("Varv" + (i + 1) + "; ");
				}
			}
			out.write("Start; ");
			if (raceType == 2) {
				for (int i = 0; i < laps - 1; i++) {
					out.write("Varvning" + (i + 1) + "; ");
				}
			}
			out.write("Mål\n");
			Driver tDriver;

			for (Integer i : register.keySet()) {
				tDriver = register.get(i);
				out.write(checkError(i, tDriver.startTime(), tDriver
						.finishTime()));
			}
			// Close the output stream
			out.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}

	private String checkError(int i, List<String> startTime,
			List<String> finishTime) {
		StringBuilder sb = new StringBuilder();
		sb.append(i + "; ");
		String totalTimeCheck = "";
		String name = register.get(i).getName();
		checkName(sb, name);
		if (raceType == 2) {
			sb.append(register.get(i).getNumberOfLaps() + "; ");
		}
		totalTimeCheck = checkTotaltime(startTime, finishTime, sb,
				totalTimeCheck);
		if (raceType == 2 && (laps != 0)) {
			if (!(startTime.size() == 0 || finishTime.size() == 0)) {
			for (int j = 0; j < laps; j++) {
				sb.append(register.get(i).getLapTime(j)
						+ "; ");
			}
			}
		}
		
		checkStartTime(startTime, sb);
		if (raceType == 2 && finishTime.size() != 0) {
			int check = finishTime.size() - 1;
			if (check > laps - 1) {
				check = laps - 1;
			}
			for (int e = 0; e < check; e++) {
				sb.append(finishTime.get(e) + "; ");
			}
			for(int b = finishTime.size() - 1; b < laps-1; b++){
				sb.append("; ");
			}
			
			if(Time.timeDiff(startTime.get(0), finishTime
						.get(finishTime.size() - 1)).compareTo("3.00.00")>=0){
				sb.append(finishTime.get(finishTime.size() - 1));
				
			}else{
				sb.append("Slut?");
			}
			
		} else {
			checkFinishTime(finishTime, sb);
		}
		checkIfManyStartTime(startTime, sb);
		if (raceType == 1) {
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
			if (raceType == 2) {
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

	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		driver.setName(name);
		register.put(startNumber, driver);
	}

	public void addClass(Integer startNumber, String c) {
		Driver driver = getDriver(startNumber);
		driver.addClass(c);
		register.put(startNumber, driver);
	}

	public Driver getDriver(Integer key) {
		return register.containsKey(key) ? register.get(key) : new Driver();
	}

	public int size() {
		return register.size();
	}

}