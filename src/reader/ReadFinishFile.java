package reader;

import race.Race;

/**
 * This class can read a finish file and add the finish time + driverId to the
 * race object.
 */
public class ReadFinishFile extends FileIO {

	/**
	 * Creates the ReadFinishFile that reads a file.
	 * 
	 * @param race
	 *            The race that the files belongs to
	 * @param fileName
	 *            The name of the file
	 */
	public ReadFinishFile(Race race, String fileName) {
		super(race, fileName);
	}

	/**
	 * Method is called by readFile() and adds the finish time and riderID to
	 * race object.
	 */
	protected void add() {
		race.addFinishTime(riderID, time);
	}

}