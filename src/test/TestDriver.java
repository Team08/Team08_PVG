package test;


import static org.junit.Assert.*;
import main.Driver;

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
		assertEquals("01.01.01", driver.startTime());
		
	}
	
	@Test
	public void testAddFinishTime() {
		String time = "00.00.00";
		driver.addFinishTime(time);
		assertEquals("00.00.00", driver.finishTime());
		
	}
	
	@Test
	public void testSetName() {
		String name = "Test";
		driver.setName(name);
		assertEquals("Test", driver.getName());
	}

}
