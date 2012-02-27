package test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Enduro;

import org.junit.Before;
import org.junit.Test;


public class TestAcceptanceVarvloppFullt{
	private static String testStartPath = "src/test/testfiles/accepVF_starttimes.test";
	private static String testFinishPath = "src/test/testfiles/accepVF_finishtimes.test";
	private static String testNamefilePath = "src/test/testfiles/accepVF_namefile.test";
	private static String testResultPath = "src/test/testfiles/accepVF_actualRes.txt";
	private static String testExpResultPath = "src/test/testfiles/accepVF_expResult.test";
	private static String testExpSortPath = "src/test/testfiles/accepVF_expSort.test";
	private static String testSortPath = "src/test/testfiles/accepVF_actualSort.txt";
	private static String testConfig = "src/test/testfiles/accepVF_config.properties";
	
	public TestAcceptanceVarvloppFullt() {
		//super(testStartPath, testFinishPath, testNullPath,testResultPath,0,3,"");
		
		 
	}

	@Before public void setup() {
		String[] args = new String [1];

		args[0]= testConfig;


		
		new Enduro(args);
	}
	
	@Test public void testResult(){
		Boolean temp = false;
		try {
			File file1 = new File(testExpResultPath);
			File file2 = new File(testResultPath);
			Scanner scan1 = new Scanner(file1);
			Scanner scan2 = new Scanner(file2);
			String scantext1 = " ";
			String scantext2;
			while (scan1.hasNextLine() || scan2.hasNextLine()){
				scantext1 = scan1.nextLine();
				scantext2 = scan2.nextLine();
				assertEquals("scan1 =! scan2 ", scan1.nextLine(),
						scan2.nextLine());
				temp = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertTrue("kommer inte in i filerna i testResult", temp);
	}
	
	
}