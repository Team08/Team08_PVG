package test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		super("", "", "src/namnfil.test",1,0,0);
	}

	
	@Before
	public void setUp() throws Exception {

		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());
		sorter = new Sorter("", "", "src/namnfil.test",1,0,0);
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
	
	

	
	@Test
	public void testCorrectClass() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect class should be SENIOR, was" + sorter.getDriver(2).classes().get(0), "SENIOR", sorter.getDriver(2).classes().get(0));
		assertEquals("Incorrect class should be JUNIOR, was" + sorter.getDriver(103).classes().get(0), "JUNIOR", sorter.getDriver(103).classes().get(0));
	}
	
	@Test
	public void testCorrectName() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect name of driver, should be Bengt Bsson, was" + sorter.size(), "Bengt Bsson", sorter.getDriver(2).getName());
		assertEquals("Incorrect name of driver, should be Erik Esson, was" + sorter.size(), "Erik Esson", sorter.getDriver(103).getName());
	}
	

}