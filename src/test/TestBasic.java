package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Scanner;

import main.Driver;
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
		assertTrue("Time was not greater than zero", register.registerDriver("Hanna").length > 0);
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
	

	
	
}
