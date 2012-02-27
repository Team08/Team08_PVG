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
 * This class builds the time results for stage races and can write the result
 * to result file.
 */
public class StageResult extends Result {
	private HashMap<String, TreeMap<Integer, Driver>> mapOfDiffRaceClasses;
	int stages;
	String resultFile;
	private ArrayList<String> driverAttributes;


	/**
	 * Creates StageResult.
	 * 
	 * @param index
	 *            the treeMap that contains essential information to write a
	 *            result file
	 * @param stages
	 *            how many stages time that will be displayed in the result file
	 * @param resultFile
	 *            the name of the resultFile that this class will compute the
	 *            results to
	 */
	public StageResult(TreeMap<Integer, Driver> index, int stages,
			String resultFile, ArrayList<String> driverAttributes) {
		this.index = index;
		this.stages = stages;
		this.resultFile = resultFile;
		this.driverAttributes = driverAttributes;
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
			if(!(driverAttributes == null) && !driverAttributes.isEmpty()){
				for(int i = 0; i < driverAttributes.size(); i++){
						sb.append(driverAttributes.get(i) + "; ");
				}
			}
			sb.append("Totaltid; ");
			sb.append("#Etapper; ");
			for (int i = 0; i < stages; i++) {
				sb.append("Etapp" + (i + 1) + "; ");
			}
			for (int i = 0; i < stages - 1; i++) {
				sb.append("Start" + (i + 1) + "; Mål" + (i + 1) + "; ");
			}
			sb.append("Start" + (stages) + "; Mål" + stages);
			// Vi antar att vi har alla starttime och finishtime för alla
			// ettapper i index TreeMap.
			// dvs finishtime[0] - starttime[0] = etapp[1], finishtime[1] -
			// starttime[1] = etapp[2], Mål = etapp[0]+etapp[1]...
			Driver tDriver;
			mapOfDiffRaceClasses = new HashMap<String, TreeMap<Integer, Driver>>();
			ArrayList<Driver> notSortedDrivers = new ArrayList<Driver>();
			for (Integer i : index.keySet()) {
				tDriver = index.get(i);
				String classes = tDriver.getRaceClass();

				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i,
						tDriver));
				notSortedDrivers.add(tDriver);
			}
			
			//Skriver ut till fil
			//Hämtar TreeMap med className som key så att vi får ut alla som tillhör klassen.
			for (String className : mapOfDiffRaceClasses.keySet()) {
				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses.get(className);
				out.write(className + "\n");
				out.write(sb.toString());
				for (Integer i : tm.keySet()) {
					tDriver = tm.get(i);
					out.write(checkErrorStageRace(i, tDriver));
				}
			}
			out.close();

		} catch (Exception e) {
			System.err.println("Error: Misslyckades med att skriva resultatfilen för etapplopp");
			System.exit(1);
		}

	}

	public String checkErrorStageRace(int i, Driver driver) {
		StringBuilder sb = new StringBuilder();
		// StartNR
		sb.append(i + "; ");
		// Namn
		sb.append(driver.getName() + "; ");
		// KlubbOmFinns Hämta ATTRIBUTERNA
		// sb.append(getDriverAttributes(driver));
		// Totaltid för alla genomförda etapper
		sb.append(driver.getTotStageTime() + "; ");
		// # ANTAL Etapper som driver har genomfört.
		sb.append(driver.getNbrOfStages() + "; ");
		// totaltid för etapp1
		sb.append(getStageTime(driver));
		// Starttid för etapp1;
		sb.append(getTimes(driver)); // här blir det fel.
		
		return sb.toString();
	}

	public String getDriverAttributes(Driver driver) {
		return "EJIMPMLEMENTERD";
	}

	public String getStageTime(Driver driver) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stages; i++) {
			sb.append(driver.getStageTime(i) + "; ");
		}
		return sb.toString();
	}

	public String getTimes(Driver driver) {
		StringBuilder sb = new StringBuilder();
		List<Time> startTemp = driver.startTime();
		List<Time> finishTemp = driver.finishTime();
		System.out.println(123);
		System.out.println(startTemp.get(0).toString());
		System.out.println(321);
		for (int i = 0; i < stages; i++) {
			if (startTemp.get(i) != null) {
				sb.append(startTemp.get(i).toString());
			}
			sb.append("; ");
			if (finishTemp.get(i) != null) {
				sb.append(finishTemp.get(i).toString());
			}
			if(i != stages-1){
				sb.append("; ");
			}
		}
		return sb.toString();

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
	 * Check if there are ManyFinishTimes.
	 * 
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	public void checkIfManyFinishTime(List<Time> finishTime, StringBuilder sb) {
		if (finishTime.size() > 1) {
			sb.append("; Flera måltider?");
			for (int j = 1; j <= (finishTime.size() - 1); j++) {
				sb.append(" " + finishTime.get(j));
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
	 *            the StringBuilder to append to
	 * @return A string with error-notations if any invalid time was found
	 */
	@Override
	public String checkTotaltime(List<Time> startTime, List<Time> finishTime, StringBuilder sb) {
		return null;
	}

	@Override
	public String checkError(int i, List<Time> startTime, List<Time> finishTime) {
		return null;
	}

}
