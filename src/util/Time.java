package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Time implements Comparable{
	private int time;

	public Time(int time) {
		this.time = time;
	}

	
	private int time(){
		return time;
	}
	
	public Time(String representTime) {
		String[] temp = representTime.split("\\.");
		time = parseTime(temp);
	}

	// BORDE EJ BEHÖVAS MEN FINNS FÖR ATT DE ÄR SÅ LÄTT ATT IMPLEMENTERA
	public void setTime(int time) {
		this.time = time;
	}

	// BORDE EJ BEHÖVAS MEN FINNS FÖR ATT DE ÄR SÅ LÄTT ATT IMPLEMENTERA
	public void setTime(String time) {
		String[] temp = time.split("\\.");
		this.time = parseTime(temp);
	}

	public int getTime() {
		return time;
	}

	// EJ TESTADE
	private int parseTime(String[] temp) throws NumberFormatException {
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		if (hour < 0 || minute < 0 || second < 0) {
			throw new NumberFormatException();
		}
		int start = 3600 * hour + 60 * minute + second;
		return start;
	}

	// EJ TESTADE
	public static String addZero(int minutes) throws NumberFormatException {
		if (minutes < 0)
			throw new NumberFormatException("No negative numbers are allowed");
		String min;
		if (minutes < 10) {
			min = "0" + minutes;
		} else {
			min = Integer.toString(minutes);
		}
		return min;
	}

	public boolean equals(Time t2) {
		if (time - t2.getTime() != 0) {
			return false;
		}
		return true;
	}

	public boolean greaterThan(Time t2) {
		if (!(time > t2.getTime())) {
			return false;
		}
		return true;
	}

	public boolean lesserThan(Time time2) {
		if (!(time < time2.getTime())) {
			return false;
		}
		return true;
	}

	public String toString() {
		int minutes = (time % 3600) / 60;
		String min = addZero(minutes);

		int seconds = (time % 3600) % 60;
		String sec = addZero(seconds);

		String total = time / 3600 + "." + min + "." + sec;

		return total;
	}

	public int timeDiff(Time t2) {
		if (time > t2.getTime()) {
			return time - t2.getTime();
		}else{
			return t2.getTime() - time;
		}
	}

	public int compareTo(Object arg0) {
		final int SMALLER = -1;
		final int EQUAL = 0;
		final int BIGGER = 1;

		if (this.time() == ((Time) arg0).time()) {
			return EQUAL;
		}
		if (this.time() < ((Time) arg0).time()) {
			return SMALLER;
		}
		return BIGGER;
	}

	public ArrayList<Time> sortArrayInOrder(List<Time> times) {
		Collections.sort(times);
		return (ArrayList<Time>) times;
	}

}
