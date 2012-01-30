package main;


public class ReadFinishFile extends FileIO {
	
	/**
	 * The constructor which takes the file name of the finishfile as argument
	 * 
	 * @param sorter
	 *            	the target sorter
	 * @param fileName
	 * 				the name of finishfile           
	 */
	public ReadFinishFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addFinishTime(riderID, name);
		
	}
}