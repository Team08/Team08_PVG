package test;

import static org.junit.Assert.*;

import main.Driver;

import org.junit.*;

import race.LapRace;
import util.Time;

public class TestRace {
	private LapRace race;

	@Before
	public void setUp() {

		race = new LapRace("", "","", "", "", "", 0, "", null, null);

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testAddToTreeMap() {
		Integer i = new Integer(1);
		Time time = new Time("01.01.01");
		race.addStartTime(i, time);
		assertEquals(1, race.index.size());
	}

	@Test
	public void testAddStartTime() {
		Integer i = new Integer(1);
		Time time = new Time("01.01.01");
		race.addStartTime(i, time);
		Driver driver = race.index.get(i);
		assertEquals(new Time("1.01.01").toString(), driver.startTime().get(0)
				.toString());
	}
	


	@Test
	public void testAddFinishTime() {
		Integer i = new Integer(1);
		Time time = new Time("02.02.00");
		race.addFinishTime(i, time);
		Driver driver = race.index.get(i);
		assertEquals("2.02.00", driver.finishTime().get(0).toString());

	}

	@Test
	public void testAddFirstStartThenFinishTimes() {
		Integer i = new Integer(1);
		Time startTime = new Time("01.01.01");
		Time finishTime = new Time("02.02.02");
		race.addStartTime(i, startTime);
		race.addFinishTime(i, finishTime);
		Driver driver = race.index.get(i);
		assertEquals("Wrong start time", "1.01.01", driver.startTime().get(0)
				.toString());
		assertEquals("Wrong finish time", "2.02.02", driver.finishTime().get(0)
				.toString());
	}
	
	@Test
	public void testAddName() {
		Integer i = new Integer(1);
		String name = "Anders Asson";
		race.addName(i, name);
		Driver driver = race.index.get(i);
		assertEquals(name, driver.getName());
	}
	
	@Test
	public void testAddTwoName() {
		Integer i = new Integer(1);
		String name = "Anders Asson";
		Integer z = new Integer(2);
		String nameSecond = "Bengt Baron";
		race.addName(i, name);
		race.addName(z, nameSecond);
		Driver driver = race.index.get(i);
		Driver driverSecond = race.index.get(z);
		assertEquals(name, driver.getName());
		assertEquals(nameSecond, driverSecond.getName());
	}
	@Test
	public void testAddClass() {
		Integer i = new Integer(1);
		String name = "SENIOR";
		race.addClass(i, name);
		Driver driver = race.index.get(i);
		assertEquals(name, driver.getRaceClass());
	}
	
	@Test
	public void testGetDriverThatExist() {
		Driver driver = new Driver("Unique Name123");
		race.index.put(new Integer(47), driver);
		Driver driverRes = race.getDriver(new Integer(47));
		assertEquals("Unique Name123", driverRes.getName());		
	}
	
	@Test
	public void testGetDriverThatDoesntExist() {
		Driver driver = new Driver("Unique Name1234");
		race.index.put(new Integer(47), driver);
		Driver driverRes = race.getDriver(new Integer(40));
		assertFalse(driver == driverRes);
	}
	
	
	
	
	
}
