package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import util.Time;
import model.Register;

/**
 * A button namned "Registrera" in the GUI. Listens to the button
 * and when it's pushed calls a method in Register.java that
 * writes the actual time to the file. 
 * 
 * Also updates the GUI.
 * @author Team08
 *
 */
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
	/**
	 * Registers a new time in the GUI and at the same time
	 * calls the Register.java which writes the registered time
	 * to file.
	 * 
	 * If no start number is entered there will be a popup
	 * which you can enter the start number in later. This is
	 * used when the start plate is too dirty to read instantly.
	 * 
	 */
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