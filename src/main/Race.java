package main;

public class Race {
	private Driver driver;

	public Race(Driver driver){
		this.driver = driver;
	}
	
	public boolean startRace(){
		return true;
	}
	
	public double stopRace(){
		return 1;
	}
}
