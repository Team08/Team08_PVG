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

	public void addStartTime(String time) {
		startTime = time;
		
	}

	public String startTime() {
		return startTime;
	}

	public String finishTime() {
		return finishTime;
	}

	public void addFinishTime(String time) {
		finishTime = time;
		
	}
}
