package test;


import static org.junit.Assert.*;
import main.Driver;
import util.Time;
import util.Time2;

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
		Time time = new Time("01.01.01");
		driver.addStartTime(time);
		assertEquals("1.01.01", driver.startTime().get(0).toString());	
	}
	
	@Test
	public void testAddMultipleStartTime() {
		Time time = new Time("01.01.01");
		driver.addStartTime(time);
		time = new Time("01.01.02");
		driver.addStartTime(time);
		assertEquals("1.01.02", driver.startTime().get(1).toString());		
	}
	
	@Test
	public void testAddFinishTime() {
		Time time = new Time("00.00.00");
		driver.addFinishTime(time);
		assertEquals("0.00.00", driver.finishTime().get(0).toString());	
	}
	
	@Test
	public void testAddMultipleFinishTime() {
		Time time = new Time("01.01.01");
		driver.addFinishTime(time);
		time = new Time("01.01.02");
		driver.addFinishTime(time);
		assertEquals("1.01.02", driver.finishTime().get(1).toString());	
	}
	
	@Test
	public void testSetName() {
		String name = "Test";
		driver.setName(name);
		assertEquals("Test", driver.getName());
	}


	@Test
	public void testTotalTime(){
		Time startTime = new Time("00.00.00");
		driver.addStartTime(startTime);
		Time finishTime = new Time("01.02.03");
		driver.addFinishTime(finishTime);
		Time temp = new Time( driver.startTime().get(0).timeDiff(driver.finishTime().get(0)));
		assertEquals("1.02.03", temp.toString());
	}
	
	@Test
	public void testTotalTimeWhenTwoStartTimes(){
		Time startTime = new Time("00.00.00");;
		driver.addStartTime(startTime);
		startTime = new Time("00.00.10");
		driver.addStartTime(startTime);
		Time finishTime = new Time("01.02.03");
		driver.addFinishTime(finishTime);
		Time temp = new Time( driver.startTime().get(0).timeDiff(driver.finishTime().get(0)));
		assertEquals("1.02.03", temp.toString());
	}
	
	@Test
	public void testTotalTimeWhenTwoFinishTimes(){
		Time startTime = new Time("00.00.00");
		driver.addStartTime(startTime);
		Time finishTime = new Time("01.02.03");
		driver.addFinishTime(finishTime);
		finishTime = new Time("01.02.10");
		driver.addFinishTime(finishTime);
		Time temp = new Time( driver.startTime().get(0).timeDiff(driver.finishTime().get(0)));
		assertEquals("1.02.03", temp.toString());
	}
	@Test
	public void testTotalTimeIfMoreMinutesInStartTime(){
		Time startTime = new Time("01.40.01");
		driver.addStartTime(startTime);
		Time finishTime = new Time("02.02.01");
		driver.addFinishTime(finishTime);
		Time temp = new Time( driver.startTime().get(0).timeDiff(driver.finishTime().get(0)));
		assertEquals("0.22.00", temp.toString());
	}
	
	@Test
	public void testAddClassToDriverAndFetch() {
		String specClass = "KLASS";
		driver.addClass(specClass);
		assertEquals(specClass, driver.getClasses());
	}
	
}