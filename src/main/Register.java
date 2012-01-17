package main;

public class Register {
	private Driver driver;

	public Register(Driver driver){
		this.driver = driver;
	}
	
	public boolean startRace(){
		return true;
	}
	
	public double stopRace(){
		return 1;
	}
}
