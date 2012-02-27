package race;

import java.util.ArrayList;
import java.util.TreeMap;

import result.LapResult;

import main.Driver;

import util.Time;

/**
 * A sub class of the abstract Race class. This class will be used when a lap
 * race is used in the sorter.
 * 
 * @author Team08
 * 
 */
public class LapRace extends Race {
	LapResult result;
	String raceTime;
	int laps;
	protected String nameFile;
	String resultat;
	String sortedFile;
	String lapTime;

	/**
	 * The standard constructor
	 * 
	 * @param start
	 *            the name of the start file
	 * @param stop
	 *            the name of the finish file
	 * @param nameFile
	 *            the name of the name file
	 * @param resultFile
	 *            the name of the result file
	 * @param raceTime
	 *            The minimum total time the race is ongoing (hh.mm)
	 * @param laps
	 *            the number of laps which are to be written to the file
	 * @param startType
	 *            the type of start (e.g. "masstart")
	 * @param lapTime - the minimum time of a lap
	 */

	public LapRace(String start, String stop, String nameFile,
			String resultFile, String sortedFile, String raceTime, int laps, String startType,
			ArrayList<String> driverAttributes, String lapTime) {
		super(start, stop, nameFile, startType, driverAttributes);
		this.raceTime = raceTime;
		this.laps = laps;
		this.nameFile = nameFile;
		this.resultat = resultFile;
		this.sortedFile = sortedFile;
		this.lapTime = lapTime;

	}

	/**
	 * Creates a LapResult that creates and writes the result file.
	 * 
	 * @param index
	 *            the treemap index with start numbers and drivers
	 */
	public void getResult(TreeMap<Integer, Driver> index) {
		result = new LapResult(index, laps, raceTime, resultat, sortedFile, driverAttributes, lapTime);
		result.writeResultFile();
		
	}

}
