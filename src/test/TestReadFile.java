package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;
import main.Driver;
import main.ReadFile;
import main.Register;
import main.Sorter;
import org.junit.Before;
import org.junit.Test;

public class TestReadFile {
	private Register register;
	private Driver driver;
	private Sorter sorter;
//	private String[] fileIn;
	private ReadFile read;
	
//	fileIn[1] = "2; 12.00.01";
//	fileIn[2] = "3; 12.00.02";
//	fileIn[3] = "4; 12.00.03";

	
	@Before public void setup(){
		this.driver = new Driver("Team08");
		this.register = new Register(driver);
		this.sorter = new Sorter("Start.txt","Stop.txt");
//		this.fileIn = new String[4];
		this.read = new ReadFile();
	}
	

	@Test public void TestReadFileIntoTree() throws FileNotFoundException{
		TreeMap<Integer,String> map = new TreeMap<Integer,String>();
		map.put(1, "12.00.00");
		map.put(2, "12.00.01");
		map.put(3, "12.00.02");
		map.put(4, "12.00.03");
		File file = new File("TestStart.test");
		TreeMap<Integer,String> map2 = read.read2(file);
		assertEquals("",map.size(),map2.size());
		
		for(int i=1; i<map.size()+1;i++){
			assertEquals("",map.get(i),map2.get(i));
		}
	}

	@Test
	public void TestReadFileSeperator() throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("12.00.00");
		list.add("2");
		list.add("12.00.01");
		list.add("3");
		list.add("12.00.02");
		list.add("4");
		list.add("12.00.03");
		File file = new File("TestStart.test");
		ArrayList<String> list2 = read.read(file);
		assertEquals("Wrong size Arraylist", list.size(), list2.size());
		for (int i = 0; i < list.size(); i++) {
			assertEquals("", list.get(i), list2.get(i));
		}
	}

	@Test(expected = FileNotFoundException.class)
	public void TestNoFile() throws FileNotFoundException {
		File file = new File("FinnsEJ.test");
		TreeMap<Integer, String> map2 = read.read2(file);

	}
}