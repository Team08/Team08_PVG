package test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Enduro;

import org.junit.Before;
import org.junit.Test;


public class TestAcceptance13{
	private static String testStartPath = "src/test/testfiles/accep13_starttimes.test";
	private static String testFinishPath = "src/test/testfiles/accep13_finishtimes.test";
	private static String testNamefilePath = "src/test/testfiles/accep13_namefile.test";
	private static String testResultPath = "src/test/testfiles/accep13_actualRes.txt";
	private static String testExpResultPath = "src/test/testfiles/accep13_expResult.test";

	
	
	public TestAcceptance13() {
		//super(testStartPath, testFinishPath, testNullPath,testResultPath,0,3,"");
		
		 
	}

	@Before public void setup() {
		String[] args = new String [8];
		args[0]= testStartPath;
		args[1]= testFinishPath;
		args[2]= testNamefilePath;
		args[3]= testResultPath;
		args[4]= "varv";
		args[5]= "enkelstart";
		args[6]= "0.15";
		args[7]= "3";
System.out.println(args[0]);
System.out.println(args[1]);
System.out.println(args[2]);
System.out.println(args[3]);
System.out.println(args[4]);
System.out.println(args[5]);
System.out.println(args[6]);
System.out.println(args[7]);


		
		new Enduro(args);
	}
	
	@Test public void testResult(){
		try {
			System.out.println("ASD");
			File file1 = new File(testExpResultPath);
			File file2 = new File(testResultPath);
			Scanner scan1 = new Scanner(file1);
			Scanner scan2 = new Scanner(file2);
			String scantext1;
			String scantext2;
			while (scan1.hasNext() || scan2.hasNext()){
				
				scantext1 = scan1.nextLine();
				scantext2 = scan2.nextLine();
				System.out.println(scantext1 + " = " + scantext2);
				assertEquals("scan1 =! scan2 ", scan1.nextLine(),
						scan2.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}