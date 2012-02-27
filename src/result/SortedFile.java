package result;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import util.Time;

import main.Driver;
import main.Sorter;

/**
 * The class that takes care of the sorted resultfile
 * 
 * @author Team08
 *
 */
public class SortedFile {
	private ArrayList<Driver> unsorted, sorted, noClass;
	private HashMap<String, ArrayList<Driver>> classSortedMap;
	protected HashMap<String, TreeMap<Integer, Driver>> kaosMap;
	private Sorter sort;
	private RaceClassBuilder build;
	private int maxNbrOfLaps;
	private Time raceTime;
	private String sortedFile;

	/**
	 * The constructor for the sortedfile
	 * 
	 * @param kaosMap - the map that contains the information needed to write the file
	 * @param maxNbrOfLaps - the number of laps that should be written in the resultfile
	 * @param raceTime - the time that should be met if the driver has fulfilled the race
	 * @param sortedFile - the name of the sortedfile
	 */
	public SortedFile(HashMap<String, TreeMap<Integer, Driver>> kaosMap,
			int maxNbrOfLaps, Time raceTime, String sortedFile) {
		this.raceTime = raceTime;
		this.maxNbrOfLaps = maxNbrOfLaps;
		this.kaosMap = kaosMap;
		sort = new Sorter();
		classSortedMap = new HashMap<String, ArrayList<Driver>>();
		noClass = new ArrayList<Driver>();
		this.sortedFile = sortedFile;

	}

	/**
	 * Writes the sorted information to a file
	 */
	public void writeToFile() {
		betterMap();
		if (!(sortedFile.equals("")|| sortedFile == null)) {
			try {
				FileWriter fstream = new FileWriter(sortedFile);
				BufferedWriter writer = new BufferedWriter(fstream);
				for (String s : classSortedMap.keySet()) {
					build = new RaceClassBuilder(maxNbrOfLaps, raceTime);
					build.writeResult(classSortedMap.get(s));
					writer.write(build.toString());
				}
				build = new RaceClassBuilder(maxNbrOfLaps, raceTime);

				if (noClass.size() > 0) {
					build.writeResult(noClass);
				}

				writer.write(build.toString());
				writer.close();

			} catch (Exception e) {

				System.err.println("Error: Misslyckades med att skriva den sorterade filen");
				e.printStackTrace();
				System.exit(1);
			}

		} else {

		}

	}

	/**
	 * Puts the information in a map that is easier to handle
	 */
	private void betterMap() {
		for (String s : kaosMap.keySet()) {
			unsorted = new ArrayList<Driver>();
			sorted = new ArrayList<Driver>();
			TreeMap<Integer, Driver> temp = kaosMap.get(s);

			for (Integer i : temp.keySet()) {
				unsorted.add(temp.get(i));
			}

			sorted = sort.lapSort(unsorted, raceTime);
			if (s.equals("Icke existerande startnummer")) {
				noClass = sorted;
			} else {
				classSortedMap.put(s, sorted);
			}
		}
	}

}