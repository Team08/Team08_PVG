package main;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	private String name;
	private List<String> startTime = new ArrayList<String>();
	private String finishTime;

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

	public String finishTime() {
		return finishTime;
	}

	/**
	 * Inserts a new finish time The current finish time is replaced by the new
	 * time
	 * 
	 * @param time
	 */
	public void addFinishTime(String time) {
		finishTime = time;

	}

	public String totalTime() {
<<<<<<< HEAD
		String[] temp = startTime.get(0).split("\\.");
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		int start = 3600 * hour + 60 * minute + second;
=======

		String[] temp = startTime.split("\\.");
		int start = parseTime(temp);
>>>>>>> 8e7cae3a8da8a588a71e9458a254d4266e4303d7

		temp = finishTime.split("\\.");
		int finish = parseTime(temp);

		int totalInt = finish - start;
		
		int minutes = (totalInt % 3600) / 60;
		String min  = addZero(minutes);
		
		int seconds = (totalInt % 3600) % 60;
		String sec = addZero(seconds);
		
		
		String total = totalInt / 3600 + "." + min + "." + sec;

		return total;
	}

	private String addZero(int minutes) {
		String min;
		if (minutes < 10) {
			min = "0" + minutes;
		} else {
			min = Integer.toString(minutes);
		}
		return min;
	}

	private int parseTime(String[] temp) {
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		int start = 3600 * hour + 60 * minute + second;
		return start;
	}
}
