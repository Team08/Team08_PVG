package test;


import java.io.FileNotFoundException;

import main.ReadFinishFile;
import main.ReadStartFile;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReadFileFromFile {
	private ReadStartFile rsf;
	private ReadFinishFile rff;
	
	public TestReadFileFromFile() {
		 rsf = new ReadStartFile(new Sorter("Unknown", "Unknown", "Unknown",1,0,0), "Unknown");
		 rff = new ReadFinishFile(new Sorter("Unknown", "Unknown", "Unknown",1,0,0), "Unknown");
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