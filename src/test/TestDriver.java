package test;


import static org.junit.Assert.*;
import main.Driver;
import util.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDriver {
	private Driver driver;

	@Before
	public void setUp() throws Exception {
		this.driver = new Driver();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddStartTime() {
		String time = "01.01.01";
		driver.addStartTime(time);
		assertEquals("01.01.01", driver.startTime().get(0));	
	}
	
	@Test
	public void testAddMultipleStartTime() {
		String time = "01.01.01";
		driver.addStartTime(time);
		time = "01.01.02";
		driver.addStartTime(time);
		assertEquals("01.01.02", driver.startTime().get(1));		
	}
	
	@Test
	public void testAddFinishTime() {
		String time = "00.00.00";
		driver.addFinishTime(time);
		assertEquals("00.00.00", driver.finishTime().get(0));	
	}
	
	@Test
	public void testAddMultipleFinishTime() {
		String time = "01.01.01";
		driver.addFinishTime(time);
		time = "01.01.02";
		driver.addFinishTime(time);
		assertEquals("01.01.02", driver.finishTime().get(1));	
	}
	
	@Test
	public void testSetName() {
		String name = "Test";
		driver.setName(name);
		assertEquals("Test", driver.getName());
	}


	@Test
	public void testTotalTime(){
		String startTime = "00.00.00";
		driver.addStartTime(startTime);
		String finishTime = "01.02.03";
		driver.addFinishTime(finishTime);
		assertEquals("1.02.03", Time.timeDiff(driver.startTime().get(0), driver.finishTime().get(0)));
	}
	
	@Test
	public void testTotalTimeWhenTwoStartTimes(){
		String startTime = "00.00.00";
		driver.addStartTime(startTime);
		startTime = "00.00.10";
		driver.addStartTime(startTime);
		String finishTime = "01.02.03";
		driver.addFinishTime(finishTime);
		assertEquals("1.02.03", Time.timeDiff(driver.startTime().get(0), driver.finishTime().get(0)));
	}
	
	@Test
	public void testTotalTimeWhenTwoFinishTimes(){
		String startTime = "00.00.00";
		driver.addStartTime(startTime);
		String finishTime = "01.02.03";
		driver.addFinishTime(finishTime);
		finishTime = "01.02.10";
		driver.addFinishTime(finishTime);
		assertEquals("1.02.03", Time.timeDiff(driver.startTime().get(0), driver.finishTime().get(0)));
	}
	@Test
	public void testTotalTimeIfMoreMinutesInStartTime(){
		String startTime = "01.40.01";
		driver.addStartTime(startTime);
		String finishTime = "02.02.01";
		driver.addFinishTime(finishTime);
		Time t = new Time();
		assertEquals("0.22.00", Time.timeDiff(driver.startTime().get(0), driver.finishTime().get(0)));
	}
	
	@Test
	public void testAddClassToDriverAndFetch() {
		String specClass = "KLASS";
		driver.addClass(specClass);
		assertEquals(specClass, driver.classes().get(0));
	}
	
}