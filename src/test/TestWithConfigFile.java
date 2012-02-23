package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.junit.*;

import race.LapRace;
import reader.ReadFinishFile;



public class TestWithConfigFile {
	private LapRace race;
	private String start = "defaultStart";
	private String stop = "defaultStop";
	private String name = "defaultName";
	private String result = "defaultResult";
	private String raceTime = "";
	private String raceType = "";
	private int distance = 0;
	private String startType = "";
	private String[] attributeArray;
	private String attributeString = "";
	private static String testConfigPath = "src/test/testfiles/configTest.properties";
	private static String testStartPath = "src/test/testfiles/TestStart.test";
	private static String testFinishPath = "src/test/testfiles/TestFinish.test";
	
	private ReadFinishFile rff;
	Properties configFile = new Properties();
	
	
	@Before public void setup(){
		race = new LapRace(start,
				stop, "", result,"", "", 0, "", null);
		try {
			configFile.load(new FileInputStream(testConfigPath));
			start = configFile.getProperty("STARTFILE");
			stop = configFile.getProperty("STOPFILE");
			name = configFile.getProperty("NAMEFILE");
			result = configFile.getProperty("RESULTFILE");
			raceTime = configFile.getProperty("RACETIME");
			raceType = configFile.getProperty("RACETYPE");	
			startType = configFile.getProperty("STARTTYPE");				
			distance = Integer.parseInt(configFile.getProperty("DISTANCE"));				
			attributeString = configFile.getProperty("DRIVER_ATTRIBUTES");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testReadOneFinishFile(){
		rff = new ReadFinishFile(race, testFinishPath);
	
		
		try {
			rff.readFile();

			File file = new File(testFinishPath);
			Set<Integer> driverSet = race.index.keySet();
			Scanner scan;
			String[] str;
			try {
				String line;
				scan = new Scanner(file);
				Iterator<Integer> itr = driverSet.iterator();
				for (int i = 0; i < driverSet.size(); i++) {
					line = scan.nextLine();
					str = line.split("; ");
					Integer j = itr.next();
					Integer startNumber = Integer.parseInt(str[0]);
					assertEquals(startNumber.toString(), j.toString());
			
					assertEquals(str[1], race.index.get(j).finishTime().get(0).toString());
				}
			} catch (FileNotFoundException e) {
			}

		} catch (FileNotFoundException e) {
		}
	}
	
	@Test
	public void testReadMultipleFinishFilesWithNoEmptyTimes(){
		rff = new ReadFinishFile(race, stop);
		
		
		try {
			rff.readFile();

			File file = new File("src/test/testfiles/TestFinish2.test");
			Set<Integer> driverSet = race.index.keySet();
			Scanner scan;
			String[] str;
			try {
				String line;
				scan = new Scanner(file);
				Iterator<Integer> itr = driverSet.iterator();
				for (int i = 0; i < driverSet.size(); i++) {
					line = scan.nextLine();
					str = line.split("; ");
					Integer j = itr.next();
					Integer startNumber = Integer.parseInt(str[0]);
					assertEquals(startNumber.toString(), j.toString());
					assertNotNull("hej",str[1]);
					assertEquals(str[1], race.index.get(j).finishTime().get(1).toString());
					
				}
			} catch (FileNotFoundException e) {
				System.out.println("HELLOW");
			}

		} catch (FileNotFoundException e) {
			System.out.println("HELLOW, YOU TOO");
		}
	}

}
