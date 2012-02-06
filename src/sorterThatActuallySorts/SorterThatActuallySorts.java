package sorterThatActuallySorts;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import main.Driver;
import main.Time;

public class SorterThatActuallySorts {
	
	public String sortbyLaps (HashMap<String, TreeMap<Integer, Driver>> a){
		StringBuilder sb = new StringBuilder();
		HashMap<String, HashMap<Driver, Integer>> map = new HashMap<String, HashMap<Driver, Integer>>();
		HashMap<Driver, Integer> temp = new HashMap<Driver, Integer>();
		for(String className : a.keySet()){
			sb.append(className);
			TreeMap<Integer, Driver> tm = a.get(className);
			for(Integer i : tm.keySet()){
				for(Driver d : temp.keySet()){
					if(tm.get(i).getNumberOfLaps() < (d.getNumberOfLaps())){
						temp.put(tm.get(i), i);
					}else if(tm.get(i).getNumberOfLaps() == (d.getNumberOfLaps())){
					}
				}
			}
			
				
				
		}
		
		return null;
	}
	

	
	
	
	
	
	
	
	
	
	
}
