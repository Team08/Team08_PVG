package main;

import java.util.ArrayList;
import java.util.List;

import util.Time;

/**
 * The Driver class which represents a driver. The driver knows his id, his name, start times, 
 * finish times and which classes he's a part of. The start times and finish times lists are
 * sorted in increasing order.
 * 
 * @author Team08
 * 
 */
public class Driver {
	private String name;
	private ArrayList<Time> startTime = new ArrayList<Time>(); 
	private ArrayList<Time> finishTime = new ArrayList<Time>();
	private String classes = "";
	private int id;
	private ArrayList<String> driverAttribute = new ArrayList<String>();

	/**
	 * The constructor which creates the Driver without a name
	 * 
	 */
	public Driver() {

	}

	/**
	 * The constructor which creates the Driver with a name
	 * 
	 * @param name
	 *            the drivers name
	 */
	public Driver(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the driver
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Inserts a new start time in the list. Multiple start times are allowed.
	 * 
	 * @param time
	 *            New start time
	 */
	public void addStartTime(Time time) {
		startTime.add(time);
		time.sortArrayInOrder(startTime);
	}

	/**
	 * Inserts a new finish time in the list. Multiple finish times are allowed
	 * 
	 * @param time
	 *            New finish time
	 */
	public void addFinishTime(Time time) {
		finishTime.add(time);
		time.sortArrayInOrder(finishTime);
	}

	/**
	 * Returns the driver's start times as a list
	 * 
	 * @return The start times as a list
	 */
	public ArrayList<Time> startTime() {
		return startTime;
	}

	/**
	 * Returns the driver's finish times as a list.
	 * 
	 * @return The finish times as a list
	 */
	public ArrayList<Time> finishTime() {
		return finishTime;
	}

	/**
	 * Sets the name of the driver. The current name is replaced.
	 * 
	 * @param name
	 *            The new name of the driver
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the time of the lap i (starting from 0)
	 * 
	 * @param i
	 *            The lap number, starting from 0
	 * @return The lap time as a string
	 */
	public String getLapTime(int i) {
		int laptime;
		if (i == 0) {			
			laptime = startTime.get(0).timeDiff(finishTime.get(0));
		} else if (finishTime.size() > i) {
			laptime = finishTime.get(i - 1).timeDiff(finishTime.get(i));
		} else {
			return "";
		}
		return new Time(laptime).toString();
	}

	/**
	 * The driver is added to a new class. The old class is overwritten
	 * 
	 * @param c
	 *            The new class the driver is a part of
	 * 
	 */
	public void addClass(String c) {
		classes = c;
	}

	/**
	 * Returns the current class the driver is a part of
	 * 
	 * @return A string with the class name
	 */
	public String getRaceClass() {
		return classes;
	}

	/**
	 * Returns the number of laps the driver has driven
	 * 
	 * @return The number of laps as an integer
	 */
	public int getNumberOfLaps() {
		
		return finishTime.size();
	}

	/**
	 * Sets the drivers id=start number
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the drivers id=start number
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the total time or -1 if no total time exists
	 * @return the total time or -1 
	 */
	public int totalTime() {
		if(startTime.size() > 0 && finishTime.size() > 0) {
			return startTime.get(0).timeDiff(finishTime.get(finishTime.size() - 1));
		}
		return -1;
	}
	
	/**
	 * Adds an attribute to the driver
	 * 
	 * @param attribute - the attribute to add
	 */
	public void addAttribute(String attribute){
		driverAttribute.add(attribute);
	}
	
	
	/**
	 * Returns the drivers attributes as a list
	 * @return the attributes
	 */
	public ArrayList<String> getAttributes(){
		return driverAttribute;
	}
	
	/**
	 * Returns a list of the lap times that the driver has An empty list if
	 * there are no lap times
	 * 
	 * @return list of lap times
	 */
	public ArrayList<String> listOfLapTimes() {
		ArrayList<String> lapTimes = new ArrayList<String>();
		if (startTime.size() != 0) {
			for (int i = 0; i < finishTime.size(); i++) { // Denna metod bör ses
															// över. Om det
															// finns två finish
															// times i listan
															// men ingen
				lapTimes.add(getLapTime(i));               // start time kommer vi returnera
												           // en tom lista, när listan
												           // egentligen bör innehålla ett
												          // varv.
			}
		}
		return lapTimes;
	}

	/**
	 * This method is used in Story 19, which is NOT implemented. Therefore the method isn't used.
	 */
	public String getStageTime(int i) {
		// Kolla så det finns någon tid för etappen i
		if (i > startTime().size() - 1 || i > finishTime().size() - 1) {
			return ("");
			// Annars returnera tiden etappen i
		} else {
			return (new Time(finishTime().get(i).timeDiff(startTime().get(i)))
					.toString());
		}
	}

	private int addStageTime(int i) {
		if (i > startTime().size() - 1 || i > finishTime().size() - 1) {
			return 0;
		} else {
			return (finishTime().get(i).timeDiff(startTime().get(i)));
		}
	}

	/**
	 * This method is used in Story 19, which is NOT implemented. Therefore the method isn't used.
	 */
	public String getTotStageTime() {
		int sum = 0;
		for (int i = 0; i < startTime.size(); i++) {
			sum += addStageTime(i);
		}
		return new Time(sum).toString();
	}

	/**
	 * This method is used in Story 19 and 20, which is NOT implemented. Therefore the method isn't used.
	 */
	public String getNbrOfStages() {
		String s = Integer.toString(finishTime.size());
		if (startTime.size() < finishTime.size()) {
			s = Integer.toString(startTime.size());
		}
		return s;
	}
}
