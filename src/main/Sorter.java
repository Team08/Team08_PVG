package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import util.Time;

public class Sorter {
	private ArrayList<Driver> driverList;
	private Time raceTime;
	
	public Sorter(){
		
	}
	
	public ArrayList<Driver> lapSort(ArrayList<Driver> old, Time raceTime){
		this.raceTime = raceTime;
		driverList = old;
		Collections.sort(driverList, new lapSortComparator());
		return driverList;
	}


	public class lapSortComparator implements Comparator<Driver>{

		public int compare(Driver o1, Driver o2) {
			
			Integer d1T = (new Time(o1.totalTime()).lesserThan(raceTime)) ? -1 : 0;
			Integer d2T = (new Time(o2.totalTime()).lesserThan(raceTime)) ? -1 : 0;
			
			System.out.println(" \n \n " + o1.getId() + " vs " + o2.getId());
			
			int c1 = d1T.compareTo(d2T);
			System.out.println(c1);
			if(c1 != 0) {
				return c1;
			}
			
			Integer d1Laps = new Integer(o1.getNumberOfLaps());
			Integer d2Laps = new Integer(o2.getNumberOfLaps());
			
			System.out.println(d1Laps + " : " + d2Laps);
			
			int c2 = d1Laps.compareTo(d2Laps);
			System.out.println(c2);
			if(c2 != 0) {
				return c2;
			}
			
			return new Time(o1.totalTime()).compareTo(o2.totalTime());
			
			
			
//			int o1TotTime = o1.totalTime();
//			int o2TotTime = o2.totalTime();
//			
//			if (o1TotTime > 0 && o2TotTime > 0) {
//				if (new Time(o1TotTime).greaterOrEqualTo(raceTime)
//						&& new Time(o2TotTime).greaterOrEqualTo(raceTime)) {
//					if (o1.getNumberOfLaps() < o2.getNumberOfLaps()) {
//						return 1;
//					} else if (o1.getNumberOfLaps() > o2.getNumberOfLaps()) {
//						return -1;
//					} else {
//						if (o1.totalTime() > o2.totalTime()) {
//							return 1;
//						} else if (o1.totalTime() < o2.totalTime()) {
//							return -1;
//						} else {
//							return 0;
//						}
//					}
//				} else if (new Time(o1TotTime).greaterOrEqualTo(raceTime)
//						&& new Time(o2TotTime).lesserThan(raceTime)) {
//					return 1;
//				} else if (new Time(o1TotTime).lesserThan(raceTime)
//						&& new Time(o2TotTime).greaterOrEqualTo(raceTime)) {
//					return -1;
//				}
//				if (o1.totalTime() > o2.totalTime()) {
//					return 1;
//				} else if (o1.totalTime() < o2.totalTime()) {
//					return -1;
//				} else {
//					return 0;
//				}
//			} else {
//				int c = (new Integer(o1TotTime)).compareTo(new Integer(
//						o2TotTime));
//				System.out.println("\n" + o1.getId() + " vs " + o2.getId()
//						+ " : " + c + "\n ");
//				return c;
//				}
//			
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public class raceTimeSortComparator implements Comparator<Driver>{
//
//		public int compare(Driver o1, Driver o2) {
//			if(new Time(o1.totalTime()).greaterOrEqualTo(raceTime)  && new Time(o2.totalTime()).greaterOrEqualTo(raceTime)){				
//				return 1;
//			}
//			if(new Time(o1.totalTime()).lesserThan(raceTime)  && new Time(o2.totalTime()).lesserThan(raceTime)){
//				return -1;
//			}
//			if(new Time(o1.totalTime()).greaterOrEqualTo(raceTime) && new Time(o2.totalTime()).lesserThan(raceTime)){
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
