package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Register;

public class BasicGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 1920;
	private static int HEIGHT = 1080;
	private Register register;
	private JTextArea textArea;
	private JTextField driverID;
	private JButton registerButton;
	private JPanel displayPanel;
	private JPanel topPanel;
	private JScrollPane scrollPane;
	private String lastRegisteredRiders;

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
		registerButton.addActionListener(new RegisterButtonListener());

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

		lastRegisteredRiders = new String("Senaste registerade tider");
		textArea.setText(lastRegisteredRiders);

		registerButton = new JButton("Registrera förare");
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

	private void writeToFile(int hours, int minutes, int seconds) {
		try {
			// Create file

			FileWriter fstream = new FileWriter("register", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(driverID.getText() + "; " + hours + "." + minutes + "."
					+ seconds + "\n");

			// Close the output stream
			out.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}

	private void writeInScrollPane(int hours, int minutes, int seconds) {
		String stringMinutes = new String(minutes + "");
		String stringSeconds = new String(seconds + "");
		if (minutes < 10) {
			stringMinutes = 0 + stringMinutes;
		}
		if (seconds < 10) {
			stringSeconds = 0 + stringSeconds;
		}
		register.startRace();
		lastRegisteredRiders = lastRegisteredRiders + "\n" + driverID.getText()
				+ "; " + hours + "." + stringMinutes + "." + stringSeconds;
		textArea.setText(lastRegisteredRiders);
		driverID.setText("");
	}

	class RegisterButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (driverID.getText().length() != 0) {
				GregorianCalendar calendar = new GregorianCalendar();

				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);
				int seconds = calendar.get(Calendar.SECOND);

				writeToFile(hours, minutes, seconds);
				writeInScrollPane(hours, minutes, seconds);

			}

		}
	}

}
