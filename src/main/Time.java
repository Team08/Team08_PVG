package main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Time {
		

	public static String addZero(int minutes) {
		String min;
		if (minutes < 10) {
			min = "0" + minutes;
		} else {
			min = Integer.toString(minutes);
		}
		return min;
	}
	
	public static String timeDiff(String startTime, String finishTime) {
		String[] temp = startTime.split("\\.");
		int start = parseTime(temp);

		temp = finishTime.split("\\.");
		int finish = parseTime(temp);

		int totalInt = finish - start;

		int minutes = (totalInt % 3600) / 60;
		String min = addZero(minutes);

		int seconds = (totalInt % 3600) % 60;
		String sec = addZero(seconds);

		String total = totalInt / 3600 + "." + min + "." + sec;

		return total;
		
	}

	private static int parseTime(String[] temp) {
		int hour = Integer.parseInt(temp[0]);
		int minute = Integer.parseInt(temp[1]);
		int second = Integer.parseInt(temp[2]);
		int start = 3600 * hour + 60 * minute + second;
		return start;
	}
	public static String[] makeTimeList(){
		GregorianCalendar calendar = new GregorianCalendar();

		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String[] times = new String[3];
		String stringMinutes = Time.addZero(minutes);
		String stringSeconds = Time.addZero(seconds);


		times[0] = Integer.toString(hours);
		times[1] = stringMinutes;
		times[2] = stringSeconds;

		return times;
	}
	
}