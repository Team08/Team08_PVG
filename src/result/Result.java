package result;

import java.util.List;
import java.util.TreeMap;

import main.Driver;

import util.Time;

public abstract class Result {
	protected TreeMap<Integer, Driver> index;	
	
	public abstract String checkError(int i, List<Time> startTime,
			List<Time> finishTime);

	public abstract void checkIfManyFinishTime(List<Time> finishTime,
			StringBuilder sb);

	public abstract void checkIfManyStartTime(List<Time> startTime,
			StringBuilder sb);

	public abstract void checkFinishTime(List<Time> finishTime,
			StringBuilder sb);

	public abstract void checkStartTime(List<Time> startTime, StringBuilder sb);

	public abstract String checkTotaltime(List<Time> startTime,
			List<Time> finishTime, StringBuilder sb, String totalCheck);

	public void checkName(StringBuilder sb, String name) {
		if (name == null) {
			sb.append("Namn?; ");
		} else {
			sb.append(name + "; ");
		}
	}

}