package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import race.LapRace;
import reader.ReadFinishFile;
import reader.ReadStartFile;

import org.junit.Test;

public class TestReadFile extends LapRace {

	private static String testStartPath = "src/test/testfiles/TestStart.test";
	private static String testFinishPath = "src/test/testfiles/TestFinish.test";
	

	private ReadStartFile startfFile;
	private ReadFinishFile finishFile;

	public TestReadFile() {
		super("TestStart.test", "TestFinish.test", "", "","", "", 0, "", null, null);
		startfFile = new ReadStartFile(new LapRace(testStartPath,
				testFinishPath, "", "","", "", 0, "", null, null), testStartPath);
		finishFile = new ReadFinishFile(new LapRace(testStartPath,
				testFinishPath, "", "", "","", 0, "", null, null), testFinishPath);
	}

	@Test
	public void TestReadStartFile() {
		try {

			startfFile.readFile();
			File file = new File(testStartPath);
			Set<Integer> driverSet = index.keySet();
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
					assertEquals(str[1], index.get(j).startTime().get(0));
				}

			} catch (FileNotFoundException e) {
			}
		} catch (FileNotFoundException e) {
		}
	}

	@Test
	public void TestReadFinishFileOneFinishTime() {
		try {
			finishFile.readFile();

			File file = new File(testFinishPath);
			Set<Integer> driverSet = index.keySet();
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

					assertEquals(str[1], index.get(j).finishTime().get(0));
				}
			} catch (FileNotFoundException e) {
			}

		} catch (FileNotFoundException e) {
		}
	}

	@Test
	public void TestReadFinishFileMultipleFinishTimes() {
		try {
			finishFile.readFile();

			File file = new File(testFinishPath);
			Set<Integer> driverSet = index.keySet();
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
					for (int x = 0; x < index.get(j).finishTime().size(); x++) {
						assertEquals(str[x + 1], index.get(j).finishTime().get(
								x));
					}
				}
			} catch (FileNotFoundException e) {
			}

		} catch (FileNotFoundException e) {
		}
	}
	
	
	
	

}