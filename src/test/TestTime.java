package test;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Driver;
import main.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTime {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddZeroToStringWhenNeeded() {
		assertEquals("09", Time.addZero(9));
	}
	
	@Test
	public void testAddZeroToStringWhenNotNeeded() {
		assertEquals("11", Time.addZero(11));
	}
	
	@Test
	public void testAddZeroWhenLastDigitIsZero() {
		assertEquals("10", Time.addZero(10));
	}
	
	@Test(expected=NumberFormatException.class)
	public void testAddZeroWhenDigitIsNegative() {
		Time.addZero(-1);
	}
	
	@Test
	public void testAddZeroWhenDigitIsZero() {
		assertEquals("00", Time.addZero(0));
	}
	
	@Test
	public void testTimeDiffWhenDiffIsZero() {
		assertEquals("0.00.00", Time.timeDiff("00.00.00", "00.00.00"));
	}
	
	@Test
	public void testTimeDiffWhenTwoDifferentNumbers() {
		assertEquals("0.45.00", Time.timeDiff("13.15.00", "14.00.00"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenStartIsLargerThanFinish() {
		Time.timeDiff("00.16.00", "00.15.00");
	}
	
	@Test
	public void timeDiffShouldBeZero() {
		assertEquals("0.00.00", Time.timeDiff("00.15.00", "00.15.00"));
	}
	
	@Test (expected = NumberFormatException.class) 
	public void testTimeDiffWhenStartIsNegative() {
		Time.timeDiff("-1.15.15", "1.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenFinishIsNegative() {
		Time.timeDiff("00.00.00", "0.-15.00");
	}
	
	@Test (expected = NumberFormatException.class) 
	public void testTimeDiffWhenBothNegative() {
		Time.timeDiff("-1.00.00", "-1.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenStartLargerThanFinishAndFinishIsNegative() {
		Time.timeDiff("12.15.00", "-10.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenInputIsLetters() {
		Time.timeDiff("a.b.c", "a.b.c");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenWrongInputFormat() {
		Time.timeDiff("36.00", "00.00.00");
		
	}
	
	@Test
	public void testGetCurrentTime() {
		String[] currTime = Time.makeTimeList();
		GregorianCalendar calendar = new GregorianCalendar();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String[] currTime2 = {Integer.toString(hours), Time.addZero(minutes), Time.addZero(seconds)};
		
		assertArrayEquals(currTime, currTime2);
		
		
	}
	
}