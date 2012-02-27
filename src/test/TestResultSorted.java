package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.Test;

import race.LapRace;
import race.Race;

public class TestResultSorted {
	
	public static void main(String[] args) {
		new TestResultSorted();
	}
		protected TreeMap<Integer, Driver> register;
		private Race race;
		private String start = "src/test/testfiles/TestResultSortedFiles/ResultSorted_starttime.test";
		private String stop = "src/test/testfiles/TestResultSortedFiles/ResultSorted_finishtime.test";
		private String name = "src/test/testfiles/TestResultSortedFiles/ResultSorted_namefile.test";
		private String result = "src/test/testfiles/TestResultSortedFiles/ResultSorted_nonsortedResult.txt";
		private String sorted = "src/test/testfiles/TestResultSortedFiles/ResultSorted_sortedResult.txt";
		private String raceTime = "";
		private String raceType = "varv";
		private int laps = 0;
		private String startType = "enkelstart";
		private ArrayList<String> driverAttributes = new ArrayList<String>();

		private void createRace(){			
			if (raceType.equals("varv")) {
			race = new LapRace(start, stop, name, result, sorted, raceTime, laps, startType, driverAttributes);
			}
			race.computeTotalTime();
			System.out.println("TestResultSorted har genererat: " + result);
			if(!sorted.equals("")){
			System.out.println("TestResultSorted har genererat: " + sorted);
			}
		}
		
		String ExpResult = "src/test/testfiles/TestResultSortedFiles/ResultSorted_expResult.test";
		@Test
		public void testExpResultWithsortedResult(){
			// Initiala värden;
			this.laps = 3;
			this.raceTime ="00.10";
			createRace();
			try{

			// Ladda den sorterade filen
			FileReader fr = new FileReader(new File(sorted));
			BufferedReader br = new BufferedReader(fr);
			String line = "Actual";

			// Ladda den förväntade filen
			FileReader fr2 = new FileReader(new File(ExpResult));
			BufferedReader br2 = new BufferedReader(fr2);
			String line2 = "Exp";
			
			while( (line = br.readLine()) != null && (line2 = br2.readLine()) != null) {
				System.out.println(line);
				System.out.println(line2);
				assertTrue("sortedfilens innehåll stämmer inte med expResult" , line.equals(line2));
			}
			
			}catch (Exception e){
			e.printStackTrace();
			}
		}
		
		// Jämför "sortedResult.txt med expResult2
		// Skillnaden mellan result1 och 2 är att raceTime ="10.00" (hh.mm)
		String ExpResult2 = "src/test/testfiles/TestResultSortedFiles/ResultSorted_expResult2.test";
		@Test
		public void testExpResultWithsortedResult2(){
			this.laps = 3;
			this.raceTime ="10.00";
			createRace();
			try{

			// Ladda den sorterade filen
			FileReader fr = new FileReader(new File(sorted));
			BufferedReader br = new BufferedReader(fr);
			String line = "Actual";

			// Ladda den förväntade filen
			FileReader fr2 = new FileReader(new File(ExpResult2));
			BufferedReader br2 = new BufferedReader(fr2);
			String line2 = "Exp";
			
			while( (line = br.readLine()) != null && (line2 = br2.readLine()) != null) {
				System.out.println(line);
				System.out.println(line2);
				assertTrue("sortedfilens innehåll stämmer inte med expResult2" , line.equals(line2));
			}
			
			}catch (Exception e){
			e.printStackTrace();
			}
		}
		
		
}

	


