package test;

import static org.junit.Assert.*;
import main.Driver;
import main.Register;
import org.junit.*;

public class TestBasic {
private Register register;

	@Before public void setup(){
		this.register = new Register(new Driver("Team08"));
	}
	
	@Test public void  testThatRaceTimeIsGreaterThanZero(){
		register.startRace();
		assertTrue("Time was not greater than zero", register.stopRace()>0);
	}
}
