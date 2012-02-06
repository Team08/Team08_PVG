package test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import main.Driver;
import main.Varvrace;
import reader.ReadNameFile;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadNameFile extends Varvrace {
	private ReadNameFile namefile;
	private TreeMap<Integer, Driver> driverTreeMap;

	private Varvrace varvrace;
	
	public TestReadNameFile() {
		super("", "", "namnfil.test","maraton",0,0);
	}

	
	@Before
	public void setUp() throws Exception {

		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());
		varvrace = new Varvrace("", "", "namnfil.test","maraton",0,0);
		namefile = new ReadNameFile(varvrace , "namnfil.test");

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
		assertEquals("Incorrect number of drivers, should be 5, was " + varvrace.size(), 5, varvrace.size());
	}
	
	

	
	@Test
	public void testCorrectClass() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect class should be SENIOR, was" + varvrace.getDriver(2).classes().get(0), "SENIOR", varvrace.getDriver(2).classes().get(0));
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(103).classes().get(0), "JUNIOR", varvrace.getDriver(103).classes().get(0));
	}
	
	@Test
	public void testCorrectName() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect name of driver, should be Bengt Bsson, was" + varvrace.size(), "Bengt Bsson", varvrace.getDriver(2).getName());
		assertEquals("Incorrect name of driver, should be Erik Esson, was" + varvrace.size(), "Erik Esson", varvrace.getDriver(103).getName());
	}
	

}