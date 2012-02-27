package test;

import static org.junit.Assert.*;
import main.Driver;
import util.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLap {
	private Driver driver;

	@Before
	public void setUp() throws Exception {
		this.driver = new Driver();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOneLapTime() {
		Time time = new Time("12.00.00");
		driver.addStartTime(time);
		time = new Time("12.30.00");
		driver.addFinishTime(time);

		assertEquals("0.30.00", driver.getLapTime(0));
	}

	@Test
	public void testMultipleLapTimes() {
		Time time = new Time("12.00.00");
		driver.addStartTime(time);
		time = new Time("12.29.00");;
		driver.addFinishTime(time);
		time = new Time("13.00.00");
		driver.addFinishTime(time);
		time = new Time("13.00.01");
		driver.addFinishTime(time);

		assertEquals("0.29.00", driver.getLapTime(0));
		assertEquals("0.31.00", driver.getLapTime(1));
		assertEquals("0.00.01", driver.getLapTime(2));
	}

	@Test
	public void testNoLapTime() {
		Time time = new Time("12.00.00");
		driver.addStartTime(time);
		time = new Time("12.29.00");
		driver.addFinishTime(time);

		assertEquals("0.29.00", driver.getLapTime(0));
		assertEquals("", driver.getLapTime(1));

	}

	@Test
	public void testNumberOfLaps() {
		Time time = new Time("12.00.00");
		driver.addStartTime(time);
		time =  new Time("12.29.00");
		driver.addFinishTime(time);
		time =  new Time("13.00.00");
		driver.addFinishTime(time);
		time =  new Time("13.00.01");;
		driver.addFinishTime(time);

		assertEquals(3, driver.getNumberOfLaps());

	}
	
	@Test
	public void testNumberOfLapsWithNoLaps() {
		Time time = new Time("12.00.00");
		driver.addStartTime(time);

		assertEquals(0, driver.getNumberOfLaps());

	}
}
