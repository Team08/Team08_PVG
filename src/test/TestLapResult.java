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
		lr = new LapResult(null, 0, "0.00", "",null, null);
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
	public void testCheckTotaltimeNoStartTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		finishList.add(new Time(3600));
		lr.checkTotaltime(startList, finishList, sb);
		assertEquals("--.--.--; ", sb.toString());
	}
	
	@Test
	public void testCheckTotaltimeNoFinishTime(){
		StringBuilder sb = new StringBuilder();
		List<Time> startList = new ArrayList<Time>();
		List<Time> finishList = new ArrayList<Time>();
		startList.add(new Time(3600));
		lr.checkTotaltime(startList, finishList, sb);
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
		
		String res = lr.checkTotaltime(startList, finishList, sb);
		assertEquals("11.30.00; ", sb.toString());
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
		
		String res = lr.checkTotaltime(startList, finishList, sb);
		assertEquals("0.11.38; ", sb.toString());
		assertEquals("; Omöjlig Totaltid?", res);
	}
	
	
		
	
}
