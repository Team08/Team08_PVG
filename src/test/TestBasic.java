package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import main.Driver;
import main.Register;
import main.Sorter;

import org.junit.Before;
import org.junit.Test;

public class TestBasic {
private Register register;
private Driver driver;
private Sorter sorter;

	@Before public void setup(){
		this.driver = new Driver("Team08");
		this.register = new Register(driver);
		this.sorter = new Sorter("Start.txt","Stop.txt");
	}
	
	@Test public void  testThatRaceTimeIsGreaterThanZero(){
		register.startRace();
		assertTrue("Time was not greater than zero", register.stopRace().getTime()>0);
	}
	
	@Test public void testDriversName(){
		Driver driver = new Driver("Joe");
		assertEquals("Joe",driver.getName());
	}
	
	@Test public void testThatStartFileCreates(){
		register.startRace();
		File f = new File("Start.txt");
		assertTrue("The Startfile was not found", f.exists()); 
	}
	
	@Test public void testThatStopFileCreates(){
		register.stopRace();
		File f = new File("Stop.txt");
		assertTrue("The Stopfile was not found", f.exists()); 
	}
	
	@Test public void testThatResultFileCreates(){
		 
		Sorter.main(new String[0]);
		File f = new File("Result.txt");
		assertTrue("The Resultfile was not found", f.exists()); 
	}
	
}
