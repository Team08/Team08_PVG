package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import main.Sorter;
import org.junit.Test;

public class TestReadFile extends Sorter {

	public TestReadFile() {
		super("TestStart.test", "TestStart.test", "");
	}

	@Test
	public void TestReadStartFile() {
		try {
			super.readStartFile();
			Set<Integer> driverSet = register.keySet();
			Scanner scan;
			String line;
			File file = new File("TestStart.test");
			Iterator<Integer> itr = driverSet.iterator();
			try {
				scan = new Scanner(file);
				for (int i = 0; i < driverSet.size(); i++) {
					Integer j = itr.next();
					line = scan.nextLine();
					String[] str = line.split("; ");
					Integer startNumber = Integer.parseInt(str[0]);
					assertEquals(startNumber.toString(), j.toString());
					assertEquals(str[1], register.get(j).startTime().get(0));

				}

			} catch (FileNotFoundException e) {
				//
			}

		} catch (FileNotFoundException e) {
		}
	}

	@Test
	public void TestReadFinishFile() {
		try {
			super.readFinishFile();
			Set<Integer> driverSet = register.keySet();
			Scanner scan;
			File file = new File("TestStart.test");
			try {
				String line;
				scan = new Scanner(file);
				Iterator<Integer> itr = driverSet.iterator();
				for (int i = 0; i < register.size(); i++) {
					line = scan.nextLine();
					String[] str = line.split("; ");
					Integer startNumber = Integer.parseInt(str[0]);
					Integer j = itr.next();
					assertEquals(startNumber.toString(), j.toString());
					assertEquals(str[1], register.get(j).finishTime().get(0));

				}
			} catch (FileNotFoundException e) {
				//
			}

		} catch (FileNotFoundException e) {
		}
	}

}
