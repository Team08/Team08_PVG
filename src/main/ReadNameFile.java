package main;

public class ReadNameFile extends FileIO {

	/**
	 * The constructor which takes the file name of the name file as argument
	 * 
	 * @param file
	 *            The file with the start numbers and names
	 */
	public ReadNameFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}

	protected void add() {
		sorter.addName(riderID, name);
	}

}