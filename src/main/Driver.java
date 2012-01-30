package main;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	private String name;
	private List<String> startTime = new ArrayList<String>();
	private List<String> finishTime = new ArrayList<String>();
	private List<String> classes = new ArrayList<String>();

	public Driver() {

	}
	
	/**
	 * The constructor which creates the Driver
	 * 
	 * @param name
	 *            the drivers name
	 */
	public Driver(String name) {
		this.name = name;
	}
	/**
	 * return driver name
	 * 
	 * @return returns name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Inserts a new start time The current start time is replaced by the new
	 * time
	 * 
	 * @param time
	 *            The start time
	 */
	public void addStartTime(String time) {
		startTime.add(time);

	}
	/**
	 * returns a list of starttimes
	 * 
	 * @return returns a list of starttimes
	 */
	public List<String> startTime() {
		return startTime;
	}
	/**
	 * returns a list of finishtimes
	 */
	public List<String> finishTime() {
		return finishTime;
	}

	/**
	 * Inserts a new finish time
	 * 
	 * @param time
	 *            The new time that replaces the old one
	 */
	public void addFinishTime(String time) {
		finishTime.add(time);

	}
	/**
	 * Inserts a new name
	 * 
	 * @param name
	 *            The new name that replaces the old one
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the time from last registered time to the lap i
	 * 
	 * @param i
	 *            The lapnumber, starting from 0
	 * @return The time as a String
	 */
	public String getLapTime(int i) {
		String laptime;
		if (i == 0) {
			laptime = Time.timeDiff(startTime.get(0), finishTime.get(0));
		}else if (finishTime.size() > i){
			laptime = Time.timeDiff(finishTime.get(i-1), finishTime.get(i));
		}else{
			laptime = "";
		}
		return laptime;
	}
	/**
	 * Inserts a new class to the list classes
	 * 
	 * @param c
	 *            
	 */
	public void addClass(String c) {
		classes.add(c);
	}
	
	/**
	 * Returns a list with all classes
	 * @return A list with classes
	 */
	public List<String> classes() {
		return classes;
	}
	
	/**
	 * Returns the number of laps
	 * 
	 * @return The number of laps as an integer
	 */
	public int getNumberOfLaps() {
		return finishTime.size();
	}
}
