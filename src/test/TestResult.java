package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import main.ReadNameFile;
import main.Sorter;
import org.junit.Test;

public class TestResult extends Sorter {

	public TestResult() {
		super("TestStart.test", "TestFinish.test", "src/namnfil.test");
	}
	
	//incomplete
	@Test
	public void TestwriteResultFile() throws FileNotFoundException{
		readStartFile();
		readFinishFile();
		writeResultFile("Result.txt");
	}
}
