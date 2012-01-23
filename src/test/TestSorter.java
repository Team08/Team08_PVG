package test;


import static org.junit.Assert.*;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
		assertEquals(register.size(), 1);
	}
	@Test
	public void testFirstRow() throws Exception{
		writeResultFile();
		Scanner sc = new Scanner("Result.txt");
		
		assertEquals("StartNr; Totaltid; Starttid; Måltid", sc.nextLine());
		sc.close();
	}

}
