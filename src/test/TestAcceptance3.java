//package test;
//
//import static org.junit.Assert.*;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import main.Enduro;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import race.Varvrace;
//
//public class TestAcceptance3{
//	private static String testStartPath = "src/test/testfiles/starttider.test";
//	private static String testFinishPath = "src/test/testfiles/maltider.test";
//	private static String testNullPath = "src/test/testfiles/null.test";
//	private static String testResultPath = "src/test/testfiles/result.test";
//	private static String testResultatPath = "src/test/testfiles/resultat.test";
//
//	
//	
//	public TestAcceptance3() {
//		//super(testStartPath, testFinishPath, testNullPath,testResultPath,0,3,"");
//		
//		 
//	}
//
//	@Before public void setup() {
//		String[] args = new String [8];
//		args[0]= testStartPath;
//		args[1]= testFinishPath;
//		args[2]= testNullPath;
//		args[3]= testResultPath;
//		args[4]= "varv";
//		args[5]= "enkelstart";
//		args[6]= "10.40";
//		args[7]= "3";
//		new Enduro(args);
//	}
//	
//	@Test public void testThatResultatEqualsResultat(){
//		try {
//			File file1 = new File(testResultatPath);
//			File file2 = new File(testResultPath);
//			Scanner scan1 = new Scanner(file1);
//			Scanner scan2 = new Scanner(file2);
//			
//			while (scan1.hasNext() || scan2.hasNext()){
//				assertEquals(scan1.nextLine(),
//						scan2.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//}