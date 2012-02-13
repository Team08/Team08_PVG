package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {
	private ArrayList<Driver> driverList;
	
	public Sorter(){
		
	}
	
	public ArrayList<Driver> lapSort(ArrayList<Driver> old){
		driverList = old;
		Collections.sort(driverList, new lapSortComparator());
	
		return driverList;
	}


	
	
	
	private class lapSortComparator implements Comparator<Driver>{

		public int compare(Driver o1, Driver o2) {
			if(o1.getNumberOfLaps() < o2.getNumberOfLaps()){
				return 1;
			}
			else if(o1.getNumberOfLaps() > o2.getNumberOfLaps()){
				return -1;
			} else{
				if(o1.totalTime() > o2.totalTime()){
					return 1;
				} else if(o1.totalTime() < o2.totalTime()){
					return -1;
				}else{
					return 0;
				}
			}
		}
	}
}
