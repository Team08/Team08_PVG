package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import race.Race;
import race.Varvrace;
import reader.FileIO;
import reader.ReadFinishFile;
import reader.ReadStartFile;
import util.Time;
import main.Driver;
import main.Sorter;

import org.junit.Test;

public class TestReadFile extends Varvrace {
	
	private static String testStartPath = "src/test/testfiles/TestStart.test";
	private static String testFinishPath = "src/test/testfiles/TestFinish.test";

	private ReadStartFile startfFile;
	private ReadFinishFile finishFile;

	public TestReadFile() {
<<<<<<< HEAD
		
		super("TestStart.test", "TestStart.test", "","","",0,"");
		 startfFile = new ReadStartFile(new Varvrace(testStartPath, testFinishPath, "","","",0,""), testStartPath);
		 finishFile = new ReadFinishFile(new Varvrace(testStartPath, testFinishPath, "","","",0,""), testFinishPath);
=======
		super("TestStart.test", "TestStart.test", "","maraton",0,0);
		 startfFile = new ReadStartFile(new Varvrace(testStartPath, testFinishPath, "","maraton",0,0), testStartPath);
		 finishFile = new ReadFinishFile(new Varvrace(testStartPath, testFinishPath, "","maraton",0,0), testFinishPath);
>>>>>>> b1ed29e556415c2a7448afa6076641f3560c122b
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
					for(int x = 0; x < index.get(j).finishTime().size();x++){
						assertEquals(str[x + 1], index.get(j).finishTime().get(x));
					}
				}
			} catch (FileNotFoundException e) {
			}

		} catch (FileNotFoundException e) {
		}
	}


}