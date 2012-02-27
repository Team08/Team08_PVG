package result;

import java.util.ArrayList;
import java.util.List;

import main.Driver;

import util.Time;

/**
 * Class that handles the writing of the sorted file
 * 
 * @author Team08
 *
 */
public class RaceClassBuilder {

	private int maxNbrOfLaps;
	private String raceClass;
	private StringBuilder sb;
	private ArrayList<Driver> driverList;
	private Time raceTime;
	ArrayList<String> lapTimes;

	/**
	 * The class that handles the build of the sorted resultfile
	 * 
	 * @param nbrOfLaps - the number of laps that should be written in the stringbuilder
	 * @param raceTime - the time that should be met if the driver has fulfilled the race
	 */
	public RaceClassBuilder(int nbrOfLaps, Time raceTime) {
		this.raceTime = raceTime;
		maxNbrOfLaps = nbrOfLaps;
		sb = new StringBuilder();
	}

	/**
	 * Writes the sorted file in the stringbuilder
	 * 
	 * @param allDriversInAClass - all the drivers that belong to the same class
	 */
	public void writeResult(ArrayList<Driver> allDriversInAClass) {
		this.raceClass = allDriversInAClass.get(0).getRaceClass();
		driverList = allDriversInAClass;
		addRaceClass();
		addHeader();
		writeEachDriverResult();
	}

	/**
	 * Writes the drivers result to the class stringbuilder
	 */
	private void writeEachDriverResult() {
		int plac = 1;
		for (Driver driver : driverList) {
			// Writes the id, info and result of each ---driver--- on one line
			// #################EXAMPLE##########################
			// Plac; StartNr; Namn; #Varv; Totaltid; Varv1; Varv2
			
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

	/**
	 * Writes the laptimes to the stringbuilder (only ; if there are no laptimes)
	 */
	private void printLapTimes() {
		for (String time : lapTimes) {
			sb.append(time.toString());
			sb.append("; ");
		}
		addSemiColon(maxNbrOfLaps - lapTimes.size());
	}
	
	/**
	 * adds ; to the stringbuilder
	 * 
	 * @param nbrOfSemiColon - the number of ; that should be added
	 */
	private void addSemiColon(int nbrOfSemiColon) {
		for (int i = 0; i < nbrOfSemiColon; i++) {
			sb.append("; ");
		}
	}

	/**
	 * adds a raceclass to the stringbuilder
	 */
	private void addRaceClass() {
		sb.append(raceClass + "\n");
	}

	/**
	 * Adds a header to the stringbuilder
	 */
	private void addHeader() {

		// StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start;
		// Varvning1; Varvning2; MÃ¥l

		sb.append("Plac; StartNr; Namn; #Varv; Totaltid; ");
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
