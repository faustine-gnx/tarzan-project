package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
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
import javax.swing.SwingConstants;

// start our game
public class StartPanel extends JPanel {
	JLabel welcomeLabel; 
	JButton startButton;
	JButton highScoresButton;
	JButton rulesButton;
	JComponent nameArea;
	JSlider strengthEnduranceSlider;
	JComboBox<String> levelComboBox;

	public JLabel image ;
	public Container c; 

	// Start panel

	public StartPanel() {	
		this.welcomeLabel = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!",SwingConstants.CENTER);
		this.startButton = new JButton("Start");
		this.highScoresButton = new JButton("High Scores");
		this.rulesButton = new JButton("Game rules");
		this.nameArea = new JTextArea("Replace this text by your name");
		this.strengthEnduranceSlider = new JSlider(0,100,50);
		Hashtable labelTable = new Hashtable();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		this.strengthEnduranceSlider.setLabelTable(labelTable);
		this.strengthEnduranceSlider.setPaintLabels(true);
		this.levelComboBox = new JComboBox<String>(new String[]{" Level 1 ", " Level 2 ", " Level 3 "});

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.startButton.addActionListener(new StartButtonListener());
		this.highScoresButton.addActionListener(new HighScoresButtonListener());
		this.rulesButton.addActionListener(new RulesButtonListener());
		this.strengthEnduranceSlider.addChangeListener(new SliderListener());
		this.levelComboBox.addActionListener(new LevelComboListener());

		ImageIcon img = new ImageIcon("resources/jungle_tarzan.jpg");
		this.image = new JLabel(img);
				
		this.add(this.welcomeLabel);
		this.add(this.nameArea);
		this.add(this.highScoresButton);
		this.add(this.rulesButton);
		this.add(this.strengthEnduranceSlider);
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

}

