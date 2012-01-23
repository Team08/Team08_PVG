package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Register;

public class BasicGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 500;
	private static int HEIGHT = 250;
	private JLabel display;
	private Register model;
	private JLabel time;
	private JLabel latest;
	private JTextArea textArea;
	private JTextField driverID;
	private JButton registerButton;
	private JPanel displayPanel;
	private JPanel buttonPanel;

	public BasicGUI(String frameName, Register model) {
		super(frameName);
		this.model = model;
		this.setLayout(new GridLayout(2, 1));
		init();

		addComponents();

		setupLayout();
	}

	private void setupLayout() {
		buttonPanel.setLayout(new GridLayout(1, 2));

		// add register
		registerButton.addActionListener(new RegisterButtonListener());

		// FINISHING TOUCHES
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// Add components
	private void addComponents() {
		displayPanel.add(latest);
		displayPanel.add(textArea);

		buttonPanel.add(driverID);
		buttonPanel.add(registerButton);

		// ADDS PANELS TO FRAME
		this.add(buttonPanel);
		this.add(displayPanel);
	}

	// Initialize the componnets
	private void init() {
		displayPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel = new JPanel();
		latest = new JLabel("Senast registerade tider");
		textArea = new JTextArea(10, 30);
		driverID = new JTextField("#");
		registerButton = new JButton("Start");
		setFonts();
	}

	// Sets the bigger fonts for all components
	private void setFonts() {
		Font newTextFont = new Font(textArea.getFont().getName(), textArea
				.getFont().getStyle(), 50);
		Font newSmallTextFont = new Font(textArea.getFont().getName(), textArea
				.getFont().getStyle(), 70);
		Font newBigTextFont = new Font(textArea.getFont().getName(), textArea
				.getFont().getStyle(), 20);
		textArea.setFont(newSmallTextFont);
		registerButton.setFont(newTextFont);
		driverID.setFont(newBigTextFont);
		latest.setFont(newTextFont);
	}

	class RegisterButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			Date startTime = model.startRace();
			textArea.setText(startTime.toString());

		}
	}

}
