package race;

import java.util.ArrayList;
import java.util.TreeMap;

import result.LapResult;
import result.Result;
import result.SpecialDistanceResult;
import result.StageResult;

import main.Driver;
/**
* NOT USED AT THE MOMENT
*/
public class StageRace extends Race{
	Result result;
	int stages;
	protected String nameFile;
	String resultat;
	private String specialDistances;
	private String[] specialDistancesSplit;
	private int factor;
	
	public StageRace(String startFile, String stopFile, String nameFile, String resultFile, int stages,
			String startType, ArrayList<String> driverAttributes, String specialDistances, int factor) {
		super(startFile, stopFile, nameFile, startType, driverAttributes);
		this.nameFile = nameFile;
		this.resultat = resultFile;
		this.stages = stages;
		this.specialDistances = specialDistances;
		splitSpecialDistances();
		this.factor = factor;
		
	}
	
	
	public void getResult(TreeMap<Integer, Driver> index) {
		if (specialDistancesSplit.length > 1) {
			result = new SpecialDistanceResult(index, stages, resultat,driverAttributes,specialDistancesSplit, factor);
		} else {
			result = new StageResult(index, stages, resultat, driverAttributes);
		}
		result.writeResultFile();
	}
	
	private void splitSpecialDistances() {
		if (specialDistances != null) {
			specialDistancesSplit = specialDistances.split(",");
		} else {
			specialDistancesSplit = new String[0];
		}
	}
	
	
	

}
