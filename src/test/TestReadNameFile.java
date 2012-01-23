package test;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.TreeMap;

import main.Driver;
import main.ReadNameFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadNameFile {
	private ReadNameFile rnf;
	private TreeMap<Integer, Driver> tm;
	
	@Before
	public void setUp() throws Exception {
		tm = new TreeMap<Integer, Driver>();
		tm.put(new Integer(1), new Driver());
		tm.put(new Integer(4), new Driver());
		tm.put(new Integer(9), new Driver());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCorrectNumberOfDrivers() {
		String filename = "src/namnfil.test";
		rnf = new ReadNameFile(filename);
		rnf.readFile(tm);
		assertEquals("Incorrect number of drivers", 6, tm.size());
	}
	
	@Test
	public void testReadNameFile() throws Exception {
		String filename = "src/namnfil.test";
		rnf = new ReadNameFile(filename);
		TreeMap<Integer, Driver> tm = new TreeMap<Integer, Driver>();
		tm.put(new Integer(1), new Driver());
		tm.put(new Integer(4), new Driver());
		tm.put(new Integer(9), new Driver());
		
		rnf.readFile(tm);
		
		Driver d1 = tm.get(1);
		Driver d2 = tm.get(4);
		Driver d3 = tm.get(9);
		
		assertEquals("Driver 1 wrong name", "Anders Asson", d1.getName());
		assertEquals("Driver 4 wrong name", "David Dsson", d2.getName());
		assertNull("Driver 9 has a name", d3.getName());
		
	}

}
