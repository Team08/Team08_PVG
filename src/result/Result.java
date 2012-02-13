package result;

import java.util.List;
import java.util.TreeMap;

import main.Driver;

import util.Time;

/**
 * An abstract class. Uses the template method-architecture. Appropriate sub
 * classes will be called based on what kind of Result should be printed, e.g.
 * Lapresult. The sub classes writes the files, and checks for errors.
 * 
 * This class almost only contains the methods which all sub classes should
 * contain.
 */
public abstract class Result {
	protected TreeMap<Integer, Driver> index;

	/**
	 * Check if any error and returns a String.
	 * 
	 * 
	 * @param i
	 *            the index
	 * @param startTime
	 *            the list of start times
	 * @param finishTime
	 *            the list of finish times
	 * @return The string line with results and errors if any was found
	 */
	public abstract String checkError(int i, List<Time> startTime,
			List<Time> finishTime);


	/**
	 * Check if the finish time list contains any finish time.
	 * 
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */

	public void checkFinishTime(List<Time> finishTime, StringBuilder sb) {
		if (finishTime.size() == 0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
	}
	


	/**
	 * Check if the start time list contains any finish time.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param sb
	 *            the StringBuilder to append to
	 */

	public void checkStartTime(List<Time> startTime, StringBuilder sb) {
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
	}
	

	/**
	 * Check if the finish time list contains any finish time.
	 * 
	 * @param startTime
	 *            the list of start times to check
	 * @param finishTime
	 *            the list of finish times to check
	 * @param sb
	 *            the StringBuilder to append to
	 * @return A string of the total time with
	 *         error-notations if any invalid time was found
	 */
	public abstract String checkTotaltime(List<Time> startTime,
			List<Time> finishTime, StringBuilder sb);

	/**
	 * Check if the name is null and appends the name to sb.
	 * 
	 * @param name
	 *            the name to check
	 * @param sb
	 *            the StringBuilder to append to
	 */
	public void checkName(StringBuilder sb, String name) {
		if (name == null) {
			sb.append("Namn?; ");
		} else {
			sb.append(name + "; ");
		}
	}

}