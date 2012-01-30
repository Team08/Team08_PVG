package main;

public class ReadStartFile extends FileIO {

	public ReadStartFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}

	@Override
	protected void add(Integer startNbr, String value) {
		sorter.addStartTime(startNbr, value);
	}

}