package main;

import java.util.ArrayList;
import java.util.List;

import util.Time;
import util.Time2;

public class Driver {
	private String name;
	private List<Time> startTime = new ArrayList<Time>();
	private List<Time> finishTime = new ArrayList<Time>();
	private String classes = "";

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
	 * Inserts a new start time The current start time is replaced by the new
	 * time
	 * 
	 * @param time
	 *            The start time
	 */
	public void addStartTime(Time time) {
		startTime.add(time);

	}

	/**
	 * Inserts a new finish time
	 * 
	 * @param time
	 *            The new time that replaces the old one
	 */
	public void addFinishTime(Time time) {
		finishTime.add(time);

	}

	/**
	 * Returns the driver's classes as a list
	 * 
	 * @return The classes as a list
	 */
	public List<Time> startTime() {
		return startTime;
	}

	/**
	 * Returns the driver's finish times as a list
	 * 
	 * @return The finish times as a list
	 */
	public List<Time> finishTime() {
		return finishTime;
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
	 *            The lapnumber, starting from 0
	 * @return The time as a String
	 */
	public Time getLapTime(int i) {
		int laptime;
		if (i == 0) {
			laptime = startTime.get(0).timeDiff(finishTime.get(0));
		} else if (finishTime.size() > i) {
			laptime = finishTime.get(i - 1).timeDiff(finishTime.get(i));
		} else {
			laptime = 0;
		}
		return new Time(laptime);
	}

	/**
	 * Inserts a new class to the list classes
	 * 
	 * @param c
	 * 
	 */
	public void addClass(String c) {
		classes = c;
	}

	/**
	 * Returns a list with all classes
	 * @return 
	 * 
	 * @return A list with classes
	 */
	public String getClasses() {
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
