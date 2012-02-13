package reader;

import main.Race;




public class ReadFinishFile extends FileIO {
	
	/**
	 * The constructor which takes the file name of the finishfile as argument
	 * 
	 * @param sorter
	 *            	the target sorter
	 * @param fileName
	 * 				the name of finishfile           
	 */
	public ReadFinishFile(Race race, String fileName) {
		super(race, fileName);
	}
	
	protected void add() {
		race.addFinishTime(riderID, name);
		
	}
}