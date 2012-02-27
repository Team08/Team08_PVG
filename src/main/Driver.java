package main;

import java.util.ArrayList;
import java.util.List;

import util.Time;

/**
 * The Driver class which represents a driver. The driver knows his id, his name, start times, 
 * finish times and which classes he's a part of.
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
	 * Returns the driver's starttimes as a list
	 * 
	 * @return The starttimes as a list
	 */
	public ArrayList<Time> startTime() {
		return startTime;
	}

	/**
	 * Returns the driver's finishtimes as a list.
	 * 
	 * @return The finishtimes as a list
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
	 * @return A time object which represents the lap time
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
	 * Sets the drivers id=startnumber
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the drivers id=startnumber
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the totaltime or -1 if no totaltime exists
	 * @return the totaltime or -1 
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
	 * Returns the drivers attributes
	 * @return the attributes
	 */
	public ArrayList<String> getAttributes(){
		return driverAttribute;
	}
	
	/**
	 * Returns a list of the laptimes that the driver has
	 * An empty list if there are no laptimes
	 * @return list of laptimes
	 */
	public ArrayList<String> listOfLapTimes(){
		ArrayList<String> lapTimes = new ArrayList<String>();
		if(startTime.size()!=0){
		for(int i = 0; i < finishTime.size(); i++){
			lapTimes.add(getLapTime(i));
		}}
		return lapTimes;
	}

	/**
	 * Metod f�r story 19, etapprace, anv�nds dock inte
	 */
	public String getStageTime(int i) {
		// Kolla s� det finns n�gon tid f�r etappen i
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
	 * Metod f�r story 19, etapprace, anv�nds dock inte
	 */
	public String getTotStageTime() {
		int sum = 0;
		for (int i = 0; i < startTime.size(); i++) {
			sum += addStageTime(i);
		}
		return new Time(sum).toString();
	}

	/**
	 * Metod f�r story 19, 20, etapprace och specialstr�ckor, anv�nds dock inte
	 */
	public String getNbrOfStages() {
		String s = Integer.toString(finishTime.size());
		if (startTime.size() < finishTime.size()) {
			s = Integer.toString(startTime.size());
		}
		return s;
	}

}
