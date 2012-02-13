package result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import main.Driver;
import main.Sorter;

import util.Time;

/**
 * An sub class of Result which is used when a Lap Race has been used a
 * parameter to the main class in Enduro.
 * 
 * This class creates the result file after having tested the input files for
 * invalid parameters. If this is the case the result file still is created, but
 * the file will contain appropriate error messages.
 * 
 * @author Team08
 */
public class LapResult extends Result {
	private Sorter sorter;
	private HashMap<String, TreeMap<Integer, Driver>> mapOfDiffRaceClasses;
	int laps;
	Time raceTime;
	String resultFile;
	private String nonExistingNbr = "Icke existerande startnummer";

	/**
	 * Creates LapResult.
	 * 
	 * @param index
	 *            the treeMap that contains essential information to write a
	 *            result file
	 * @param laps
	 *            how many laps time that will be displayed in the result file
	 * @param raceTimeString
	 *            the time a driver needs to get over to have finished the race
	 * @param resultFile
	 *            the name of the resultFile that this class will compute the
	 *            results to
	 */
	public LapResult(TreeMap<Integer, Driver> index, int laps,
			String raceTimeString, String resultFile) {
		this.index = index;
		this.laps = laps;
		this.raceTime = new Time(raceTimeString + ".00");
		this.resultFile = resultFile;
	}

	/**
	 * Writes the result to the resultFile.
	 */
	public void writeResultFile() {
		sorter = new Sorter();
		try {

			FileWriter fstream = new FileWriter(resultFile);

			StringBuilder sb = new StringBuilder();
			BufferedWriter out = new BufferedWriter(fstream);
			sb.append("StartNr; Namn; ");

			sb.append("#Varv; ");

			sb.append("Totaltid; ");

			for (int i = 0; i < laps; i++) {
				sb.append("Varv" + (i + 1) + "; ");

			}
			sb.append("Start; ");

			for (int i = 0; i < laps - 1; i++) {
				sb.append("Varvning" + (i + 1) + "; ");
			}

			sb.append("Mål\n");

			Driver tDriver;

			mapOfDiffRaceClasses = new HashMap<String, TreeMap<Integer, Driver>>();

			for (Integer i : index.keySet()) {
				tDriver = index.get(i);

				if (tDriver.getName() == null) { //
					tDriver.addClass(nonExistingNbr); //

				}
				String classes = tDriver.getClasses(); //

				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i, // Lägger
																			// in
																			// en
																			// treemap
						tDriver)); // (treemap innehåller idnummer mappade till
									// respektive förare)
			} // i en klass

			ArrayList<Driver> unsortedListOfDriversInAClass; // arraylist som
																// används för
																// att plocka ut
																// alla förare
																// i en viss
																// klass för
																// att skickas
																// in till
																// sortern.
			ArrayList<Driver> sortedListOfDriversInAClass;
			List<Driver> nonExistingNbrList = null;

			for (String className : mapOfDiffRaceClasses.keySet()) {
				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses
						.get(className);

				unsortedListOfDriversInAClass = new ArrayList<Driver>(tm
						.values());
				sortedListOfDriversInAClass = sorter
						.lapSort(unsortedListOfDriversInAClass); // //Nu har vi
																	// en
																	// sorterad
																	// arraylist
																	// med alla
																	// förarna
																	// i en
																	// klass

				if (className.equals(nonExistingNbr)) {
					nonExistingNbrList = sortedListOfDriversInAClass;

				} else {
					out.write(className + "\n"); // Skriver ut klassnamn
					out.write(sb.toString()); // Skriver ut "linjal"
					for (Driver driver : sortedListOfDriversInAClass) { // Skriver
																		// varje
																		// person
																		// som
																		// hör
																		// till
																		// klassen.
						out.write(checkError(driver.getId(),
								driver.startTime(), driver //
										.finishTime()));
					}
				}
			}

			if (nonExistingNbrList != null) {

				out.write(nonExistingNbr + "\n");
				out.write(sb.toString());

				for (Driver driver : nonExistingNbrList) {
					out.write(checkError(driver.getId(), driver.startTime(),
							driver

							.finishTime()));
				}
			}

