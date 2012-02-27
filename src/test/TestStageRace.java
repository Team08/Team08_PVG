package test;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import main.Driver;

import org.junit.Before;
import org.junit.Test;

import race.LapRace;
import race.StageRace;
import result.StageResult;
import util.Time;

public class TestStageRace {
	//expectedresult är inte rätt formaterade! Ändra detta och radera kommentar!
	private static String testResultPath = "src/test/testfiles/ExpectedResultOfStageTest.test";
	private static String testActualResultPath = "src/test/testfiles/ResultOfStageTest.txt";
	private static String testStartPath = "src/test/testfiles/TestStart.test, src/test/testfiles/TestStart2.test";
	private static String testFinishPath = "src/test/testfiles/TestFinish.test, src/test/testfiles/TestFinish2.test";
	private static String testNamefilePath = "src/test/testfiles/null.test";
	private StageRace sr;

	@Before
	public void setup() {
		sr = new StageRace(testStartPath, testFinishPath, testNamefilePath, testActualResultPath, 2, "etapp", null, "0" , 0);
	}

	@Test
	public void testPrintStageRace() {
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
//public class TestStageRace {
//	//expectedresult är inte rätt formaterade! Ändra detta och radera kommentar!
//	private static String testResultPath = "src/test/testfiles/ExpectedResultOfStageTest.test";
//	private static String testActualResultPath = "src/test/testfiles/ResultOfStageTest.txt";
//	private static String testStartPath = "src/test/testfiles/TestStart.test, src/test/testfiles/TestStart2.test";
//	private static String testFinishPath = "src/test/testfiles/TestFinish.test, src/test/testfiles/TestFinish2.test";
//	private static String testNamefilePath = "src/test/testfiles/null.test";
//	private StageRace sr;
//
//	@Before
//	public void setup() {
//		sr = new StageRace(testStartPath, testFinishPath, testNamefilePath, testActualResultPath, 2, "etapp", null, "0" , 0);
//	}
//
//	@Test
//	public void testPrintStageRace() {
//		sr.computeTotalTime();
	}
//		sr.getResult(sr.index);
//		try {
//			File file1 = new File(testResultPath);
//			File file2 = new File(testActualResultPath);
//			Scanner scan1 = new Scanner(file1);
//			Scanner scan2 = new Scanner(file2);
//			while(scan2.hasNextLine()){
//				System.out.println(scan2.nextLine());
//				assertEquals(scan1.nextLine(),scan2.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			System.err.println("Fel i TestStageRace.");
//			e.printStackTrace();
//			
//		}
//	}
	
//	}
//	
//	
}
