package result;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import main.Driver;
import main.Sorter;

public class SortedFile {
	private ArrayList<Driver> unsorted, sorted, noClass;
	private HashMap<String, ArrayList<Driver>> classSortedMap;
	private HashMap<String, TreeMap<Integer, Driver>> kaosMap;
	private Sorter sort;
	private RaceClassBuilder build;
	private int maxNbrOfLaps;

	public SortedFile(HashMap<String, TreeMap<Integer, Driver>> kaosMap, int maxNbrOfLaps) {
		this.maxNbrOfLaps = maxNbrOfLaps;
		this.kaosMap = kaosMap;
		sort = new Sorter();
		classSortedMap = new HashMap<String, ArrayList<Driver>>();
		noClass = new ArrayList<Driver>();
		
	}

	public void writeToFile() {
		betterMap();
		try {
			FileWriter fstream = new FileWriter("src/test/testfiles/SortedFile.txt");
			BufferedWriter writer = new BufferedWriter(fstream);
			for(String s: classSortedMap.keySet()){
				build = new RaceClassBuilder(maxNbrOfLaps);
				build.writeResult(classSortedMap.get(s));
				System.out.println(build.toString());
				writer.write(build.toString());
			}
			build = new RaceClassBuilder(maxNbrOfLaps);
			writer.write(build.toString());
			writer.close();
		} catch (Exception e) {

			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
		
	}

	private void betterMap() {
		for (String s : kaosMap.keySet()) {
			unsorted = new ArrayList<Driver>();
			sorted = new ArrayList<Driver>();
			TreeMap<Integer, Driver> temp = kaosMap.get(s);
			
			for (Integer i : temp.keySet()) {
				unsorted.add(temp.get(i));
			}
			
			sorted = sort.lapSort(unsorted);
			if (s.equals("Icke existerande startnummer")) {
				noClass = sorted;
			}else{
				classSortedMap.put(s, sorted);
			}
		}
	}

}
