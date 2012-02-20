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
	
	//Varf�r g�r de inte igenom??? Kolla upp
	@Test
	public void testLesser() {
		Time time2 = new Time("17.00.00");
		Time time = new Time("14.00.00");
		assertEquals(true, time.lesserThan(time2));
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
	
	@Test
	public void testMultiplyTimeByPosNbrNotZero() {
		Time t1 = new Time("01.05.04");
		assertEquals(new Time("3.15.12").toString(), t1.multiplyTimeBy(3, t1).toString());
	}
	
	@Test
	public void testMultiplyTimeByZero() {
		Time t1 = new Time("01.05.04");
		assertEquals(new Time("0.00.00").toString(), t1.multiplyTimeBy(0, t1).toString());
	}
	
	@Test
	public void testMultiplyTimeByOne() {
		Time t1 = new Time("01.05.04");
		assertEquals(new Time("01.05.04").toString(), t1.multiplyTimeBy(1, t1).toString());
	}
	
	@Test(expected=NumberFormatException.class)
	public void testMultiplyTimeByNegNbrNotZero() {
		Time t1 = new Time("01.05.04");
		assertEquals(new Time("3.15.12").toString(), t1.multiplyTimeBy(-3, t1).toString());
	}
	
	@Test
	public void testMultiplyTimeByPosNbrNotZeroPass60() {
		Time t1 = new Time("00.55.00");
		assertEquals(new Time("02.45.00").toString(), t1.multiplyTimeBy(3, t1).toString());
	}
	
	@Test
	public void testAddToTimeAddItself() {
		Time t1 = new Time("01.05.04");
		assertEquals(new Time("2.10.08").toString(), t1.addToTime(t1).toString());
	}
	
	@Test
	public void testAddToTimeNotZero() {
		Time t1 = new Time("01.05.04");
		Time t2 = new Time("02.40.30");
		assertEquals(new Time("3.45.34").toString(), t1.addToTime(t2).toString());
	}
	
	@Test
	public void testAddToTimePass60NotZero() {
		Time t1 = new Time("01.05.04");
		Time t2 = new Time("02.57.30");
		assertEquals(new Time("4.02.34").toString(), t1.addToTime(t2).toString());
	}
	
	@Test(expected=NumberFormatException.class)
	public void testAddToTimeNegTimeZero() {
		Time t1 = new Time("01.05.04");
		Time t2 = new Time("-02.57.30");
		assertEquals(new Time("4.02.34").toString(), t1.addToTime(t2).toString());
	}
	
	@Test
	public void testAddToTimeZero() {
		Time t1 = new Time("01.05.04");
		Time t2 = new Time("00.00.00");
		assertEquals(new Time("1.05.04").toString(), t1.addToTime(t2).toString());
	}
	
	//Add test code to test ParseTime och addZero. Kan dessa skrivas om?
	
}
