package main;

public class Driver {
	private String name;
	private String startTime;
	private String finishTime;
	
	public Driver() {
		
	}
	
	public Driver(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	/**
	 * Inserts a new start time
	 * The current start time is replaced by the new time
	 * @param time The start time
	 */
	public void addStartTime(String time) {
		startTime = time;
		
	}

	public String startTime() {
		return startTime;
	}

	public String finishTime() {
		return finishTime;
	}

	/**
	 * Inserts a new finish time
	 * The current finish time is replaced by the new time
	 * @param time
	 */
	public void addFinishTime(String time) {
		finishTime = time;
		
	}
}
