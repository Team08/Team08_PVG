package test;


import static org.junit.Assert.*;
import main.Driver;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;
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
		assertEquals(1, register.size());
	}

	@Test
	public void testFirstRow() throws Exception{
		writeResultFile("Result.txt");
		Scanner sc = new Scanner(new File("Result.txt"
				));
		assertEquals("StartNr; Totaltid; Starttid; Måltid", sc.nextLine());
		sc.close();
	}
	
	@Test
	public void testOneDriverResult() throws Exception{
		Integer i = new Integer(1);
		String start = "12.00.00";
		super.addStartTime(i, start);
		String finish = "13.23.34";
		super.addFinishTime(i, finish);
		writeResultFile("Result.txt");
		Scanner sc = new Scanner(new File("Result.txt"));
		sc.nextLine();
		assertEquals("1; --.--.--; 12.00.00; 13.23.34", sc.nextLine());
		sc.close();
	}
	
	@Test
	public void testAddStartTime() {
		Integer i = new Integer(1);
		String time = "01.01.01";
		super.addStartTime(i, time);
		Driver driver = register.get(i);
		assertEquals("01.01.01", driver.startTime());
	}
	
	@Test
	public void testAddFinishTime() {
		Integer i = new Integer(1);
		String time = "02.02.00";
		super.addFinishTime(i, time);
		Driver driver = register.get(i);
		assertEquals("02.02.00", driver.finishTime());
		
		

	}
	
	@Test
	public void testAddFirstStartThenFinishTimes() {
		Integer i = new Integer(1);
		String startTime = "01.01.01";
		String finishTime = "02.02.02";
		super.addStartTime(i, startTime);
		super.addFinishTime(i, finishTime);
		Driver driver = register.get(i);
		assertEquals("Wrong start time", "01.01.01", driver.startTime());
		assertEquals("Wrong finish time", "02.02.02", driver.finishTime());
	}
	
	@Test
	public void testAddFirstFinishThenStartTimes() {
		Integer i = new Integer(1);
		String startTime = "01.01.01";
		String finishTime = "02.02.02";
		super.addFinishTime(i, finishTime);
		super.addStartTime(i, startTime);
		Driver driver = register.get(i);
		assertEquals("Wrong start time", "01.01.01", driver.startTime());
		assertEquals("Wrong finish time", "02.02.02", driver.finishTime());
	}

}
