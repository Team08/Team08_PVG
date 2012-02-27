package result;

import java.util.ArrayList;
import java.util.List;

import main.Driver;

import util.Time;

public class RaceClassBuilder {

	private int maxNbrOfLaps;
	private String raceClass;
	private StringBuilder sb;
	private ArrayList<Driver> driverList;
	private Time raceTime;
	ArrayList<String> lapTimes;
	private ArrayList<String> driverAttributes;

	public RaceClassBuilder(int nbrOfLaps, Time raceTime, ArrayList<String> driverAttributes) {
		this.raceTime = raceTime;
		maxNbrOfLaps = nbrOfLaps;
		sb = new StringBuilder();
		this.driverAttributes = driverAttributes;
	}

	public void writeResult(ArrayList<Driver> allDriversInAClass) {
		this.raceClass = allDriversInAClass.get(0).getRaceClass();
		driverList = allDriversInAClass;
		addRaceClass();
		addHeader();
		writeEachDriverResult();
	}

	private void writeEachDriverResult() {
		int plac = 1;
		for (Driver driver : driverList) {
			// Writes the id, info and result of each ---driver--- on one line
			// #################EXAMPLE##########################
			// Plac; StartNr; Namn; #Varv; Totaltid; Varv1; Varv2

			// //ANVÄNDS INTE LÄNGRE, VAR TÄNKT TILL DEN NYA DESIGNEN: CheckError checkError = new CheckError(driver); ////HÄR MÅSTE
			// ALLA EVENTUELLA FEL FIXAS!
			
			// Hämta ut totaltiden för föraren, returnerar -1 om starttid el sluttid fattas
			int totalTimeTemp = driver.totalTime();
			// Om totaltiden existerar och uppfyller den stipulerade tiden, skriv ut placering
			if(totalTimeTemp > -1 && raceTime.lesserThan(new Time(totalTimeTemp))){
				sb.append(plac + "; ");
				plac++;
			}else{
				sb.append("; ");
			}
			
			
			sb.append(driver.getId());
			sb.append("; ");
			
			if(driver.getName()==null){
				sb.append("Namn?");
			}else{
				sb.append(driver.getName());
			}
			sb.append("; ");
			
			//Attributes
			ArrayList<String> attributes = driver.getAttributes();
			for(int i = 0; i < attributes.size(); i++){
				sb.append(attributes.get(i) + "; ");
			}
			

			lapTimes = driver.listOfLapTimes();
			if(lapTimes.size()!=0){
				sb.append(lapTimes.size());
			}
			
			sb.append("; ");
			
			if(totalTimeTemp > -1) {
				sb.append(Time.totalTimeString(totalTimeTemp) + "; ");
			} else {
				sb.append("; ");
			}
			
			printLapTimes();
			sb.append("\n");
		}

	}

	private void printLapTimes() {
		for (String time : lapTimes) {
			sb.append(time.toString());
			sb.append("; ");
		}
		addSemiColon(maxNbrOfLaps - lapTimes.size());
	}
	
	private void addSemiColon(int nbrOfSemiColon) {
		for (int i = 0; i < nbrOfSemiColon; i++) {
			sb.append("; ");
		}
	}

	private void addRaceClass() {
		sb.append(raceClass + "\n");
	}

	/**
	 * Adds a header to the resultFile
	 */
	private void addHeader() {

		// StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start;
		// Varvning1; Varvning2; Mål
		StringBuilder tempSb = new StringBuilder();
		if(!driverAttributes.isEmpty()){
			for(int i = 0; i < driverAttributes.size(); i++){
				if(i < driverAttributes.size()-1){
					tempSb.append(driverAttributes.get(i) + "; ");
				}else{
					tempSb.append(driverAttributes.get(i));
				}
			}
		}
		
		sb.append("Plac; StartNr; Namn; " + tempSb.toString() + " #Varv; Totaltid; ");
		for (int i = 0; i < maxNbrOfLaps; i++) {
			sb.append("Varv");
			sb.append(i + 1 + "; ");
		}
		sb.append("\n");
	}
	
	public String toString(){
		return sb.toString();
	}

}
