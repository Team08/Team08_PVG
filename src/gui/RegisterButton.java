package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.Time;
import model.Register;

public class RegisterButton extends JButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BasicGUI gui;
	private Register register;

	/**
	 * The constructor which creates a RegisterButton
	 * 
	 * @param gui
	 *            the basicGUI
	 * @param register
	 *            the target register
	 */
	public RegisterButton(BasicGUI gui, Register register) {
		super("Registrera förare");
		this.gui = gui;
		this.register = register;
		addActionListener(this);
	}

	/**
	 * The actionlistener of RegisterButton, updates the GUI
	 * 
	 * @param arg0
	 *            the ActionEvent
	 */
	public void actionPerformed(ActionEvent arg0) {
		pushedButton();

	}

	protected void pushedButton(){
		String id = gui.getDriverID();
		String[] times = Time.makeTimeList();
		if (gui.getDriverID().length() == 0) {
			try {

				String driverID = JOptionPane.showInputDialog(null,
						"Den registrerade tiden är : " + times[0] + "."
								+ times[1] + "." + times[2]
								+ " \n Förarnummer: ");
				if (!driverID.equals(JOptionPane.OK_OPTION)) {
					if(driverID.length() == 0){
						wrongID();
					}else {
					try {
						int idcheck = Integer.parseInt(driverID);
						if (idcheck < 0){
							wrongID();
						}else{
						
							regDriverToFile(driverID, times);

						
						}
					} catch (NumberFormatException e) {
						wrongID();
					}
					
				}}

			} catch (NullPointerException e) {
			}
		} else {
			try {
				int idcheck = Integer.parseInt(id);
				if (idcheck<0){
					wrongID();
				}else{
				if (gui.getDriverID().length() != 0) {
					regDriverToFile(id, times);

				}
				}
			} catch (NumberFormatException e) {
				wrongID();
			}
		}
	}
	private void wrongID(){
		gui.makeTextFieldEmpty();
		JOptionPane.showMessageDialog(null,
				"IDnumret får bara vara positiva heltal");
	}
	
	private void regDriverToFile(String name, String[] times) {
		register.registerDriver(name);
		gui.writeInScrollPane(times[0], times[1], times[2], name);
	}
}