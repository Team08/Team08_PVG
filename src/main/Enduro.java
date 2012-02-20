package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

import race.Race;
import race.StageRace;
import race.LapRace;

import util.Time;

/**
 * The main class which initiates the Sorter program.
 * 
 * @author Team08
 * 
 */
public class Enduro {
	protected TreeMap<Integer, Driver> register;
	private Race race;
	private String start = "defaultStart";
	private String stop = "defaultStop";
	private String name = "defaultName";
	private String result = "defaultResult";
	private String raceTime = "";
	private String raceType = "";
	private int distance = 0;
	private String startType = "";
	private String[] attributeArray;
	private String attributeString = "";
	private ArrayList<String> driverAttributes = new ArrayList<String>();
	private String specialDistances;
	private int factor;
	private GenerateConfig genCon;

	/**
	 * The constructor which creates an Enduro object. Enter a config file in
	 * order to make the sorter function as wanted. E.g. if there has been a lap
	 * race or a stage race
	 * 
	 * @param config
	 *            file which the user creates before starting the sorter program
	 *            in order for it to produce the wanted result
	 */
	public Enduro(String[] args) {

		if (args.length == 0) {
			Properties configFile = new Properties();
			genCon = new GenerateConfig(configFile);
			try {
				configFile.load(new FileInputStream("config.properties"));
				if(genCon.checkKey()){
					start = configFile.getProperty("STARTFILE");
					stop = configFile.getProperty("STOPFILE");
					name = configFile.getProperty("NAMEFILE");
					result = configFile.getProperty("RESULTFILE");
					raceTime = configFile.getProperty("RACETIME");
					raceType = configFile.getProperty("RACETYPE");	
					startType = configFile.getProperty("STARTTYPE");				
					distance = Integer.parseInt(configFile.getProperty("DISTANCE"));				
					attributeString = configFile.getProperty("DRIVER_ATTRIBUTES");
					specialDistances = configFile.getProperty(("SPECIAL_DISTANCES"));
					factor = Integer.parseInt(configFile.getProperty(("FACTOR")));
				
					String[] attributeArray = attributeString.split("; ");
				
					for (int i = 0; i < attributeArray.length; i++) {
					driverAttributes.add(attributeArray[i]);
					}
					createRace();
				}else{
					System.err.println("Misslyckades med att läsa konfigurationsfilen");
				}

			} catch (FileNotFoundException e1) {
				System.err.println("Misslyckades med att läsa konfigurationsfilen, en ny har autogenerats");
				genCon.autogenerateConfig();
			} catch (IOException e1) {				
				System.err.println("");
				e1.printStackTrace();

			}

		} else {
			try {
				start = args[0];
				stop = args[1];
				name = args[2];
				result = args[3];
				raceType = args[4];
				startType = args[5];
				startType = startType.toLowerCase();
				raceType = raceType.toLowerCase();
				distance = Integer.parseInt(args[7]);

				attributeString = args[8];
				
				String[] attributeArray = args[8].split("; ");

				for (int i = 0; i < attributeArray.length; i++) {
					driverAttributes.add(attributeArray[i]);
				}
				

				specialDistances = args[9]; 
				factor = Integer.parseInt(args[10]);

				if (raceType.equals("varv")) {
					raceTime = args[6];
				}
				createRace();
			} catch (Exception e) {
				System.err.println("Error: Fel inmatning av argument");

			}}
		
		
	}
	
	
	private void createRace(){
		if (raceType.equals("varv")) {

			race = new LapRace(start, stop, name, result, raceTime, distance, startType, driverAttributes);
		}else if (raceType.equals("etapp")){
			race = new StageRace(start, stop, name, result, distance, startType, driverAttributes, specialDistances, factor);

		}
		race.computeTotalTime();
	}
	

	/**
	 * Main program that initiates the Sorter program.
	 * 
	 * @param the
	 *            config file
	 */

	public static void main(String[] args) {

		new Enduro(args);
	}
}
