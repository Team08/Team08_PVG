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

	@Override
	protected void add(Integer startNbr, String value) {
		sorter.addName(startNbr, value);
	}

}