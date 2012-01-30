package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import main.FileIO;
import main.ReadFinishFile;
import main.ReadStartFile;
import main.Sorter;
import org.junit.Test;

public class TestReadFile extends Sorter {

	private ReadStartFile startfFile;
	private ReadFinishFile finishFile;

	public TestReadFile() {
		super("TestStart.test", "TestStart.test", "",1,0,0);
		 startfFile = new ReadStartFile(new Sorter("TestStart.test", "TestStart.test", "",1,0,0), "TestStart.test");
		 finishFile = new ReadFinishFile(new Sorter("TestStart.test", "TestStart.test", "",1,0,0), "TestStart.test");
	}

	@Test
	public void TestReadStartFile() {
		try {

			startfFile.readFile();

			File file = new File("TestStart.test");
			Set<Integer> driverSet = register.keySet();
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
					assertEquals(str[1], register.get(j).startTime().get(0));
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

			File file = new File("TestStart.test");
			Set<Integer> driverSet = register.keySet();
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
					
					assertEquals(str[1], register.get(j).finishTime().get(0));
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

			File file = new File("TestStart.test");
			Set<Integer> driverSet = register.keySet();
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
					for(int x = 0; x < register.get(j).finishTime().size();x++){
						assertEquals(str[x + 1], register.get(j).finishTime().get(x));
					}
				}
			} catch (FileNotFoundException e) {
			}

		} catch (FileNotFoundException e) {
		}
	}

}