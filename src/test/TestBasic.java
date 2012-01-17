package test;

import static org.junit.Assert.*;
import main.Driver;
import main.Race;
import org.junit.*;

public class TestBasic {
private Race race;

	@Before public void setup(){
		this.race = new Race(new Driver("Team08"));
	}
	
	@Test public void  testThatRaceTimeIsGreaterThanZero(){
		race.startRace();
		assertTrue("Time was not greater than zero", race.stopRace()>0);
	}
}
