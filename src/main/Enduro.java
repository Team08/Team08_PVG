package main;

import java.util.TreeMap;

import reader.ReadFinishFile;
import reader.ReadNameFile;
import reader.ReadStartFile;

public class Enduro {
	protected TreeMap<Integer, Driver> register;
	
	private Race race;
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;

	public Enduro(String[] args) {
		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		
		String raceType = "";
		start = args[0];
		stop = args[1];
		name = args[2];
		result = args[3];
		raceType = args[4];

		
		
	
		raceType = raceType.toLowerCase();
		if (raceType.equals("varv")) {
			System.out.println("Hit borde jag komma");
			int raceTime = Integer.parseInt(args[5]);
			int laps = Integer.parseInt(args[6]);
			race = new Varvrace(start, stop, name, result, raceTime, laps);
		}
		
		
		race.computeTotalTime();
		
//		Sorter sorter = new Sorter(start, stop, name, raceType, raceTime, laps);
//		sorter.writeResultFile(result);
		
	}

	public static void main(String[] args) {
		new Enduro(args);

	}
}
