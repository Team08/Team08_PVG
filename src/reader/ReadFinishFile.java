package reader;

import race.Race;


public class ReadFinishFile extends FileIO {
	
	/**
	 * The constructor which takes the file name of the finish file as argument
	 * 
	 * @param sorter
	 *            	the target sorter
	 * @param fileName
	 * 				the name of finish file           
	 */
	public ReadFinishFile(Race race, String fileName) {
		super(race, fileName);
	}
	
	protected void add() {
		race.addFinishTime(riderID, time);
		
	}

}