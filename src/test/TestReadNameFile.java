package test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import main.Driver;
import race.Varvrace;
import reader.ReadNameFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class TestReadNameFile extends Varvrace {
	private ReadNameFile namefile;
	private TreeMap<Integer, Driver> driverTreeMap;
	private static String testNamePath = "src/test/testfiles/namnfil.test";


	private Varvrace varvrace;
	
	public TestReadNameFile() {
		super("", "", testNamePath,"","",0,"");
	}

	
	@Before
	public void setUp() throws Exception {

		driverTreeMap = new TreeMap<Integer, Driver>();
		driverTreeMap.put(new Integer(1), new Driver());
		driverTreeMap.put(new Integer(4), new Driver());
		driverTreeMap.put(new Integer(9), new Driver());
		varvrace = new Varvrace("", "", testNamePath,"","",0,"");
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
				
		assertEquals("Incorrect class should be SENIOR, was" + varvrace.getDriver(1).getClasses(), "SENIOR", varvrace.getDriver(1).getClasses());
		assertEquals("Incorrect class should be SENIOR, was" + varvrace.getDriver(2).getClasses(), "SENIOR", varvrace.getDriver(2).getClasses());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(101).getClasses(), "JUNIOR", varvrace.getDriver(101).getClasses());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(102).getClasses(), "JUNIOR", varvrace.getDriver(102).getClasses());
		assertEquals("Incorrect class should be JUNIOR, was" + varvrace.getDriver(103).getClasses(), "JUNIOR", varvrace.getDriver(103).getClasses());
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