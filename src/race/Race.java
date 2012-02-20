package race;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import main.Driver;

import reader.ReadFinishFile;
import reader.ReadNameFile;
import reader.ReadStartFile;
import result.Result;
import util.Time;

/**
 * An abstract class which represents a race.
 * 
 * @author Team08
 * 
 */

public abstract class Race {
	public TreeMap<Integer, Driver> index;

	// Result
	Result result;

	// Sorter

	// Variables
	protected String stopFile;
	protected String startFile;
	protected String startType;
	protected String nameFile;
	protected ArrayList<String> driverAttributes;

	// Readers
	private ReadNameFile rnf;
	private ReadStartFile rsf;
	private ReadFinishFile rff;

	/**
	 * The standard constructor which initiates the FileIO readers
	 * 
	 * @param startFile
	 *            the name of the start file
	 * @param stopFile
	 *            the name of the stop file
	 * @param nameFile
	 *            the name of the name file
	 * @param startType
	 *            the name of the start type (varv, etapp)
	 */
	public Race(String startFile, String stopFile, String nameFile, String startType, ArrayList<String> driverAttributes) {
		this.startFile = startFile;
		this.stopFile = stopFile;
		this.startType = startType;
		this.nameFile = nameFile;
		this.driverAttributes = driverAttributes; 

		rnf = new ReadNameFile(this, nameFile);
		rsf = new ReadStartFile(this, startFile);
		rff = new ReadFinishFile(this, stopFile);
		index = new TreeMap<Integer, Driver>();

	}

	/**
	 * Creates a Result that creates a result file
	 * 
	 * @param index
	 *            the treemap index
	 */
	public abstract void getResult(TreeMap<Integer, Driver> index);

	/**
	 * Reads the files and puts them in the treemap index, then computes the
	 * total time from the start/stop-files.
	 * 
	 * The result file will have been written after this methods ends.
	 */
	public void computeTotalTime() {
		// Names are put in the TreeMap from the name file
		try {
			rnf.readFile();
			if (startType.equals("masstart")) {
				rsf.readFileMassStart();
			} else {
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
	 * Creates a driver and adds a start time to it and puts the created driver
	 * in the treemap index. Multiple start times are allowed.
	 * 
	 * The driver does not know his start number, this is stored in the treemap
	 * instead.
	 * 
	 * @param startnumber
	 *            The registered start number
	 * @param time
	 *            the registered time.
	 */
	public void addStartTime(Integer startNumber, Time time) {
		Driver driver = getDriver(startNumber);
		driver.setId(startNumber);
		driver.addStartTime(time);
		index.put(startNumber, driver);

	}

	/**
	 * Creates a driver and adds a new finish time to it and puts the created driver
	 * in the treemap index. Multiple finish times are allowed.
	 * 
	 * The driver does not know his start number, this is stored in the treemap
	 * instead.
	 * 
	 * @param startnumber
	 *            The registered start number
	 * @param time
	 *            the registered time.
	 */
	public void addFinishTime(Integer startNumber, Time time) {
		Driver driver = getDriver(startNumber);
		driver.setId(startNumber);
		driver.addFinishTime(time);
		index.put(startNumber, driver);

	}

	/**
	 * Creates a driver and adds a name to it and puts the created driver in the
	 * treemap index
	 * 
	 * The driver does not know his start number, this is stored in the treemap
	 * instead.
	 * 
	 * @param startnumber
	 *            The registered start number
	 * @param name
	 *            the drivers name.
	 */
	public void addName(Integer startNumber, String name) {
		Driver driver = getDriver(startNumber);
		// driver . set ID nummer måste finnas på dessa metoder.
		driver.setId(startNumber);
		driver.setName(name);
		index.put(startNumber, driver);

	}

	/**
	 * Creates a driver and adds a class to it and puts the created driver in
	 * the treemap index. The old class will be overwritten.
	 * 
	 * @param startnumber
	 *            The registered start number
	 * @param c
	 *            the drivers class.
	 */
	public void addClass(Integer startNumber, String c) {
		Driver driver = getDriver(startNumber);
		driver.addClass(c);
		index.put(startNumber, driver);

	}

	/**
	 * Checks if a driver exists in the treemap with a given key.
	 * If he exists the driver object will be returned, otherwise
	 * a new driver will be created.
	 * 
	 * @param key
	 *            the driver key which is used to see if the driver
	 *            exists in the treemap
	 * @return driver The searched driver, which will be a new driver
	 * 				  if the driver does not exist.
	 */
	public Driver getDriver(Integer key) {
		return index.containsKey(key) ? index.get(key) : new Driver();
	}

	/**
	 * return the size of treemap index
	 * 
	 * @return int The list index's size
	 */
	public int size() {
		return index.size();
	}
	
	public void addAttribute(int startNumber, String attribute){
		Driver driver = getDriver(startNumber);
		driver.addAttribute(attribute);
		index.put(startNumber, driver);
	}
}
