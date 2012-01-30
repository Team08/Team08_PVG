package main;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	private String name;
	private List<String> startTime = new ArrayList<String>();
	private List<String> finishTime = new ArrayList<String>();
	
	public Driver() {

	}

	public Driver(String name) {
		this.name = name;
	}

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

	public List<String> startTime() {
		return startTime;
	}

	public List<String> finishTime() {
		return finishTime;
	}

	/**
     * Inserts a new finish time
     *
     * @param time    The new time that replaces the old one
     */
	public void addFinishTime(String time) {
		finishTime.add(time);

	}

	public void setName(String name) {
		this.name = name;		
	}
	
	
	/**
	 * Returns the time from last registered time to the lap i
	 * @param i    The lapnumber, starting from 0
	 * @return    The time as a String
	 */
	public String getLapTime(int i) {
		if(i == 0){}
		return " ";
	}
	
	/**
	 * Converts a time represented as a string into a time represented as an integer
	 * @param temp    The time as a string vector
	 * @return    The time as an integer
	 */
	
	public int getNumberOfLaps() {
		// TODO Auto-generated method stub
		return 0;
	}
}
