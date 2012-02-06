package main;


public class ReadFinishFile extends FileIO {
	
	/**
	 * The constructor which takes the file name of the finish file as argument
	 * 
	 * @param sorter
	 *            	the target sorter
	 * @param fileName
	 * 				the name of finish file           
	 */
	public ReadFinishFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addFinishTime(riderID, name);
		
	}

}