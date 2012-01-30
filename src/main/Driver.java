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
	
	/**
	 * Returns the total time
	 *
	 * [Returns the time given when subtracting the finishing time from the starting time]
	 *
	 * @return The total time represented as a string
	 */
	public String totalTime() {
		String[] temp = startTime.get(0).split("\\.");
		int start = parseTime(temp);


		temp = finishTime.get(0).split("\\.");
		int finish = parseTime(temp);

		int totalInt = finish - start;
		
		int minutes = (totalInt % 3600) / 60;
		String min  = addZero(minutes);
		
		int seconds = (totalInt % 3600) % 60;
		String sec = addZero(seconds);
		
		
		String total = totalInt / 3600 + "." + min + "." + sec;

		return total;
	}
	/**
	 * Makes sure that the right format is applied on minutes and seconds.
	 *
	 * Converts a number less than ten from the format "X" to "0X"]
	 *
	 * @param minutes Can actually be "seconds" as well.  
	 * @return Returns the time in minutes/or seconds in the correct format.
	 */
	private String addZero(int minutes) {
		String min;
		if (minutes < 10) {
			min = "0" + minutes;
		} else {
			min = Integer.toString(minutes);
		}
		return min;
	}
	/**
	 * Converts a time represented as a string into a time represented as an integer
	 * @param temp    The time as a string vector
	 * @return    The time as an integer
	 */
	private int parseTime(String[] temp) {
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		int start = 3600 * hour + 60 * minute + second;
		return start;
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
