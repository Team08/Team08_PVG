package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Driver;
import result.LapResult;
import util.Time;
import util.Time2;
import model.Register;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLapResult {

	private LapResult lr;
	
	@Before public void setup(){
		lr = new LapResult(null, 0, "00.00", null);
	}
	
	
	@Test
	public void testCheckIfManyFinishTimeOneTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time(36000));
		lr.checkIfManyFinishTime(list, sb);
		assertEquals(0, sb.length());
	}
	
	@Test
	public void testCheckIfManyFinishTimeTwoTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("01.00.00"));
		list.add(new Time("02.00.00"));
		lr.checkIfManyFinishTime(list, sb);
		assertEquals("; Flera måltider? 2.00.00", sb.toString());
	}
	
	@Test
	public void testCheckIfManyFinishTimeThreeTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("01.00.00"));
		list.add(new Time("02.00.00"));
		list.add(new Time("03.00.00"));
		lr.checkIfManyFinishTime(list, sb);
		assertEquals("; Flera måltider? 2.00.00 3.00.00", sb.toString());
	}
	
	@Test
	public void testCheckIfManyStartTimeOneTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time(36000));
		lr.checkIfManyStartTime(list, sb);
		assertEquals(0, sb.length());
	}
	
	@Test
	public void testCheckIfManyStartTimeTwoTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("01.00.00"));
		list.add(new Time("02.00.00"));
		lr.checkIfManyStartTime(list, sb);
		assertEquals("; Flera starttider? 2.00.00", sb.toString());
	}
	
	@Test
	public void testCheckIfManyStartTimeThreeTimes(){
		StringBuilder sb = new StringBuilder();
		List<Time> list = new ArrayList<Time>();
		list.add(new Time("01.00.00"));
		list.add(new Time("02.00.00"));
		list.add(new Time("03.00.00"));
		lr.checkIfManyStartTime(list, sb);
		assertEquals("; Flera starttider? 2.00.00 3.00.00", sb.toString());
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
	
	@Test
	public void testCheckTotaltimeNoStartTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		finishList.add(new Time(3600));
		lr.checkTotaltime(startList, finishList, sb, "");
		assertEquals("--.--.--; ", sb.toString());
	}
	
	@Test
	public void testCheckTotaltimeNoFinishTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		startList.add(new Time(3600));
		lr.checkTotaltime(startList, finishList, sb, "");
		assertEquals("--.--.--; ", sb.toString());
	}
	
	@Test
	public void testCheckTotaltimeBothTimesGreaterTotalTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		startList.add(new Time("2.30.00"));
		startList.add(new Time("15.00.00"));
		finishList.add(new Time("16.00.00"));
		finishList.add(new Time("14.00.00"));
		
		String res = lr.checkTotaltime(startList, finishList, sb, "print1");
		assertEquals("11.30.00; ", sb.toString());
		assertEquals("print1", res);
	}
	
	@Test
	public void testCheckTotaltimeBothTimesLessTotalTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		startList.add(new Time("2.30.58"));
		startList.add(new Time("15.00.00"));
		finishList.add(new Time("16.00.00"));
		finishList.add(new Time("2.42.36"));
		
		String res = lr.checkTotaltime(startList, finishList, sb, "print1");
		assertEquals("0.11.38; ", sb.toString());
		assertEquals("; Omöjlig Totaltid?", res);
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
		
	
	
	
}
