package main;
import model.Register;
import gui.BasicGUI;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BasicGUI("Start",new Register(new Driver("Team08")));
	}
}
