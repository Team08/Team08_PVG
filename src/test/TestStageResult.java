package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import result.StageResult;
import util.Time;

public class TestStageResult {

private StageResult lr;
	
	@Before public void setup(){
		lr = new StageResult(null, 0, null);
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
	
	
}
