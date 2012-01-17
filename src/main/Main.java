package main;
import gui.BasicGUI;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BasicGUI("Start",new Race(new Driver("Team08")));
		new BasicGUI("Finish",new Race(new Driver("Team08")));
	}
}
