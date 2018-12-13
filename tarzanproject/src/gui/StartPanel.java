package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;*/

// start our game
public class StartPanel extends JPanel implements ActionListener, ChangeListener {
	protected JLabel welcomeLabel; 
	protected JButton startButton;
	protected JButton highScoresButton;
	protected JButton rulesButton;
	protected JTextField nameField;
	protected JSlider strengthEnduranceSlider;
	protected JComboBox<String> levelComboBox;
	protected JLabel enterName;
	protected JLabel chooseLevel;
	protected JLabel chooseStrengthEndurance;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";

	public JLabel image ;
	public Container c; 

	// Start panel

	public StartPanel() {	
		this.welcomeLabel = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!",SwingConstants.CENTER);
		this.enterName = new JLabel("Enter your name:");
		//this.enterName.setBounds(10, 10, 10, 10);
		this.chooseLevel = new JLabel("Choose a level:");
		this.chooseStrengthEndurance = new JLabel("Choose the strength-endurance distribution:");
		this.startButton = new JButton("Start");
		this.startButton.setActionCommand(START);
		this.highScoresButton = new JButton("High Scores");
		this.highScoresButton.setActionCommand(SCORES);
		this.rulesButton = new JButton("Game rules");
		this.rulesButton.setActionCommand(RULES);
		this.nameField = new JTextField("Anonymous");
		this.strengthEnduranceSlider = new JSlider(0,100,50);
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		this.strengthEnduranceSlider.setLabelTable(labelTable);
		this.strengthEnduranceSlider.setPaintLabels(true);
		this.levelComboBox = new JComboBox<String>(new String[]{" Level 1 ", " Level 2 ", " Level 3 "});

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		/*this.startButton.addActionListener(new StartButtonListener());
		this.highScoresButton.addActionListener(new HighScoresButtonListener());
		this.rulesButton.addActionListener(new RulesButtonListener());
		this.strengthEnduranceSlider.addChangeListener(new SliderListener());
		this.levelComboBox.addActionListener(new LevelComboListener());*/
		
		this.startButton.addActionListener(this);
		this.highScoresButton.addActionListener(this);
		this.rulesButton.addActionListener(this);
		this.strengthEnduranceSlider.addChangeListener(this);
		this.levelComboBox.addActionListener(this);

		ImageIcon img = new ImageIcon("resources/jungle_tarzan.jpg");
		this.image = new JLabel(img);
				
		this.add(this.welcomeLabel);
		this.add(this.enterName);
		this.add(this.nameField);
		this.add(this.highScoresButton);
		this.add(this.rulesButton);
		this.add(this.chooseStrengthEndurance);
		this.add(this.strengthEnduranceSlider);
		this.add(this.chooseLevel);
		this.add(this.levelComboBox);
		this.add(this.startButton);
		this.add(this.image);

		/*this.add(welcomeLabel, BorderLayout.NORTH);
		this.add(nameArea, BorderLayout.SOUTH);
		this.add(startButton, BorderLayout.CENTER);
		this.add(highScoresButton, BorderLayout.WEST);
		this.add(rulesButton, BorderLayout.EAST);*/
		
		/*
		c = this.getContentPane();
		//image = new JLabel(new ImageIcon("/textures/jungle_tarzan.jpg"));
		//BufferedImage img = ImageIO.read("/textures/jungle_tarzan.jpg");
		image = new JLabel();
		image.setSize(1000, 1000);

		c.setLayout(new FlowLayout());
		c.add(image);

		add(image);*/

		setVisible(true);	
	}
	
	public JTextField getNameField() {
		return this.nameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			String storedName = this.nameField.getText();
			// --> new GamePanel
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
		} else if (action.equals(RULES)) {
			// --> new RulesPanel
		}

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		
	}

}

