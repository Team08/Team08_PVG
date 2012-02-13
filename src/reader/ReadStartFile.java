package reader;

import main.Race;


public class ReadStartFile extends FileIO {
	/**
	 * The constructor which takes the file name of the startfile as argument
	 * 
	 * @param sorter
	 *            target sorter
	 * @param fileName
	 * 			  the name of startfile
	 */
	public ReadStartFile(Race race, String fileName) {
		super(race, fileName);
	}
	
	protected void add() {
		race.addStartTime(riderID, name);
		
	}

}