package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

import race.Race;
import race.Varvrace;

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
		String start = "defaultStart";
		String stop = "defaultStop";
		String name = "defaultName";
		String result = "defaultResult";
		String raceTime = "";
		String raceType = "";
		int laps = 0;
		String startType = "";
		String attributeString = "";
		ArrayList<String> driverAttributes = new ArrayList<String>();

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
				laps = Integer.parseInt(configFile.getProperty("LAPS"));
				attributeString = configFile.getProperty("DRIVER_ATTRIBUTES");
				String[] attributeArray = attributeString.split(";");

				for (int i = 0; i < attributeArray.length; i++) {
					driverAttributes.add(attributeArray[i]);
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
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
				raceType = raceType.toLowerCase();
				if (raceType.equals("varv")) {
					raceTime = args[6];
					laps = Integer.parseInt(args[7]);
					race = new Varvrace(start, stop, name, result, raceTime,
							laps, startType, driverAttributes);
				}
			} catch (Exception e) {
				System.out.println("Error: Fel argument");
			}
		}
		race = new Varvrace(start, stop, name, result, raceTime, laps,
				startType, driverAttributes);
		race.computeTotalTime();
	}

	/**
	 * Main program that initiates the Sorter program.
	 * 
	 * @param the
	 *            config file
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			new Enduro(args);
		}

	}
}
