package test;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.TreeMap;

import main.Driver;
import main.ReadNameFile;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadNameFile extends Sorter {
	private ReadNameFile rnf;
	private TreeMap<Integer, Driver> tm;
	private Sorter sorter;
	
	public TestReadNameFile() {
		super("", "", "src/namnfil.test");
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Before
	public void setUp() throws Exception {
		tm = new TreeMap<Integer, Driver>();
		tm.put(new Integer(1), new Driver());
		tm.put(new Integer(4), new Driver());
		tm.put(new Integer(9), new Driver());
		sorter = new Sorter("", "", "src/namnfil.test");
		rnf = new ReadNameFile(sorter, "src/namnfil.test");
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testCorrectNumberOfDrivers() {
		try {
			rnf.read();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		assertEquals("Incorrect number of drivers", 5, sorter.size());
	}
	


}