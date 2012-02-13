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
import util.Time2;

/**
 * This class builds the time results for lap races and can write the result to
 * result file.
 */
public class LapResult extends Result {
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
	 *            the duration of the race in hours
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
			ArrayList<Driver> notSortedDrivers = new ArrayList<Driver>();
			for (Integer i : index.keySet()) {
				tDriver = index.get(i);
				if(tDriver.getName() == null) {
					tDriver.addClass(nonExistingNbr);
				}
				String classes = tDriver.getClasses();
				
				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i,
						tDriver));
				//nytt
				notSortedDrivers.add(tDriver);
				
				//Nytt
			}
			Sorter sorter = new Sorter();
			sorter.lapSort(notSortedDrivers);
			

			// for (Integer i : register.keySet()) {
			// tDriver = register.get(i);
			// out.write(checkError(i, tDriver.startTime(),
			// tDriver.finishTime()));
			// }
			TreeMap<Integer, Driver> nonExistingNbrMap = null;
			for (String className : mapOfDiffRaceClasses.keySet()) {
				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses
						.get(className);
				if(className.equals(nonExistingNbr)) {
					nonExistingNbrMap = tm;
				} else {
					out.write(className + "\n");
					out.write(sb.toString());
					for (Integer i : tm.keySet()) {
						tDriver = tm.get(i);
						out.write(checkError(i, tDriver.startTime(), tDriver
								.finishTime()));
					}
				}
			}
			
			if(nonExistingNbrMap != null) {
				out.write(nonExistingNbr + "\n");
				out.write(sb.toString());
				for (Integer i : nonExistingNbrMap.keySet()) {
					tDriver = nonExistingNbrMap.get(i);
					out.write(checkError(i, tDriver.startTime(), tDriver
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
	 * <<<<<<< HEAD
	 * @param i
	 *            The current index
	 * @param startTime
	 *            A list of start time
	 * @param finishTime
	 *            A list of finish times
	 * @return The string contains the result time for target driver plus
	 *         error-notations if there are any
	 */
	@Override
	public String checkError(int i, List<Time> startTime, List<Time> finishTime) {

		StringBuilder sb = new StringBuilder();
		String totalTimeCheck = "";
		sb.append(i + "; ");
		checkName(sb, index.get(i).getName());

		sb.append(index.get(i).getNumberOfLaps() + "; ");

		totalTimeCheck = checkTotaltime(startTime, finishTime, sb,
				totalTimeCheck);
		if (laps != 0) {
			if (!(startTime.size() == 0 || finishTime.size() == 0)) {
				for (int j = 0; j < laps; j++) {
					sb.append(index.get(i).getLapTime(j) + "; ");
				}
			}
		}
//		for (int b = finishTime.size() - 1; b < laps - 1; b++) {
//			sb.append("; ");
//		}
		checkStartTime(startTime, sb);
		Time timeTemp = new Time(0);
		if(finishTime.size() != 0 && startTime.size() != 0){
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
			
			if ((!timeTemp.greaterThan(raceTime))&&check<=laps) {
				sb.append(finishTime.get(finishTime.size() - 1));
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

		
		sb.append(totalTimeCheck);
		sb.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * Check if there are ManyFinishTimes.
	 * 
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	@Override
	public void checkIfManyFinishTime(List<Time> finishTime, StringBuilder sb) {
		if (finishTime.size() > 1) {
			sb.append("; Flera måltider?");
			for (int j = 1; j <= (finishTime.size() - 1); j++) {
				sb.append(" " + finishTime.get(j));
			}
		}
	}

	/**
	 * Check if there are ManyStartTimes.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	@Override
	public void checkIfManyStartTime(List<Time> startTime, StringBuilder sb) {
		if (startTime.size() > 1) {
			sb.append("; Flera starttider?");
			for (int j = 1; j <= (startTime.size() - 1); j++) {
				sb.append(" " + startTime.get(j));
			}
		}

	}

	/**
	 * Check if the finish time list contains any finish time.
	 * 
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	@Override
	public void checkFinishTime(List<Time> finishTime, StringBuilder sb) {
		if (finishTime.size() == 0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
	}

	/**
	 * Check if the start time list contains any finish time.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	@Override
	public void checkStartTime(List<Time> startTime, StringBuilder sb) {
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
	}

	/**
	 * Check if the finish time list contains any finish time.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 * @param totalCheck
	 *            the totalcheck
	 * @return A string of the total time with
	 *         error-notations if any invalid time was found
	 */
	@Override
	public String checkTotaltime(List<Time> startTime, List<Time> finishTime,
			StringBuilder sb, String totalCheck) {
		Time totalTime = new Time(0);
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		} else {
			totalTime = new Time(startTime.get(0).timeDiff(
					finishTime.get(finishTime.size() - 1)));

			sb.append(totalTime.toString() + "; ");
			if (totalTime.lesserThan(new Time("0.15.00"))) {
				totalCheck = "; Omöjlig Totaltid?";
			}

		}
		return totalCheck;
	}
}
