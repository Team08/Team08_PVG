package race;

import java.util.TreeMap;

import result.LapResult;

import main.Driver;

import util.Time;


public class Varvrace extends Race{
	LapResult result;
	String raceTime;
	int laps;
	protected String nameFile;
	String resultat;

	public Varvrace(String start, String stop, String nameFile,String resultFile, String raceTime, int laps, String startType) {
		super(start, stop, nameFile,startType);
		this.raceTime = raceTime;
		this.laps = laps;
		this.nameFile = nameFile;
		this.resultat = resultFile;
	}

	@Override
	public void getResult(TreeMap<Integer, Driver> index) {
		result = new LapResult(index, laps, raceTime,resultat);
		result.writeResultFile();
		
	}

	

}

