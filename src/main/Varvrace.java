package main;

import java.util.TreeMap;


public class Varvrace extends Race{
	LapResult result;
	int raceTime;
	int laps;
	String nameFile;
	String resultat;

	public Varvrace(String start, String stop, String nameFile,String resultFile, int raceTime, int laps) {
		super(start, stop, nameFile, raceTime, laps);
		this.raceTime = raceTime;
		this.laps = laps;
		this.nameFile = nameFile;
		this.resultat = resultFile;
		
	}

	@Override
	public void getResult(TreeMap<Integer, Driver> index) {
		result = new LapResult(index, laps, raceTime);
		result.writeResultFile(resultat);
		
	}

	

}

