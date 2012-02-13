package main;

import java.util.TreeMap;

import race.Race;
import race.Varvrace;

import util.Time;


public class Enduro {
	protected TreeMap<Integer, Driver> register;
	
	private Race race;

	public Enduro(String[] args) {
			String start = "defaultStart";
			String stop = "defaultStop";
			String name = "defaultName";
			String result = "defaultResult";
			String raceTime = "";
			String raceType = "";
			int laps = 0;
			String startType = "";

			try {
				start = args[0];
				stop = args[1];
				name = args[2];
				result = args[3];
				raceType = args[4];
				startType = args[5];
				raceType = raceType.toLowerCase();
				if(raceType.equals("varv")){
					raceTime = args[6];
					laps = Integer.parseInt(args[7]);
					race = new Varvrace(start, stop, name, result, raceTime, laps, startType);
				}
			} catch (Exception e) {
				System.out.println("Error: Fel argument");
			}
		
		
		
		race.computeTotalTime();
	}

	public static void main(String[] args) {
		new Enduro(args);

	}
}
