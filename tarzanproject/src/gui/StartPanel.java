package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

// start our game
public class StartPanel extends JPanel {
	JLabel welcomeLabel; 
	JButton startButton;
	JButton highScoresButton;
	JButton rulesButton;
	JComponent nameArea;
	JSlider strengthEnduranceSlider;

	// create JPanel to group some components

	public StartPanel() {	
		this.welcomeLabel = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!", SwingConstants.CENTER);
		this.startButton = new JButton("Start");
		this.highScoresButton = new JButton("High Scores");
		this.rulesButton = new JButton("Game rules");
		this.nameArea = new JTextArea("Replace this text by your name");
		this.strengthEnduranceSlider = new JSlider(0,100,50);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		StartButtonListener startListener = new StartButtonListener(); // or in frame (=GameApp?)
		this.startButton.addActionListener(startListener);
		HighScoresButtonListener highScoresListener = new HighScoresButtonListener();
		this.startButton.addActionListener(highScoresListener);
		RulesButtonListener rulesListener = new RulesButtonListener();
		this.startButton.addActionListener(rulesListener);
		
		this.add(welcomeLabel);
		this.add(nameArea);
		this.add(startButton);
		this.add(highScoresButton);
		this.add(rulesButton);
		this.add(strengthEnduranceSlider);
		
		/*this.add(welcomeLabel, BorderLayout.NORTH);
		this.add(nameArea, BorderLayout.SOUTH);
		this.add(startButton, BorderLayout.CENTER);
		this.add(highScoresButton, BorderLayout.WEST);
		this.add(rulesButton, BorderLayout.EAST);*/

		setVisible(true);
	}


}

