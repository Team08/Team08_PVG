package test;


import static org.junit.Assert.*;
import main.Driver;
import main.ReadFinishFile;
import main.ReadStartFile;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




	

public class TestSorterLaps extends Sorter{
	public TestSorterLaps() {
		super(null, null, null,"varv",3,3, 0);

	}

	@Before
	public void setUp() {

	}
	@Test
	public void testHeadLineOneLap(){
		addStartTime(3, "0.00.01");
		addFinishTime(3, "1.00.00");
		Scanner sc;
		writeResultFile("Result.txt");
		try {
			sc = new Scanner(new File("Result.txt"));
			assertEquals("StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål", sc.nextLine());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testHeadLineThreeLaps(){
		addStartTime(3, "0.00.01");
		addFinishTime(3, "1.00.00");
		addFinishTime(3, "1.22.00");
		Scanner sc;
		try {
			sc = new Scanner(new File("Result.txt"));
			assertEquals("StartNr; Namn; #Varv; Totaltid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mål", sc.nextLine());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testResultOneLap(){
		addStartTime(3, "0.00.01");
		addFinishTime(3, "1.00.00");
		try {
			Scanner sc = new Scanner(new File("Result.txt"));
			sc.nextLine();
			assertEquals("3; Namn?; 1; 0.59.59; 0.59.59; ; ; 0.00.01; ; ; Slut?", sc.nextLine());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testResultThreeLaps(){
		addStartTime(3, "0.00.01");
		addFinishTime(3, "1.00.00");
		addFinishTime(3, "1.22.00");
		writeResultFile("Result.txt");
		try {
			Scanner sc = new Scanner(new File("Result.txt"));
			sc.nextLine();
			assertEquals("3; Namn?; 2; 1.21.59; 0.59.59; 0.22.00; ; 0.00.01; 1.00.00; ; Slut?", sc.nextLine());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
