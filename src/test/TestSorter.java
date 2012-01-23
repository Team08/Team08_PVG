package test;


import static org.junit.Assert.*;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSorter extends Sorter {

	public TestSorter() {
		super("", "");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddToTreeMap() {
		Integer i = new Integer(1);
		String time = "01.01.01";
		super.addStartTime(i, time);
		assertEquals(register.size(), 1);
	}

}
