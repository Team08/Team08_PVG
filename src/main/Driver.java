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
	 * Returns the name of the driver
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a new start time
	 * 
	 * @param time
	 *            The start time
	 */
	public void addStartTime(String time) {
		startTime.add(time);

	}

	/**
	 * Returns the driver's classes as a list
	 * 
	 * @return The classes as a list
	 */
	public List<String> startTime() {
		return startTime;
	}

	/**
	 * Returns the driver's finish times as a list
	 * 
	 * @return The finish times as a list
	 */
	public List<String> finishTime() {
		return finishTime;
	}

	/**
	 * Adds a new finish time
	 * 
	 * @param time
	 *            The new time
	 */
	public void addFinishTime(String time) {
		finishTime.add(time);

	}

	/**
	 * Set the name of the driver. The current name is replaced.
	 * 
	 * @param name
	 *            The new name of the driver
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the time from last registered time to the lap i
	 * 
	 * @param i
	 *            The lap number, starting from 0
	 * @return The time as a String
	 */
	public String getLapTime(int i) {
		String laptime;
		if (i == 0) {
			laptime = Time.timeDiff(startTime.get(0), finishTime.get(0));
		} else if (finishTime.size() > i) {
			laptime = Time.timeDiff(finishTime.get(i - 1), finishTime.get(i));
		} else {
			laptime = "";
		}
		return laptime;
	}

	/**
	 * Adds a new class to the list classes
	 * 
	 * @param c
	 *            The class
	 * 
	 */
	public void addClass(String c) {
		classes.add(c);
	}

	/**
	 * Returns a list with all classes
	 * 
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