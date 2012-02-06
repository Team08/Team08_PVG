package test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.Enduro;

import org.junit.Before;
import org.junit.Test;

public class TestAcceptance3{
	public TestAcceptance3() {
		//super("src/acceptanstest3/starttider.test", "src/acceptanstest3/maltider.test", "src/acceptanstest3/null.test","varv",0,3);
	}

	@Before public void setup() {
		String[] args = new String [7];
		args[0]= "/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/starttider.test";
		args[1]= "/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/maltider.test";
		args[2]= "/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/null.test";
		args[3]= "/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/result.test";
		args[4]= "varv";
		args[5]= "1";
		args[6]= "3";
		
		new Enduro(args);
	}
	
	@Test public void testThatResultatEqualsResultat(){
		try {
			File file1 = new File("/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/resultat.test");
			File file2 = new File("/h/dk/r/ada10dlu/Desktop/pvg_projekt/PVG/result.test");
			Scanner scan1 = new Scanner(file1);
			Scanner scan2 = new Scanner(file2);
			
			while (scan1.hasNext() || scan2.hasNext()){
				assertEquals(scan1.nextLine(),
						scan2.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}