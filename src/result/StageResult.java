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
 * This class builds the time results for stage races and can write the result to
 * result file.
 */
public class StageResult extends Result{
	private HashMap<String, TreeMap<Integer, Driver>> mapOfDiffRaceClasses;
	int stages;
	String resultFile;
	
	
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
	public StageResult(TreeMap<Integer, Driver> index, int stages, String resultFile) {
		this.index = index;
		this.stages = stages;
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
			sb.append("Totaltid; ");
			sb.append("#Etapper; ");
			for (int i = 0; i < stages; i++) {
				sb.append("Etapp" + (i + 1) + "; ");
			}
			for (int i = 0; i < stages-1; i++) {
				sb.append("Start" + (i + 1) + "; Mål" + (i + 1) + "; ");
			}
			sb.append("Start" + (stages) + "; Mål"+stages);
			
			Driver tDriver;
			mapOfDiffRaceClasses = new HashMap<String, TreeMap<Integer, Driver>>();
			ArrayList<Driver> notSortedDrivers = new ArrayList<Driver>();
			for (Integer i : index.keySet()) {
				tDriver = index.get(i);
				String classes = tDriver.getClasses();
				
				mapOfDiffRaceClasses.put(classes, addTreeMap(classes, i,
						tDriver));
				//nytt
				notSortedDrivers.add(tDriver);
				
				//Nytt
			}
//			Sorter sorter = new Sorter();
//			sorter.lapSort(notSortedDrivers);
//			
//
//			// for (Integer i : register.keySet()) {
//			// tDriver = register.get(i);
//			// out.write(checkError(i, tDriver.startTime(),
//			// tDriver.finishTime()));
//			// }
//
			for (String className : mapOfDiffRaceClasses.keySet()) {
				TreeMap<Integer, Driver> tm = mapOfDiffRaceClasses
						.get(className);
				out.write(className + "\n");
				out.write(sb.toString());
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
//
	}
//
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
		// TODO Auto-generated method stub
		return null;
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
	 * Computes the totaltime if possible and appends it to the stringbuilder. If there is no totaltime --.--.-- is appended
	 * The method returns a string with an error-notation if the totaltime was impossible otherwise an empty String
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
	public String checkTotaltime(List<Time> startTime, List<Time> finishTime,
			StringBuilder sb) {
		Time totalTime = new Time(0);
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		}
//		} else {
//			
//		}

		return null;
	}

}
