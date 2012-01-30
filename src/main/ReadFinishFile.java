package main;


public class ReadFinishFile extends FileIO {

	public ReadFinishFile(Sorter sorter, String fileName) {
		super(sorter, fileName);
	}
	
	protected void add() {
		sorter.addFinishTime(riderID, name);
		
	}
	
}