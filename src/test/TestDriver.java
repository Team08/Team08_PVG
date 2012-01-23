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
	public void testAddStartTimeToList() {
		String time = "01.01.01";
		driver.addStartTime(time);
		assertEquals(driver.startTime(), "01.01.01");
		
	}

}
