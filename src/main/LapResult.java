package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.TreeMap;

import util.Time;

public class LapResult implements Result{
	private TreeMap<Integer, Driver> index;
	int laps;
	int raceTime;
	
	public LapResult(TreeMap<Integer, Driver> index, int laps, int raceTime){
		this.index = index;
		this.laps = laps;
	}
	
	public void writeResultFile(String name){
		try{
			
		FileWriter fstream = new FileWriter(name);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("StartNr; Namn; ");
		
			out.write("#Varv; ");
		
		out.write("Totaltid; ");
		 
			for (int i = 0; i < laps; i++) {
				out.write("Varv" + (i + 1) + "; ");
			
		}
		out.write("Start; ");
		
			for (int i = 0; i < laps - 1; i++) {
				out.write("Varvning" + (i + 1) + "; ");
			}
		
		out.write("Mål\n");
		Driver tDriver;

		for (Integer i : index.keySet()) {
			tDriver = index.get(i);
			out.write(checkError(i, tDriver.startTime(), tDriver
					.finishTime()));
		}
		// Close the output stream
		out.close();

	} catch (Exception e) {
		System.err.println("Error: " + e.getMessage());
		System.exit(1);
	}
}
	@Override
	public String checkError(int i, List<String> startTime,
			List<String> finishTime) {
		
		StringBuilder sb = new StringBuilder();
		String totalTimeCheck = "";
		sb.append(i + "; ");
		checkName(sb, index.get(i).getName());
		
		
		sb.append(index.get(i).getNumberOfLaps() + "; ");
		
		totalTimeCheck = checkTotaltime(startTime, finishTime, sb,
				totalTimeCheck);
		if (laps != 0) {
			if (!(startTime.size() == 0 || finishTime.size() == 0)) {
				for (int j = 0; j < laps; j++) {
					sb.append(index.get(i).getLapTime(j) + "; ");
				}
			}
		}
		checkStartTime(startTime, sb);
		if ( finishTime.size() != 0) {
			int check = finishTime.size() - 1;
			if (check > laps - 1) {
				check = laps - 1;
			}
			for (int e = 0; e < check; e++) {
				sb.append(finishTime.get(e) + "; ");
			}
			for(int b = finishTime.size() - 1; b < laps-1; b++){
				sb.append("; ");
			}

			if(Time.timeDiff(startTime.get(0), finishTime
						.get(finishTime.size() - 1)).compareTo(raceTime + ".00.00")>=0){
				sb.append(finishTime.get(finishTime.size() - 1));
				
			}else{
				sb.append("Slut?");
			}
			
		}
		sb.append(totalTimeCheck);
		sb.append("\n");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see main.Result#checkIfManyFinishTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkIfManyFinishTime(List<String> finishTime, StringBuilder sb) {
		if (finishTime.size() > 1) {
			sb.append("; Flera måltider?");
			for (int j = 1; j <= (finishTime.size() - 1); j++) {
				sb.append(" " + finishTime.get(j));
			}
		}
	}

	/* (non-Javadoc)
	 * @see main.Result#checkIfManyStartTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkIfManyStartTime(List<String> startTime, StringBuilder sb) {
		if (startTime.size() > 1) {
			sb.append("; Flera starttider?");
			for (int j = 1; j <= (startTime.size() - 1); j++) {
				sb.append(" " + startTime.get(j));
			}
		}

	}

	/* (non-Javadoc)
	 * @see main.Result#checkFinishTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkFinishTime(List<String> finishTime, StringBuilder sb) {
		if (finishTime.size() == 0) {
			sb.append("Slut?");
		} else {
			sb.append(finishTime.get(0));
		}
	}

	/* (non-Javadoc)
	 * @see main.Result#checkStartTime(java.util.List, java.lang.StringBuilder)
	 */
	@Override
	public void checkStartTime(List<String> startTime, StringBuilder sb) {
		if (startTime.size() == 0) {
			sb.append("Start?; ");
		} else {
			sb.append(startTime.get(0) + "; ");
		}
	}

	@Override
	public String checkTotaltime(List<String> startTime,
			List<String> finishTime, StringBuilder sb, String totalCheck) {
		String totalTime = "";
		if (startTime.size() == 0 || finishTime.size() == 0) {
			sb.append("--.--.--; ");
		} else {
			
				totalTime = Time.timeDiff(startTime.get(0), finishTime
						.get(finishTime.size() - 1));
			 
			sb.append(totalTime + "; ");
			if (totalTime.compareTo("0.15.00") < 0) {
				totalCheck = "; Omöjlig Totaltid?";
			}
		}
		return totalCheck;
	}

	

	/* (non-Javadoc)
	 * @see main.Result#checkName(java.lang.StringBuilder, java.lang.String)
	 */
	@Override
	public void checkName(StringBuilder sb, String name) {
		if (name == null) {
			sb.append("Namn?; ");
		} else {
			sb.append(name + "; ");
		}
	}

}
