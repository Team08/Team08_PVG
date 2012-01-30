package main;

public class ReadFinishFile extends FileIO {

	public ReadFinishFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}

	@Override
	protected void add(Integer startNbr, String value) {
		sorter.addFinishTime(startNbr, value);
	}

}