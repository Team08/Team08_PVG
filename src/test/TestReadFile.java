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
	private FileIO fileIO;
	private ReadStartFile rsf;
	private ReadFinishFile rff;

	public TestReadFile() {
		super("TestStart.test", "TestStart.test", "");
		 rsf = new ReadStartFile(new Sorter("TestStart.test", "TestStart.test", ""), "TestStart.test");
		 rff = new ReadFinishFile(new Sorter("TestStart.test", "TestStart.test", ""), "TestStart.test");
	}

	@Test
	public void TestReadStartFile() {
		try {
			rsf.read();
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
	public void TestReadFinishFile() {
		try {
			rff.read();
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

}
