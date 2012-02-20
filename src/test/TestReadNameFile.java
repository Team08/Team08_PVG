package test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import main.Driver;
import race.LapRace;
import reader.ReadNameFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class TestReadNameFile extends LapRace {
	private ReadNameFile namefile;
	private TreeMap<Integer, Driver> driverTreeMap;
	private static String testNamePath = "src/test/testfiles/namnfil.test";


	private LapRace lapRace;
	
	public TestReadNameFile() {
		super("", "", testNamePath,"","",0,"", null);
	}

	
	@Before
	public void setUp() throws Exception {

		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());

		lapRace = new LapRace("", "", testNamePath,"","",0,"", null);
		namefile = new ReadNameFile(lapRace, testNamePath);
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
		assertEquals("Incorrect number of drivers, should be 5, was " + lapRace.size(), 5, lapRace.size());
	}
	
	

	
	@Test
	public void testCorrectClass() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		assertEquals("Incorrect class should be SENIOR, was" + lapRace.getDriver(1).getRaceClass(), "SENIOR", lapRace.getDriver(1).getRaceClass());
		assertEquals("Incorrect class should be SENIOR, was" + lapRace.getDriver(2).getRaceClass(), "SENIOR", lapRace.getDriver(2).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + lapRace.getDriver(101).getRaceClass(), "JUNIOR", lapRace.getDriver(101).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + lapRace.getDriver(102).getRaceClass(), "JUNIOR", lapRace.getDriver(102).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + lapRace.getDriver(103).getRaceClass(), "JUNIOR", lapRace.getDriver(103).getRaceClass());
	}
	
	@Test
	public void testCorrectName() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Incorrect name of driver, should be Bengt Bsson, was" + lapRace.size(), "Bengt Bsson", lapRace.getDriver(2).getName());
		assertEquals("Incorrect name of driver, should be Erik Esson, was" + lapRace.size(), "Erik Esson", lapRace.getDriver(103).getName());
	}
	
	@Test
	public void testCorrectAttributes() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Vargarna", lapRace.getDriver(2).getAttributes().get(0));
		assertEquals("Harley", lapRace.getDriver(103).getAttributes().get(0));
	}
	

}