package main;

import java.io.FileNotFoundException;
import java.util.TreeMap;

import reader.ReadFinishFile;
import reader.ReadNameFile;
import reader.ReadStartFile;


public abstract class Race {
	public TreeMap<Integer, Driver> index;
	
	//Result
	Result result;
	
	//Sorter
	
	// Variables
	private String stopFile;
	private String startFile;
	

	// Readers
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;
	
	public Race(String startFile, String stopFile, String nameFile, int raceTime, int laps){
		rnf = new ReadNameFile(this, nameFile);
		rsf = new ReadStartFile(this, startFile);
		rff = new ReadFinishFile(this, stopFile);
		index = new TreeMap<Integer, Driver>();
	
	
	}
	
	public abstract void getResult(TreeMap<Integer, Driver> index);
	
	public void computeTotalTime() {
		System.out.println("Syns jag h�r");
		// Names are put in the TreeMap from the name file
					try {
						rnf.readFile();
						rsf.readFile();
						rff.readFile();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Start file is read, and start times are put in the register
					// Finish file is read, and finish times are put in the register
					getResult(index);
					
	}

	public void addStartTime(Integer startNumber, String time) {
		Driver driver = getDriver(startNumber);
		driver.addStartTime(time);
		index.put(startNumber, driver);

	}

	public void addFinishTime(Integer startNumber, String time) {
		Driver driver = getDriver(startNumber);
		driver.addFinishTime(time);
		index.put(startNumber, driver);

	}

	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		driver.setName(name);
		index.put(startNumber, driver);

	}

	public void addClass(Integer startNumber, String c) {
		Driver driver = getDriver(startNumber);
		driver.addClass(c);
		index.put(startNumber, driver);

	}

	public Driver getDriver(Integer key) {
		return index.containsKey(key) ? index.get(key) : new Driver();
	}

	public int size() {
		return index.size();
	}
	
	
	public void writeResultFile(String name){
		//sorter.sort();
	}
}
