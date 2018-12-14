package gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameApplication extends JFrame implements ActionListener {
	private JPanel allPanels;
	private final static String GAME_PANEL = "Game";
	private final static String START_PANEL = "Start";
	private final static String SCORES_PANEL = "Scores";
	private final static String RULES_PANEL = "Rules";
	private StartPanel start;
	private GamePanel game;
	private HighScoresPanel scores;
	private RulesPanel rules;
	private String player;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";
	private String BACK = "Back";
	
	private JButton startButton;
	private JButton highScoresButton;
	private JButton rulesButton;
	private JButton backButton1;
	private JButton backButton2;
	
	// default values for safety
	private final static int DEFAULT_LEVEL = 1;	
	private final static int DEFAULT_INITIAL_STRENGTH = 50;	
	private final static int DEFAULT_INITIAL_ENDURANCE = 50;	
	private final static int DEFAULT_INITIAL_ENERGY = 500;	
	private final static String DEFAULT_NAME = "Anonymous";	
	private String storedName = DEFAULT_NAME;
	private int initialStrength = DEFAULT_INITIAL_STRENGTH;
	private int initialEndurance = DEFAULT_INITIAL_ENDURANCE;
	private int level = DEFAULT_LEVEL;
	private int initialEnergy = DEFAULT_INITIAL_ENERGY; 
	
	public GameApplication() {
		initUI();
	}   
	
	private void initUI() {
		setTitle("Tarzan - The Lost Adventure");
		setPreferredSize(new Dimension(500, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		setResizable(false);
		start = new StartPanel();
		rules = new RulesPanel();
		scores = new HighScoresPanel();
		game = new GamePanel();
		
		highScoresButton = new JButton("High Scores");
		highScoresButton.setActionCommand(SCORES);
		highScoresButton.addActionListener(this);
		
		startButton = new JButton("Start");
		startButton.setActionCommand(START);
		startButton.addActionListener(this);
		
		rulesButton = new JButton("Game rules");
		rulesButton.setActionCommand(RULES);
		rulesButton.addActionListener(this);
		
		backButton1 = new JButton("Back");
		backButton1.setActionCommand(BACK);
		backButton1.addActionListener(this);
		
		backButton2 = new JButton("Back");
		backButton2.setActionCommand(BACK);
		backButton2.addActionListener(this);
		
		start.add(highScoresButton);
		start.add(rulesButton);
		rules.add(backButton1);
		scores.add(backButton2);
		start.add(startButton);

		// can we already create GamePanel?
		
		allPanels = new JPanel(new CardLayout());
		allPanels.add(start, START_PANEL);
		allPanels.add(scores, SCORES_PANEL);
		allPanels.add(rules, RULES_PANEL);
		allPanels.add(game, GAME_PANEL);

		//this.allPanels.add(game, GAME_PANEL); // ? Or can we add it later, once it is created?
		//this.add(allPanels);
		getContentPane().add(allPanels);
		setVisible(true);	
		// Image must go in StartPanel --> no need for container then?
		this.pack();
	}
	
	public GamePanel getGamePanel() {
		return game;
	}
	
	public String getStoredName() {
		return storedName;
	}
	
	public int getInitialStrength() {
		return initialStrength;
	}
	
	public int getInitialEndurance() {
		return initialEndurance;
	}
	
	public int getInitialEnergy() {
		return initialEnergy;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			String storedName = start.getNameField().getText();
			initialStrength = start.strengthEnduranceSlider.getValue();
			initialEndurance = 100 - initialStrength;
			level = this.start.getLevelNumber();
			initialEnergy = 500 - (this.level-1)*200; // Level 1 : 500; 2: 300; 3: 100
			game.setGameSettings(storedName, initialStrength, initialEndurance, level, initialEnergy);
			//this.allPanels.add(this.game, GAME_PANEL);
			// --> new GamePanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(allPanels, GAME_PANEL);
			//cl.next(this.allPanels);
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			//cl.show(this.allPanels, SCORES_PANEL);
			cl.next(allPanels);
		} else if (action.equals(RULES)) {
			// --> new RulesPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(allPanels, RULES_PANEL);
		} else if (action.equals(BACK)) {
			// --> back to startPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(allPanels, START_PANEL);
		}
	}
			
}