			// Close the output stream
			out.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}

	}

	private TreeMap<Integer, Driver> addTreeMap(String className, Integer i,
			Driver tDriver) {
		TreeMap<Integer, Driver> tm;
		if (mapOfDiffRaceClasses.containsKey(className)) {
			tm = mapOfDiffRaceClasses.get(className);
			tm.put(i, tDriver);
		} else {
			tm = new TreeMap<Integer, Driver>();
			tm.put(i, tDriver);
		}
		return tm;
	}

	/**
	 * Check if there are any invalid parameter and returns a result string line
	 * that contains error-notations if any invalid parameter found.
	 * 
	 * @param currentIndex
	 *            The current index
	 * @param startTime
	 *            A list of start time
	 * @param finishTime
	 *            A list of finish times
	 * @return The string contains the result time for target driver plus
	 *         error-notations if there are any
	 */
	@Override
	public String checkError(int currentIndex, List<Time> startTime, List<Time> finishTime) {
		StringBuilder sb = new StringBuilder();
		String totalTimeCheck = "";
		String totalLapCheck = "";
		sb.append(currentIndex + "; ");
		checkName(sb, index.get(currentIndex).getName());

		sb.append(index.get(currentIndex).getNumberOfLaps() + "; ");

		totalTimeCheck = checkTotaltime(startTime, finishTime, sb);
		if (!(startTime.size() == 0 || finishTime.size() == 0)) {
			String lapTime = "";
			for (int j = 0; j < laps; j++) {
				lapTime = index.get(currentIndex).getLapTime(j);
				Time lap = new Time(lapTime);
				if (lap.lesserThan(new Time("00.15.00"))) {
					if (!lap.equals(new Time(0))) {
						totalLapCheck = " Omöjlig varvtid ";
					}
				}
				sb.append(lapTime + "; ");
			}
		}
		checkStartTime(startTime, sb);
		Time timeTemp = new Time(0);
		if (finishTime.size() != 0 && startTime.size() != 0) {
			timeTemp = new Time(startTime.get(0).timeDiff(
					finishTime.get(finishTime.size() - 1)));
		}
		if (finishTime.size() != 0) {
			int check = finishTime.size() - 1;
			if (check > laps - 1) {
				check = laps - 1;
			}
			for (int e = 0; e < check; e++) {
				sb.append(finishTime.get(e) + "; ");
			}
			if ((!timeTemp.greaterThan(raceTime)) && check <= laps) {
				sb.append(finishTime.get(finishTime.size() - 1) + "; ");

			}
		}
		for (int b = finishTime.size() - 1; b < laps - 1; b++) {
			sb.append("; ");
		}

		if (timeTemp.greaterThan(raceTime)) {
			sb.append(finishTime.get(finishTime.size() - 1));

		} else {
			sb.append("Slut?");
		}

		sb.append(totalLapCheck);
		checkIfManyStartTime(startTime, sb);
		sb.append(totalTimeCheck);
		sb.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Check if there are many start times and appends the time or error to sb.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param sb
	 *            the target StringBuilder to append to
	 */
	public void checkIfManyStartTime(List<Time> startTime, StringBuilder sb) {
		if (startTime.size() > 1) {
			sb.append("; Flera starttider?");
			for (int j = 1; j <= (startTime.size() - 1); j++) {
				sb.append(" " + startTime.get(j));
			}
		}

	}

	/**
	 * Computes the totaltime if possible and appends it to the stringbuilder.
	 * If there is no totaltime --.--.-- is appended The method returns a string
	 * with an error-notation if the totaltime was impossible otherwise an empty
	 * String
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to <<<<<<< HEAD
	 * @return A string with error-notations if any invalid time was found
	 *         =======
	 * @param totalCheck
	 *            the totalcheck
	 * @return A string of the total time with error-notations if any invalid
	 *         time was found >>>>>>> 99c23db0e95b41872459078ea00bd83b53a1be7d
	 */
	@Override
	public String checkTotaltime(List<Time> startTime, List<Time> finishTime,
			StringBuilder sb) {
		Time totalTime = new Time(0);
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		} else {
			totalTime = new Time(startTime.get(0).timeDiff(
					finishTime.get(finishTime.size() - 1)));

			sb.append(totalTime.toString() + "; ");
			if (totalTime.lesserThan(new Time("0.15.00"))) {
				return "; Omöjlig Totaltid?";
			}
		}
		return "";
	}
}
