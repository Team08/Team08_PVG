package test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.TreeMap;

import main.Driver;
import main.ReadNameFile;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadNameFile extends Sorter {
	private ReadNameFile namefile;
	private TreeMap<Integer, Driver> driverTreeMap;
	private Sorter sorter;
	
	public TestReadNameFile() {
		super("", "", "src/namnfil.test");
	}

	
	@Before
	public void setUp() throws Exception {
		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());
		sorter = new Sorter("", "", "src/namnfil.test");
		namefile = new ReadNameFile(sorter, "src/namnfil.test");
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testCorrectNumberOfDrivers() {
		try {
			namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect number of drivers, should be 5, was" + sorter.size(), 5, sorter.size());
	}
	


}