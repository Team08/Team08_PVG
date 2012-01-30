package main;

public class ReadStartFile extends FileIO {

	public ReadStartFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addStartTime(riderID, name);
		
	}

}