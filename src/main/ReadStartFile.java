package main;

public class ReadStartFile extends FileIO {
	/**
	 * The constructor which takes the file name of the start file as argument
	 * 
	 * @param sorter
	 *            target sorter
	 * @param fileName
	 * 			  the name of start file
	 */
	public ReadStartFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addStartTime(riderID, name);
		
	}

}