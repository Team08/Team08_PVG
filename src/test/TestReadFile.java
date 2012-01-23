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
		super("TestStart.test", "TestStart.test");

	}
	@Test
	public void TestReadStartFile() throws FileNotFoundException {
		super.readStartFile();
		Set<Integer> driverSet = register.keySet();
		Scanner scan;
		File file = new File("TestStart.test");
		for (int i = 0; i < register.size(); i++) {
			try {
				scan = new Scanner(file);
				scan.useDelimiter(";");
				Iterator<Integer> itr = driverSet.iterator();
				Integer j = itr.next();
				assertEquals(scan.next().trim(), j.toString());
				assertEquals(scan.next().trim(), register.get(j).startTime());
			} catch (FileNotFoundException e) {// Catch exception if any
				throw new FileNotFoundException();

			}
		}
	}
	@Test
	public void TestReadFinishFile() throws FileNotFoundException {
		super.readFinishFile();
		Set<Integer> driverSet = register.keySet();
		Scanner scan;
		File file = new File("TestStart.test");
		for (int i = 0; i < register.size(); i++) {
			try {
				scan = new Scanner(file);
				scan.useDelimiter(";");
				Iterator<Integer> itr = driverSet.iterator();
				Integer j = itr.next();
				assertEquals(scan.next().trim(), j.toString());
				assertEquals(scan.next().trim(), register.get(j).finishTime());
			} catch (FileNotFoundException e) {// Catch exception if any
				throw new FileNotFoundException();

			}
		}
	}
}
		
	
