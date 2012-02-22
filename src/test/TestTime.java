package test;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Time;

public class TestTime {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void creatingTimeObjectInt() {
		Time time = new Time(1000);
		assertEquals(1000, time.getTime());
	}
	
	@Test
	public void creatingTimeObjectString() {
		Time time = new Time("05.00.00");
		assertEquals(18000, time.getTime());
	}
	
//	@Test
//	public void creatingFalseTimeObjectString() {
//		Time time = new Time("07.00.00");
//		//assert
//	}
	
	@Test
	public void settingTimeObjectInt() {
		Time time = new Time(1000);
		time.setTime(5000);
		assertEquals(5000, time.getTime());
	}
	
	@Test
	public void settingTimeObjectString() {
		Time time = new Time("05.00.00");
		time.setTime("10.00.00");
		assertEquals(36000, time.getTime());
	}
	
	@Test
	public void toStringTest() {
		Time time = new Time("05.00.00");
		time.setTime(36000);
		assertEquals("10.00.00", time.toString());
	}
	
	@Test
	public void testEquals() {
		Time time = new Time("08.00.00");
		Time time2 = new Time("08.00.00");
		assertEquals(true, time.equals(time2));
	}
	
	@Test
	public void testFalseEquals() {
		Time time = new Time("08.00.00");
		Time time2 = new Time("03.00.00");
		assertEquals(false, time.equals(time2));
	}
	
	@Test
	public void testGreater() {
		Time time = new Time("14.00.00");
		Time time2 = new Time("12.00.00");
		assertEquals(true, time.greaterThan(time2));
	}
	
	@Test
	public void testFalseGreater() {
		Time time = new Time("08.00.00");
		Time time2 = new Time("18.00.00");
		assertEquals(false, time.greaterThan(time2));
	}
	
	@Test
	public void testGreaterMoreDigits() {
		Time time = new Time("14.48.36");
		Time time2 = new Time("12.50.40");
		assertEquals(true, time.greaterThan(time2));
	}
	
	@Test
	public void testFalseGreaterMoreDigits() {
		Time time = new Time("02.12.13");
		Time time2 = new Time("03.13.14");
		assertEquals(false, time.greaterThan(time2));
	}
	
	
	@Test
	public void testFalseLesser() {
		Time time2 = new Time("18.00.00");
		Time time = new Time("08.00.00");
		assertEquals(false, time.greaterThan(time2));
	}
	
	@Test
	public void testLesserMoreDigits() {
		Time time2 = new Time("12.50.40");
		Time time = new Time("14.48.36");
		assertEquals(true, time.greaterThan(time2));
	}
	
	@Test
	public void testFalseLesserMoreDigits() {
		Time time2 = new Time("03.13.14");
		Time time = new Time("02.12.13");
		assertEquals(false, time.greaterThan(time2));
	}
	
	
	
	
	//Add test code to test ParseTime och addZero. Kan dessa skrivas om?
	
}
