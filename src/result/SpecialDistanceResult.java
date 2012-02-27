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
 * NOT USED AT THE MOMENT
 * 
 * 
 * An sub class of Result which is used when a Lap Race has been used a
 * parameter to the main class in Enduro.
 * 
 * This class creates the result file after having tested the input files for
 * invalid parameters. If this is the case the result file still is created, but
 * the file will contain appropriate error messages.
 * 
 * @author Team08
 */
public class SpecialDistanceResult extends Result {
	private Sorter sorter;
	private HashMap<String, TreeMap<Integer, Driver>> mapOfDiffRaceClasses;
	int stages;
	Time raceTime;
	String resultFile;
	private String nonExistingNbr = "Icke existerande startnummer";
	private String[] specialDistances;
	private int factor;
	private Time totalTime = new Time(0);
	private ArrayList<String> driverAttributes;

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
	public SpecialDistanceResult(TreeMap<Integer, Driver> index, int laps, String resultFile, ArrayList<String> driverAttributes, String[] specialDistances, int factor) {
		this.index = index;
		this.stages = laps;
		this.resultFile = resultFile;
		this.specialDistances = specialDistances;
		this.factor = factor;
		this.driverAttributes = driverAttributes;
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
			
			if(!driverAttributes.isEmpty()){
				for(int i = 0; i < driverAttributes.size(); i++){
						sb.append(driverAttributes.get(i) + "; ");
				}
			}

			sb.append("#Etapper; ");

			sb.append("Totaltid; ");

			for (int i = 0; i < stages; i++) {
				checkAndAppendStagesToHeader(sb, i+1);
			}
			

			for (int i = 0; i < stages-1; i++) {
				sb.append("Start" + (i + 1) + "; Mål" + (i + 1) + "; ");
			}
			sb.append("Start" + (stages) + "; Mål"+stages +"\n");


			Driver tDriver;

			mapOfDiffRaceClasses = new HashMap<String, TreeMap<Integer, Driver>>();
			for (Integer i : index.keySet()) {
				tDriver = index.get(i);

				if (tDriver.getName() == null) { 
					tDriver.addClass(nonExistingNbr); 

				}

				String classes = tDriver.getRaceClass(); //

				
				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i, tDriver)); 
			} 
			
			
			ArrayList<Driver> unsortedListOfDriversInAClass; 
			ArrayList<Driver> sortedListOfDriversInAClass;
			List<Driver> nonExistingNbrList = null;
			for (String className : mapOfDiffRaceClasses.keySet()) {

				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses.get(className);
				unsortedListOfDriversInAClass = new ArrayList<Driver>(tm.values());	
				sortedListOfDriversInAClass = sorter.lapSort(unsortedListOfDriversInAClass, raceTime); 
				
				if (className.equals(nonExistingNbr)) {
					nonExistingNbrList = sortedListOfDriversInAClass;

				} else {

					out.write(className + "\n"); 
					out.write(sb.toString());
					for (Driver driver : sortedListOfDriversInAClass) {
						String s = generateNewLine(driver);
						out.write(s);
					}
				}
				
			}

			if (nonExistingNbrList != null) {

				out.write(nonExistingNbr + "\n");
				out.write(sb.toString());

				for (Driver driver : nonExistingNbrList) {
					String s = generateNewLine(driver);
					out.write(s);
				}
			}
			out.close();
			

		} catch (Exception e) {
			System.err.println("Error: Misslyckades med att skriva resultatfilen för specialsträckor.");

			System.exit(1);
		}
//		SortedFile sorted = new SortedFile(mapOfDiffRaceClasses, stages, raceTime);
//		sorted.writeToFile();
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


	
	private void checkAndAppendStagesToHeader(StringBuilder sb, int stage) {
		boolean isChanged = false;
		for (int i = 0; i < specialDistances.length; i++) {
			if (Integer.parseInt(specialDistances[i]) == stage) {
				sb.append("E" + stage + "*" + factor + "; ");
				isChanged = true;
			} 
		}
		if (!isChanged) {
			sb.append("Etapp" + stage + "; ");
		}
	}
	
	private String generateNewLine(Driver driver) {
		StringBuilder sb = new StringBuilder();
		this.totalTime = new Time(0);
		sb.append(driver.getId() + "; " + driver.getName() + "; "); //Adds ID and name to line
		sb.append(generateAttributeLine(driver));
		sb.append(driver.getNbrOfStages() +"; ");
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < stages; i++) {
			temp.append(generateStageTime(driver, i) + "; ");
		}
		sb.append(generateTotalTime(driver) + "; ");
		sb.append(temp.toString());
		sb.append(getTimes(driver) + "\n");
		this.totalTime = new Time(0);
		return sb.toString();
		
	}
	

	private String generateAttributeLine(Driver driver) {
		StringBuilder sb = new StringBuilder();
		if (driver.getAttributes() != null) {
			for (String attribute : driver.getAttributes()) {
				sb.append(attribute + "; ");
			}
		}
		return sb.toString();
	}
	
	private String generateTotalTime(Driver driver) {
		return totalTime.toString();
	}
	
	private String generateStageTime(Driver driver, int i) {
		String s = driver.getStageTime(i);
		Time t = new Time(s);
		for (int k = 0; k < specialDistances.length; k++) {
			if (Integer.parseInt(specialDistances[k]) == i+1) {
				t = t.multiplyTimeBy(factor, t);
			} 
		}
		this.totalTime = totalTime.addToTime(t);
		return t.toString();
	}

	@Override
	public String checkError(int i, List<Time> startTime, List<Time> finishTime) {
		return null;
	}

	@Override
	public String checkTotaltime(List<Time> startTime, List<Time> finishTime,
			StringBuilder sb) {
		return null;
	
	}

	private String getTimes(Driver driver) {
		StringBuilder sb = new StringBuilder();
		List<Time> startTemp = driver.startTime();
		List<Time> finishTemp = driver.finishTime();
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
}
