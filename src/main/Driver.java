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
		String[] temp = startTime.get(0).split("\\.");

		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		int start = 3600 * hour + 60 * minute + second;

		temp = finishTime.split("\\.");
		hour = Integer.parseInt(temp[0]);
		minute = Integer.parseInt(temp[1]);
		second = Integer.parseInt(temp[2]);
		int finish = 3600 * hour + 60 * minute + second;
		int totalInt = finish - start;

		String min;
		if (((totalInt % 3600) / 60) < 10) {
			min = "0" + (totalInt % 3600) / 60;
		} else {
			min = Integer.toString((totalInt % 3600) / 60);
		}

		String sec;
		if (((totalInt % 3600) % 60) < 10) {
			sec = "0" + (totalInt % 3600) % 60;
		} else {
			sec = Integer.toString((totalInt % 3600) % 60);
		}

		String total = totalInt / 3600 + "." + min + "." + sec;

		return total;
	}
}
