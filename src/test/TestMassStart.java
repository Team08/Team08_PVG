package test;

import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.io.FileNotFoundException;
import java.sql.Driver;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import race.LapRace;
import race.Race;
import reader.ReadStartFile;
import util.Time;

public class TestMassStart extends LapRace {
	private main.Driver d;
	private ReadStartFile fileio;
	private Race race;
	private static String testMassStartPath = "src/test/testfiles/MassStart.test";

	public TestMassStart() {
		super("TestStart.test", "", "", "", "", "", 0, "", null, null);
	}

	@Before
	public void setUp() {
		race = new LapRace("", "", "", "", "", "", 0, "", null, null);
		fileio = new ReadStartFile(race, testMassStartPath);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testcheckTreeMap() {
		try {
			fileio.readFileMassStart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TreeMap<Integer,main.Driver> list2 = race.getIndex();
		Time time = new Time("13.31.01");
		ArrayList<Time> list = new ArrayList<Time>();
		assertEquals("",10,list2.size());
		for(int i = 1; i <= 10; i++){
			d = list2.get(i);
			int id = d.getId();
			list = d.startTime();
			assertEquals("kollar att listan innehåller ett element",1,list.size());
			assertEquals("kollar att tiden är lika med; 13.31.01",true, list.get(0).equals(time));
		}
	}
}
