package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Race;

public class BasicGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int WIDTH = 500;
	private static int HEIGHT = 250;
	private JLabel display;
	private Race model;
	private JLabel time;
	
	public BasicGUI(String frameName,Race model){
		super(frameName);
		this.model = model;
		this.setLayout(new GridLayout(2,1));
		
		//DISPLAY
		JPanel displayPanel = new JPanel(new GridLayout(2,1));
		this.display = new JLabel("Display");
		this.time = new JLabel("");
		displayPanel.add(display);
		displayPanel.add(time);
		
		
		//BUTTONS
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartButtonListener());
		JButton finishButton = new JButton("Finish");
		finishButton.addActionListener(new FinishButtonListener());
		
		if(frameName.equals("Start")) buttonPanel.add(startButton);
		else buttonPanel.add(finishButton);
		
		//ADDS PANELS TO FRAME
		this.add(displayPanel);
		this.add(buttonPanel);
		
		//FINISHING TOUCHES
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class StartButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(model.startRace()){
				display.setText("Started");
				time.setText("");
			}
		}
	}
	class FinishButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			display.setText("Finished");
			time.setText("Time " + String.valueOf(model.stopRace()));
		}
	}
}
