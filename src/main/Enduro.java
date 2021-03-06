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
	private String sorted = "defaultSorted";
	private String raceTime = "";
	private String raceType = "";
	private String lapTime = "00.15";
	private int laps = 0;
	private String startType = "";
	private String attributeString = "";
	private ArrayList<String> driverAttributes = new ArrayList<String>();
	private String specialDistances;
	private int factor;
	private GenerateConfig genCon;

	/**
	 * The constructor which creates an Enduro object. Uses a config.properties
	 * file that the user needs enter values into in order to make the sorter 
	 * function as wanted.
	 * 
	 *@param String
	 *            [] args - argument array used if there are any arguments in it
	 */
	public Enduro(String[] args) {
		String cfg = "config.properties";
		if(args.length == 1){
			cfg = args[0];
		}
		if (args.length == 0 || args.length == 1) {
			Properties configFile = new Properties();
			genCon = new GenerateConfig(configFile);
			try {
				configFile.load(new FileInputStream(cfg));
				if(genCon.checkKey()){
					readConfigKeys(configFile);
					createRace();
				}else{
					System.err.println("Misslyckades med att läsa konfigurationsfilen");
				}

			} catch (FileNotFoundException e1) {
				System.err.println("Misslyckades med att läsa konfigurationsfilen, en ny har autogenerats.");
				System.err.println("Var god och fyll i config.properties filen och starta om programmet.");
				genCon.autogenerateConfig();
				System.exit(1);
			} catch (IOException e1) {				
				e1.printStackTrace();
			}

		} else {
			try {
				readKeysfromArgs(args);
				if (raceType.equals("varv")) {
					raceTime = args[6];
				}
				createRace();
			} catch (Exception e) {
				System.err.println("Error: Du måste skriva in alla argument");
			}}
	}

	/**
	 * Reads the arguments from the array of arguments that is a parameter to
	 * the program. This is only used if there are any arguments in the array
	 * when the program starts
	 * 
	 *@param String
	 *            [] args - argument array used
	 */
	private void readKeysfromArgs(String[] args) {
		start = args[0];
		stop = args[1];
		name = args[2];
		result = args[3];
		sorted = args[9];
		raceType = args[4];
		startType = args[5];
		startType = startType.toLowerCase();
		raceType = raceType.toLowerCase();
		laps = Integer.parseInt(args[7]);
		attributeString = args[8];
		String[] attributeArray = args[8].split("; ");

		for (int i = 0; i < attributeArray.length; i++) {
			driverAttributes.add(attributeArray[i]);
		}

		specialDistances = args[9];
		factor = Integer.parseInt(args[10]);
	}

	/**
	 * Reads the arguments from the configfile that belongs to the program This
	 * is only used if there are no arguments in the array when the program
	 * starts
	 * 
	 *@param configFile
	 *            - the configfile that the arguments to the program is read
	 *            from
	 */
	private void readConfigKeys(Properties configFile) {
		start = configFile.getProperty("STARTFILE").trim();
		stop = configFile.getProperty("STOPFILE").trim();
		name = configFile.getProperty("NAMEFILE").trim();
		result = configFile.getProperty("RESULTFILE").trim();
		sorted = configFile.getProperty("SORTEDFILE").trim();
		raceTime = configFile.getProperty("RACETIME").trim();
		raceType = configFile.getProperty("RACETYPE").trim().toLowerCase();
		lapTime = configFile.getProperty("LAPTIME").trim();
		startType = configFile.getProperty("STARTTYPE").trim().toLowerCase();
		laps = Integer.parseInt(configFile.getProperty("LAPS").trim());
		attributeString = configFile.getProperty("DRIVER_ATTRIBUTES").trim();
		if (raceType.equals("etapp")) {
			specialDistances = configFile.getProperty("SPECIAL_DISTANCES")
					.trim();
			factor = Integer.parseInt(configFile.getProperty("FACTOR").trim());
		}
		String[] attributeArray = attributeString.split(";");
		for (int i = 0; i < attributeArray.length; i++) {
			driverAttributes.add(attributeArray[i].trim());
		}

	}

	


	/**
	 * Creates a LapRace and calls the method computeTotalTime that will create
	 * resultfiles Prints which files the program has generated
	 * 
	 * There is functionality for a StageRace but it is not used.
	 */
	private void createRace() {
		if (raceType.equals("varv")) {
			race = new LapRace(start, stop, name, result, sorted, raceTime,
					laps, startType, driverAttributes, lapTime);
		} else if (raceType.equals("etapp")) {
			race = new StageRace(start, stop, name, result, laps, startType,
					driverAttributes, specialDistances, factor);
		}
		race.computeTotalTime();
		System.out.println("Programmet har genererat: " + result);
		if (!sorted.equals("")) {
			System.out.println("Programmet har genererat: " + sorted);
		}
	}

	/**
	 * Main program that initiates the Sorter program.
	 */
	public static void main(String[] args) {
		new Enduro(args);
	}
}