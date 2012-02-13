package race;

import java.io.FileNotFoundException;
import java.util.TreeMap;

import main.Driver;

import reader.ReadFinishFile;
import reader.ReadNameFile;
import reader.ReadStartFile;
import result.Result;
import util.Time;


public abstract class Race {
	public TreeMap<Integer, Driver> index;
	
	//Result
	Result result;
	
	//Sorter
	
	// Variables
	protected String stopFile;
	protected String startFile;
	protected String startType;
	protected String nameFile;

	// Readers
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;
	
	public Race(String startFile, String stopFile, String nameFile, String startType){
		this.startFile= startFile;
		this.stopFile=stopFile;
		this.startType=startType;
		this.nameFile = nameFile;
		
		rnf = new ReadNameFile(this, nameFile);
		rsf = new ReadStartFile(this, startFile);
		rff = new ReadFinishFile(this, stopFile);
		index = new TreeMap<Integer, Driver>();
	
	
	}
	/**
	 * Creates a Lapresult that creates a result file
	 * 
	 * @param index
	 * 			   the treemap index        
	 */
	public abstract void getResult(TreeMap<Integer, Driver> index);
	/**
	 * Computes the totaltime from the start/stop-files and puts it in the treemap index.
	 * 
	 */
	public void computeTotalTime() {
		// Names are put in the TreeMap from the name file
					try {
						rnf.readFile();
						//1 = MassStart
						if(startType.equals("Masstart")){
						rsf.readFileMassStart();
						}else{
						rsf.readFile();
						}
						rff.readFile();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Start file is read, and start times are put in the register
					// Finish file is read, and finish times are put in the register
					getResult(index);
					
	}
	/**
	 * Creates a driver and adds a starttime to it and puts the created driver in the treemap index
	 * 
	 * @param startnumber
	 *            The registered startnumber
	 * @param time 
	 * 			the registered time.
	 */
	public void addStartTime(Integer startNumber, Time time) {
		Driver driver = getDriver(startNumber);
		driver.addStartTime(time);
		index.put(startNumber, driver);

	}
	/**
	 * Creates a driver and adds a finishtime to it and puts the created driver in the treemap index
	 * 
	 * @param startnumber
	 *            The registered startnumber
	 * @param time 
	 * 			the registered time.
	 */
	public void addFinishTime(Integer startNumber, Time time) {
		Driver driver = getDriver(startNumber);
		driver.addFinishTime(time);
		index.put(startNumber, driver);

	}
	/**
	 * Creates a driver and adds a name to it and puts the created driver in the treemap index
	 * 
	 * @param startnumber
	 *            The registered startnumber
	 * @param name 
	 * 			the drivers name.
	 */
	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		driver.setName(name);
		index.put(startNumber, driver);

	}
	/**
	 * Creates a driver and adds a class to it and puts the created driver in the treemap index
	 * 
	 * @param startnumber
	 *            The registered startnumber
	 * @param c 
	 * 			the drivers class.
	 */
	public void addClass(Integer startNumber, String c) {
		Driver driver = getDriver(startNumber);
		driver.addClass(c);
		index.put(startNumber, driver);

	}
	/**
	 * return a driver with key "key"
	 * 
	 * @param key
	 * 			   the driver key       
	 * @return driver
	 *            The searched driver 
	 */
	public Driver getDriver(Integer key) {
		return index.containsKey(key) ? index.get(key) : new Driver();
	}
	/**
	 * return the size of treemap index
	 *  
	 * @return int
	 *            The list index's size 
	 */
	public int size() {
		return index.size();
	}
	
	/**
	 * Writes a result-file with name "name"
	 * 
	 * @param name
	 * 			   the result-files name
	 */
	public void writeResultFile(String name){
		//sorter.sort();
	}
}
