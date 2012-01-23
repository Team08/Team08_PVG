package main;

public class Driver {
	private String name;
	private String startTime;
	
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
}
