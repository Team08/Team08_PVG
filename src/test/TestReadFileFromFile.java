package test;


import java.io.FileNotFoundException;

import race.LapRace;
import reader.ReadFinishFile;
import reader.ReadStartFile;
import util.Time;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadFileFromFile {
	private ReadStartFile rsf;
	private ReadFinishFile rff;
	
	public TestReadFileFromFile() {
<<<<<<< HEAD
		 rsf = new ReadStartFile(new Varvrace("Unknown", "Unknown", "Unknown","","",0,"", null), "Unknown");
		 rff = new ReadFinishFile(new Varvrace("Unknown", "Unknown", "Unknown","","",0,"", null), "Unknown");
=======
		 rsf = new ReadStartFile(new LapRace("Unknown", "Unknown", "Unknown","","",0,""), "Unknown");
		 rff = new ReadFinishFile(new LapRace("Unknown", "Unknown", "Unknown","","",0,""), "Unknown");
>>>>>>> e4ca9a8d567f44a195bf2749f3246f0b725af636
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected = FileNotFoundException.class)
	public void TestReadStartFileThrowsNoSuchFileException()
			throws FileNotFoundException {
		try {
			rsf.readFile();
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	@Test(expected = FileNotFoundException.class)
	public void TestReadFinishFileThrowsNoSuchFileException()
			throws FileNotFoundException {
		try {
			rff.readFile();
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

}