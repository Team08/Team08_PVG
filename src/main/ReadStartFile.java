package main;

public class ReadStartFile extends FileIO {
	/**
	 * The constructor which takes the file name of the startfile as argument
	 * 
	 * @param sorter, fileName
	 *            target sorter object and the startfile name
	 */
	public ReadStartFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addStartTime(riderID, name);
		
	}

}