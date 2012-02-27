package test;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.TreeMap;
//
//import main.Driver;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import race.LapRace;
//import race.StageRace;
//import result.StageResult;
//import util.Time;
//
public class TestStageResult {
//	private static String testResultPath = "src/test/testfiles/accep19_actualRes_modified.txt";
//	private static String testActualResultPath = "src/test/testfiles/accep19_actualRes_modified(result).txt";
//	private static String testStartPath = "src/test/testfiles/accep19_starttimes_modified.test";
//	private static String testFinishPath = "src/test/testfiles/accep19_finishtimes_modified.test";
//	private static String testNamefilePath = "src/test/testfiles/accep19_namefile_modified.test";
//	private StageRace sr;
//
//	@Before
//	public void setup() {
//		sr = new StageRace(testStartPath, testFinishPath, testNamefilePath, testActualResultPath, 2,
//				"etapp" , null);
//	}
//
//	@Test
//	public void testFirstline() {
//		sr.computeTotalTime();
//		sr.getResult(sr.index);
//		try {
//			File file1 = new File(testResultPath);
//			File file2 = new File(testActualResultPath);
//			Scanner scan1 = new Scanner(file1);
//			Scanner scan2 = new Scanner(file2);
//			assertEquals(scan1.nextLine(),scan2.nextLine());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testHeadline() {
//		sr.computeTotalTime();
//		sr.getResult(sr.index);
//		try {
//			File file1 = new File(testResultPath);
//			File file2 = new File(testActualResultPath);
//
//			Scanner scan1 = new Scanner(file1);
//			Scanner scan2 = new Scanner(file2);
//			scan1.nextLine();
//			scan2.nextLine();
//			assertEquals(scan1.nextLine(),scan2.nextLine());
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testOneDriver() {
//		sr.computeTotalTime();
//		sr.getResult(sr.index);
//		try {
//			File file1 = new File(testResultPath);
//			File file2 = new File(testActualResultPath);
//
//			Scanner scan1 = new Scanner(file1);
//			Scanner scan2 = new Scanner(file2);
//			scan1.nextLine();
//			scan2.nextLine();
//			scan1.nextLine();
//			scan2.nextLine();
//			assertEquals(scan1.nextLine(),scan2.nextLine());
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testCheckIfManyFinishTimeOneTime() {
//		StringBuilder sb = new StringBuilder();
//		List<Time> list = new ArrayList<Time>();
//		list.add(new Time(36000));
//		lr.checkIfManyFinishTime(list, sb);
//		assertEquals(0, sb.length());
//	}
//
//	@Test
//	public void testCheckIfManyFinishTimeTwoTimes() {
//		StringBuilder sb = new StringBuilder();
//		List<Time> list = new ArrayList<Time>();
//		list.add(new Time("01.00.00"));
//		list.add(new Time("02.00.00"));
//		lr.checkIfManyFinishTime(list, sb);
//		assertEquals("; Flera måltider? 2.00.00", sb.toString());
//	}
//
//	@Test
//	public void testCheckIfManyFinishTimeThreeTimes() {
//		StringBuilder sb = new StringBuilder();
//		List<Time> list = new ArrayList<Time>();
//		list.add(new Time("01.00.00"));
//		list.add(new Time("02.00.00"));
//		list.add(new Time("03.00.00"));
//		lr.checkIfManyFinishTime(list, sb);
//		assertEquals("; Flera måltider? 2.00.00 3.00.00", sb.toString());
//	}
//	
//	

}
