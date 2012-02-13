package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeMap;

import race.Race;
import race.StageRace;
import race.LapRace;

import util.Time;
/**
 * The main class which initiates the Sorter program.
 * @author Team08
 *
 */
public class Enduro {
	protected TreeMap<Integer, Driver> register;
	private Race race;
	
	/**
	 * The constructor which creates an Enduro object.
	 * Enter a config file in order to make the sorter function
	 * as wanted. E.g. if there has been a lap race or a stage race
	 * 
	 * @param config file which the user creates before starting the
	 * 				 sorter program in order for it to produce the
	 * 				 wanted result
	 */
	public Enduro(String[] args) {
		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		String raceTime = "";
		String raceType = "";
		int distance = 0;
		String startType = "";

		if (args.length == 0) {
			Properties configFile = new Properties();
			try {
				configFile.load(new FileInputStream("config.properties"));
				start = configFile.getProperty("STARTFILE");
				stop = configFile.getProperty("STOPFILE");
				name = configFile.getProperty("NAMEFILE");
				result = configFile.getProperty("RESULTFILE");
				raceTime = configFile.getProperty("RACETIME");
				raceType = configFile.getProperty("RACETYPE");
				distance = Integer.parseInt(configFile.getProperty("DISTANCE"));				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else{
			try {
				start = args[0];
				stop = args[1];
				name = args[2];
				result = args[3];
				raceType = args[4];
				startType = args[5];
				raceType = raceType.toLowerCase();
				distance = Integer.parseInt(args[7]);
				if (raceType.equals("varv")) {
					raceTime = args[6];	
				}
			} catch (Exception e) {
				System.out.println("Error: Fel argument");
			}
		}
		if (raceType.equals("varv")) {
			race = new LapRace(start, stop, name, result, raceTime, distance,
				startType);
		}else if (raceType.equals("etapp")){
			race = new StageRace(start, stop, name, result, distance, startType);
		}
		race.computeTotalTime();
	}
	/**
     * Main program that initiates the Sorter program.
     *
     * @param the config file
     */
	public static void main(String[] args) {
			new Enduro(args);			
	}
}
