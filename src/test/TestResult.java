package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import result.LapResult;
import util.Time;

public class TestResult {
	private LapResult lr;
	
	@Before public void setup(){
		lr = new LapResult(null, 0, "0.00","", null, null);
	}
	
	@Test
	public void testCheckNameNoName(){
		StringBuilder sb = new StringBuilder();
		lr.checkName(sb, null);		
		assertEquals("Namn?; ", sb.toString());
	}
	
	@Test
	public void testCheckNameWithName(){
		StringBuilder sb = new StringBuilder();
		lr.checkName(sb, "Bengt Baron");		
		assertEquals("Bengt Baron; ", sb.toString());
	}

	@Test
	public void testCheckFinishTimeNoTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		lr.checkFinishTime(list, sb);
		assertEquals("Slut?", sb.toString());
	}
	
	@Test
	public void testCheckFinishTimeOneTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("14.45.03"));
		lr.checkFinishTime(list, sb);
		assertEquals("14.45.03", sb.toString());
	}
	
	@Test
	public void testCheckFinishTimeTwoTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("14.45.03"));
		list.add(new Time("02.14.54"));
		lr.checkFinishTime(list, sb);
		assertEquals("14.45.03", sb.toString());
	}
	
	@Test
	public void testCheckStartTimeNoTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		lr.checkStartTime(list, sb);
		assertEquals("Start?; ", sb.toString());
	}
	
	@Test
	public void testCheckStartTimeOneTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("14.45.03"));
		lr.checkStartTime(list, sb);
		assertEquals("14.45.03; ", sb.toString());
	}
	
	@Test
	public void testCheckStartTimeTwoTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("14.45.03"));
		list.add(new Time("02.14.54"));
		lr.checkStartTime(list, sb);
		assertEquals("14.45.03; ", sb.toString());
	}
}
