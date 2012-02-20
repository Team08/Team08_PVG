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


	private LapRace varvrace;
	
	public TestReadNameFile() {
		super("", "", testNamePath,"","",0,"", null);
	}

	
	@Before
	public void setUp() throws Exception {

		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());

		varvrace = new LapRace("", "", testNamePath,"","",0,"", null);
		namefile = new ReadNameFile(varvrace, testNamePath);
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
				
		assertEquals("Incorrect class should be SENIOR, was" + varvrace.getDriver(1).getRaceClass(), "SENIOR", varvrace.getDriver(1).getRaceClass());
		assertEquals("Incorrect class should be SENIOR, was" + varvrace.getDriver(2).getRaceClass(), "SENIOR", varvrace.getDriver(2).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(101).getRaceClass(), "JUNIOR", varvrace.getDriver(101).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(102).getRaceClass(), "JUNIOR", varvrace.getDriver(102).getRaceClass());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(103).getRaceClass(), "JUNIOR", varvrace.getDriver(103).getRaceClass());
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
	
	@Test
	public void testCorrectAttributes() {
		try {
				namefile.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Vargarna", varvrace.getDriver(2).getAttributes().get(0));
		assertEquals("Harley", varvrace.getDriver(103).getAttributes().get(0));
	}
	

}