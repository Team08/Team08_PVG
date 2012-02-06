package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.TreeMap;

import main.Driver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sorterThatActuallySorts.SorterThatActuallySorts;


public class TestSorterThatActuallySorts {
	HashMap<String, TreeMap<Integer, Driver>> herp;
	TreeMap<Integer, Driver> derp;
	
	@Before
	public void setUp() throws Exception {
		this.herp = new HashMap<String, TreeMap<Integer, Driver>>();
		this.derp = new TreeMap<Integer, Driver>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSimpleMap() {
		Driver d1 = new Driver("a");
		d1.addStartTime("12.00.00");
		d1.addFinishTime("13.00.00");
		Driver d2 = new Driver("b");
		d2.addStartTime("11.00.00");
		d2.addFinishTime("11.30.00");
		
		
		derp.put(2, d1);
		derp.put(1, d2);
		
		herp.put("asd", derp);
		System.out.println(herp);
		System.out.println(SorterThatActuallySorts.sortbyLaps(herp));
	}
}
