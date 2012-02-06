package test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import main.Sorter;
import org.junit.Before;
import org.junit.Test;

public class TestAcceptance3 extends Sorter {
	public TestAcceptance3() {
		super("src/acceptanstest3/starttider.test", "src/acceptanstest3/maltider.test", "src/acceptanstest3/null.test","varv",0,3, "Enkelstart");
	}

	@Before public void setup() {
		writeResultFile("src/acceptanstest3/result.test");
	}
	
	@Test public void testThatResultatEqualsResultat(){
		try {
			File file1 = new File("src/acceptanstest3/resultat.test");
			File file2 = new File("src/acceptanstest3/result.test");
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