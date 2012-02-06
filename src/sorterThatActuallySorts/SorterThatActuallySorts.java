package sorterThatActuallySorts;

import java.util.HashMap;
import java.util.TreeMap;

import main.Driver;

public class SorterThatActuallySorts {

	public static HashMap<String, TreeMap<Driver, Integer>> sortbyLaps (HashMap<String, TreeMap<Integer, Driver>> a){
		StringBuilder sb = new StringBuilder();
		HashMap<String, TreeMap<Driver, Integer>> map = new HashMap<String, TreeMap<Driver, Integer>>();
		
		for(String className : a.keySet()){
			TreeMap<Driver, Integer> temp = new TreeMap<Driver, Integer>();
			TreeMap<Integer, Driver> tm = a.get(className);
			for(Integer i : tm.keySet()){
				temp.put(tm.get(i), i);
			}
			map.put(className, temp);
		}
		
		return map;
	}

}
