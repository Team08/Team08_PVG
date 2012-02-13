package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {
	public Sorter(){
		
	}
	
	public ArrayList<Driver> lapSort(ArrayList<Driver> old){
		ArrayList<Driver> temp = old;
		Collections.sort(temp, new lapSortComparator());
		
		return temp;
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
