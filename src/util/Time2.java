package util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Time2 {

	/**
	 * Adds a leading zero to input if input is less than 10
	 * 
	 * @param minutes
	 *            The number to add a zero to if needed
	 * @return A string with a leading zero if input less than 10
	 * @throws NumberFormatException
	 *             If illegal number format
	 */
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

	/**
	 * Returns the difference between two times. finishTime must be greater than
	 * startTime
	 * 
	 * @param startTime
	 *            The start time
	 * @param finishTime
	 *            The finish time
	 * @return Time difference as a string
	 * @throws NumberFormatException
	 *             Thrown if illegal number format
	 */
	public static String timeDiff(String startTime, String finishTime)
	throws NumberFormatException {
		String[] temp = startTime.split("\\.");

		if (temp.length != 3) {
			throw new NumberFormatException();
		}
		int start = parseTime(temp);

		temp = finishTime.split("\\.");
		int finish = parseTime(temp);

		int totalInt = finish - start;

		if (totalInt < 0) {
			throw new NumberFormatException();
		}
		int minutes = (totalInt % 3600) / 60;
		String min = addZero(minutes);

		int seconds = (totalInt % 3600) % 60;
		String sec = addZero(seconds);

		String total = totalInt / 3600 + "." + min + "." + sec;

		return total;

	}
	
	private static int parseTime(String[] temp) throws NumberFormatException {
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		if (hour < 0 || minute < 0 || second < 0) {
			throw new NumberFormatException();
		}
		int start = 3600 * hour + 60 * minute + second;
		return start;
	}
	
	/**
	 * Returns a list of three strings, [0]hours, [1]minutes and [2]seconds
	 * 
	 * @return the list with strings
	 *
	 */
	public static String[] makeTimeList(){
		GregorianCalendar calendar = new GregorianCalendar();

		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String[] times = new String[3];
		String stringMinutes = Time2.addZero(minutes);
		String stringSeconds = Time2.addZero(seconds);

		times[0] = Integer.toString(hours);
		times[1] = stringMinutes;
		times[2] = stringSeconds;

		return times;
	}

}