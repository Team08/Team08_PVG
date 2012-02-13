package util;

/**
 * A time class in milliseconds. It can also compare times and write the time in
 * string.
 */
public class Time {
	private int time;

	/**
	 * Creates the Time in milliseconds.
	 * 
	 * @param time
	 *            the time in milliseconds
	 */
	public Time(int time) {
		this.time = time;
	}

	/**
	 * Creates the Time from a string.
	 * 
	 * @param time
	 *            the time in string
	 */
	public Time(String representTime) {
		String[] temp = representTime.split("\\.");
		time = parseTime(temp);
	}

	// BORDE EJ BEHÖVAS MEN FINNS FÖR ATT DE ÄR SÅ LÄTT ATT IMPLEMENTERA
	/**
	 * Sets the time in milliseconds.
	 * 
	 * @param time
	 *            the time in milliseconds
	 */
	public void setTime(int time) {
		this.time = time;
	}

	// BORDE EJ BEHÖVAS MEN FINNS FÖR ATT DE ÄR SÅ LÄTT ATT IMPLEMENTERA
	/**
	 * Sets the time (string).
	 * 
	 * @param time
	 *            the time in string
	 */
	public void setTime(String time) {
		String[] temp = time.split("\\.");
		this.time = parseTime(temp);
	}

	/**
	 * Returns the time.
	 * 
	 * @return The time in milliseconds
	 */
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
	/**
	 * Return a string representation of minutes.
	 * 
	 * @param minutes
	 *            the minutes
	 * @throws NumberFormatException
	 *             If minutes < 0
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
	 * Determine if the times are the same.
	 * 
	 * @param t2
	 *            the time
	 * @return <CODE>true</CODE> if both time is the same:
	 *         <CODE>time - t2.getTime() == 0 </CODE>, <CODE>false</CODE>
	 *         otherwise
	 */
	public boolean equals(Time t2) {
		if (time - t2.getTime() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Determine if time is greater than the parameter time.
	 * 
	 * @param t2
	 *            the time
	 * @return <CODE>true</CODE> if this.time is greater than t2:
	 *         <CODE>time > t2.getTime() </CODE>, <CODE>false</CODE> otherwise
	 */
	public boolean greaterThan(Time t2) {
		if (!(time > t2.getTime())) {
			return false;
		}
		return true;
	}

	/**
	 * Determine if time is lesser than the parameter time.
	 * 
	 * @param t2
	 *            the time
	 * @return <CODE>true</CODE> if this.time is greater than t2:
	 *         <CODE>time < t2.getTime() </CODE>, <CODE>false</CODE> otherwise
	 */
	public boolean lesserThan(Time time2) {
		if (!(time < time2.getTime())) {
			return false;
		}
		return true;
	}

	/**
	 * Convert the time to a string and return the string.
	 * 
	 * @return The time in string
	 */
	public String toString() {
		int minutes = (time % 3600) / 60;
		String min = addZero(minutes);

		int seconds = (time % 3600) % 60;
		String sec = addZero(seconds);

		String total = time / 3600 + "." + min + "." + sec;

		return total;
	}

	/**
	 * Computes the difference between time.
	 * 
	 * @param t2
	 *            the time
	 * @return The difference between the time
	 */
	public int timeDiff(Time t2) {
		if (time > t2.getTime()) {
			return time - t2.getTime();
		}else{
			return t2.getTime() - time;
		}
	}
}