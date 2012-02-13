package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import model.Register;
/** 
 * The GUI class which creates the graphics of the Register program.
 * @author Team08
 *
 */
public class BasicGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 1920;
	private static int HEIGHT = 1080;
	private Register register;
	private JTextArea textArea;
	private JTextField driverID;
	private RegisterButton registerButton;
	private JPanel displayPanel;
	private JPanel topPanel;
	private JScrollPane scrollPane;
	private String lastRegisteredDrivers;

	/**
	 * The constructor which creates the GUI
	 * 
	 * @param frameName
	 *            the name of the frame and the register object
	 * @param register
	 * 			  the target register           
	 */
	public BasicGUI(String frameName, Register register) {
		super(frameName);
		this.register = register;
		this.setLayout(new BorderLayout());
		init();
		addComponents();
		setupLayout();
	}

	private void setupLayout() {
		topPanel.setLayout(new GridLayout(1, 2));

		// FINISHING TOUCHES
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// Add components
	private void addComponents() {
		displayPanel.add(scrollPane);
		topPanel.add(driverID);
		topPanel.add(registerButton);

		// ADDS PANELS TO FRAME
		this.add(topPanel, BorderLayout.NORTH);
		this.add(displayPanel, BorderLayout.CENTER);
	}

	// Initialize the components
	private void init() {
		displayPanel = new JPanel(new GridLayout(1, 1));
		topPanel = new JPanel();
		textArea = new JTextArea(10, 30);
		driverID = new JTextField("");
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		driverID.addActionListener(this);

		lastRegisteredDrivers = new String("Senaste registrerade tider");
		textArea.setText(lastRegisteredDrivers);
		registerButton = new RegisterButton(this, register);
		setFonts();
	}

	// Sets the bigger fonts for all components
	private void setFonts() {
		Font newTextFont = new Font(textArea.getFont().getName(), textArea
				.getFont().getStyle(), 70);

		Font newBigTextFont = new Font(textArea.getFont().getName(), textArea
				.getFont().getStyle(), 90);

		textArea.setFont(newTextFont);
		registerButton.setFont(newBigTextFont);
		driverID.setFont(newTextFont);
	}

	/**
	 * Add text to the ScrollPane
	 * 
	 * @param hours
	 *              the actual hours
	 * @param minutes
	 * 				the actual minutes
	 * @param seconds
	 * 				the actual seconds
	 * @param driver       
	 * 				the actual driver   
	 */
	public void writeInScrollPane(String hours, String minutes, String seconds, String driver) {
		lastRegisteredDrivers = lastRegisteredDrivers + "\n"
				+ driver + "; " + hours + "." + minutes + "."
				+ seconds;
		textArea.setText(lastRegisteredDrivers);
		makeTextFieldEmpty();
	}

	/**
	 * Makes the text field in the GUI empty
	 */
	public void makeTextFieldEmpty() {
		driverID.setText("");
	}

	/**
	 * Returns a driver ID from the JTextField
	 * @return the registered time and start number
	 */
	protected String getDriverID() {
		return driverID.getText();
	}

	/**
	 * Sees if an action is performed in the GUI (e.g. if the
	 * register button is pushed)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		registerButton.pushedButton();
		
	}
	
	

}