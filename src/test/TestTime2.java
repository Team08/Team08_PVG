package test;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import main.Driver;
import util.Time2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTime2 {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddZeroToStringWhenNeeded() {
		assertEquals("09", Time2.addZero(9));
	}
	
	@Test
	public void testAddZeroToStringWhenNotNeeded() {
		assertEquals("11", Time2.addZero(11));
	}
	
	@Test
	public void testAddZeroWhenLastDigitIsZero() {
		assertEquals("10", Time2.addZero(10));
	}
	
	@Test(expected=NumberFormatException.class)
	public void testAddZeroWhenDigitIsNegative() {
		Time2.addZero(-1);
	}
	
	@Test
	public void testAddZeroWhenDigitIsZero() {
		assertEquals("00", Time2.addZero(0));
	}
	
	@Test
	public void testTimeDiffWhenDiffIsZero() {
		assertEquals("0.00.00", Time2.timeDiff("00.00.00", "00.00.00"));
	}
	
	@Test
	public void testTimeDiffWhenTwoDifferentNumbers() {
		assertEquals("0.45.00", Time2.timeDiff("13.15.00", "14.00.00"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenStartIsLargerThanFinish() {
		Time2.timeDiff("00.16.00", "00.15.00");
	}
	
	@Test
	public void timeDiffShouldBeZero() {
		assertEquals("0.00.00", Time2.timeDiff("00.15.00", "00.15.00"));
	}
	
	@Test (expected = NumberFormatException.class) 
	public void testTimeDiffWhenStartIsNegative() {
		Time2.timeDiff("-1.15.15", "1.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenFinishIsNegative() {
		Time2.timeDiff("00.00.00", "0.-15.00");
	}
	
	@Test (expected = NumberFormatException.class) 
	public void testTimeDiffWhenBothNegative() {
		Time2.timeDiff("-1.00.00", "-1.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenStartLargerThanFinishAndFinishIsNegative() {
		Time2.timeDiff("12.15.00", "-10.00.00");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenInputIsLetters() {
		Time2.timeDiff("a.b.c", "a.b.c");
	}
	
	@Test (expected = NumberFormatException.class)
	public void testTimeDiffWhenWrongInputFormat() {
		Time2.timeDiff("36.00", "00.00.00");
		
	}
	
	@Test
	public void testGetCurrentTime() {
		String[] currTime = Time2.makeTimeList();
		GregorianCalendar calendar = new GregorianCalendar();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		String[] currTime2 = {Integer.toString(hours), Time2.addZero(minutes), Time2.addZero(seconds)};
		
		assertArrayEquals(currTime, currTime2);
		
		
	}
	
}