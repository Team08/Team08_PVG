package race;

import java.util.TreeMap;

import result.LapResult;
import result.StageResult;

import main.Driver;

public class StageRace extends Race{
	StageResult result;
	int stages;
	protected String nameFile;
	String resultat;
	
	public StageRace(String startFile, String stopFile, String nameFile, String resultFile, int stages,
			String startType) {
		super(startFile, stopFile, nameFile, startType);
		this.nameFile = nameFile;
		this.resultat = resultFile;
		this.stages = stages;
		
	}

	public void getResult(TreeMap<Integer, Driver> index) {
		result = new StageResult(index, stages, resultat);
		result.writeResultFile();

	}

}
