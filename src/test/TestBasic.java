package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Scanner;

import main.Driver;
import main.Time;
import model.Register;
import main.Sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBasic {
private Register register;


	@Before public void setup(){
		this.register = new Register();
	}
	
	@Test public void  testThatRaceTimeIsGreaterThanZero(){
		register.registerDriver("Hanna");
		assertTrue("Time was not greater than zero", Time.makeTimeList().length > 0);
	}
	
	@Test public void testDriversName(){
		Driver driver = new Driver("Joe");
		assertEquals("Joe",driver.getName());
	}
	
	@Test public void testThatRegisterFileCreates(){
		register.registerDriver("Joe");
		Scanner sc = new Scanner("Register.txt");
		assertTrue("The Registerfile was not found", sc.hasNext()); 
		sc.close();
	}
	@Test public void testThatRegisterFileContainsCorrectName(){
		register.registerDriver("Hanna");
		File file = new File("Register.txt");
		try {
		Scanner sc = new Scanner(file);
		String[] firstLine = new String[3];
		if(sc.hasNext()){
		String string = sc.nextLine();
		firstLine = string.split("; ");
		}
		assertEquals("The Registerfile was not found", "Hanna", firstLine[0]); 
		sc.close();
	} catch (Exception e){
		e.printStackTrace();
	}
	}
}
