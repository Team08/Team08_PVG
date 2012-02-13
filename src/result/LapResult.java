package result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import main.Driver;

import util.Time;
import util.Time2;

public class LapResult extends Result {
	private HashMap<String, TreeMap<Integer, Driver>> mapOfDiffRaceClasses;
	int laps;
	Time raceTime;

	String resultFile;

	public LapResult(TreeMap<Integer, Driver> index, int laps,
			String raceTimeString, String resultFile) {
		this.index = index;
		this.laps = laps;
		this.raceTime = new Time(raceTimeString + ".00");
		this.resultFile = resultFile;
	}

	public void writeResultFile() {
		try {

			System.out.println("hej");
			System.out.println(resultFile);
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
				String classes = tDriver.getClasses();

				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i,
						tDriver));

			}

			// for (Integer i : register.keySet()) {
			// tDriver = register.get(i);
			// out.write(checkError(i, tDriver.startTime(),
			// tDriver.finishTime()));
			// }

			for (String className : mapOfDiffRaceClasses.keySet()) {
				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses
						.get(className);
				out.write(sb.toString());
				out.write(className + "\n");
				for (Integer i : tm.keySet()) {
					tDriver = tm.get(i);
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
		for (int b = finishTime.size() - 1; b < laps - 1; b++) {
			sb.append("; ");
		}
		checkStartTime(startTime, sb);
		if (finishTime.size() != 0) {
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
			
			
			Time timeTemp = new Time(startTime.get(0).timeDiff(
					finishTime.get(finishTime.size() - 1)));
			if (timeTemp.lesserThan(raceTime)) {

				sb.append(finishTime.get(finishTime.size() - 1));

			} else {
				sb.append("Slut?");
			}

		}
		
		sb.append(totalTimeCheck);
		sb.append("\n");
		System.out.println(sb.toString());
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Result#checkIfManyFinishTime(java.util.List,
	 * java.lang.StringBuilder)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Result#checkIfManyStartTime(java.util.List,
	 * java.lang.StringBuilder)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Result#checkFinishTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkFinishTime(List<Time> finishTime, StringBuilder sb) {
		if (finishTime.size() == 0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.Result#checkStartTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkStartTime(List<Time> startTime, StringBuilder sb) {
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
	}

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
