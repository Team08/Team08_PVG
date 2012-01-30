package control;

import gui.BasicGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import model.Register;

public class RegisterButton extends JButton implements ActionListener {
	private BasicGUI gui;
	private Register register;

	public RegisterButton(BasicGUI gui, Register register) {
		super("Registrera förare");
		this.gui = gui;
		this.register = register;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		String name = gui.getDriverText();
		if (gui.getDriverText().length() != 0) {
			register.registerDriver(name);
			String[] times = register.writeToGUI(name);
			gui.writeInScrollPane(times[0], times[1], times[2]);

		}
	}

}