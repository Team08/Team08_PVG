package main;

import java.util.List;

public interface Result {

	public  String checkError(int i, List<String> startTime,
			List<String> finishTime);

	public abstract void checkIfManyFinishTime(List<String> finishTime,
			StringBuilder sb);

	public  void checkIfManyStartTime(List<String> startTime,
			StringBuilder sb);

	public  void checkFinishTime(List<String> finishTime,
			StringBuilder sb);

	public void checkStartTime(List<String> startTime, StringBuilder sb);

	public String checkTotaltime(List<String> startTime,
			List<String> finishTime, StringBuilder sb, String totalCheck);

	public void checkName(StringBuilder sb, String name);

}