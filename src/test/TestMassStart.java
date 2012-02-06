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

public class TestMassStart extends Sorter {

	public TestMassStart() {
		super("TestMassStart.test", "TestMassFinish.test", "TestMassName.test", "", 0, 0, "Masstart");
	}

	@Test
	public void enkelMasstartMarathon() throws FileNotFoundException {
		try{
		writeResultFile("ResultMassStart.txt");
		Scanner sc1 = new Scanner(new File("TestResultMassStart.test"));
		Scanner sc = new Scanner(new File("ResultMassStart.txt"));
		sc.nextLine();
		sc1.nextLine();
			assertEquals(sc1.nextLine(), sc.nextLine());
			assertEquals(sc1.nextLine(), sc.nextLine());
		sc.close();
		}catch(Exception e){
		}
		
	}

}
