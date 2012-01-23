package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadFile {

	public ReadFile() {
	}

	public ArrayList<String> read(File filename) throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>();

		Scanner scan;
		try {
			scan = new Scanner(filename);
			//Filerna måste ha ";" mellan varje string
			scan.useDelimiter(";");
			String nextStr;
			while (scan.hasNext()) {
				nextStr = scan.next();
				list.add(nextStr.trim());
				System.out.println(nextStr.trim());
			}
		} catch (FileNotFoundException e) {// Catch exception if any
			throw new FileNotFoundException();
		}
		return list;

	}

	public TreeMap<Integer, String> read2(File filename) throws FileNotFoundException {

		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		Scanner scan;
		try {
			scan = new Scanner(filename);
			scan.useDelimiter(";");
			
			while (scan.hasNext()) {
				map.put(Integer.parseInt(scan.next().trim()), scan.next().trim());
			}
		} catch (FileNotFoundException e) {// Catch exception if any
			throw new FileNotFoundException();
		}
		return map;

	}

}
