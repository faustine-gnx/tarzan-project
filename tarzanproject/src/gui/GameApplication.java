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
	JPanel allPanels;
	final static String GAME_PANEL = "Game";
	final static String START_PANEL = "Start";
	final static String SCORES_PANEL = "Scores";
	final static String RULES_PANEL = "Rules";
	public StartPanel start;
	public GamePanel game;
	public HighScoresPanel scores;
	public RulesPanel rules;
	public String player;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";
	private String BACK = "Back";
	
	protected JButton startButton;
	protected JButton highScoresButton;
	protected JButton rulesButton;
	protected JButton backButton1;
	protected JButton backButton2;
	
	// default values for safety
	protected String storedName = "Anonymous";
	protected int initialStrength = 50;
	protected int initialEndurance = 50;
	protected int level = 1;
	protected int initialEnergy = 500; // Level 1
	
	public GameApplication() {
		initUI();
	}   
	
	private void initUI() {
		this.setTitle("Tarzan - The Lost Adventure");
		this.setPreferredSize(new Dimension(500, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.start = new StartPanel();
		this.rules = new RulesPanel();
		this.scores = new HighScoresPanel();
		this.game = new GamePanel();
		
		this.highScoresButton = new JButton("High Scores");
		this.highScoresButton.setActionCommand(SCORES);
		this.highScoresButton.addActionListener(this);
		
		this.startButton = new JButton("Start");
		this.startButton.setActionCommand(START);
		this.startButton.addActionListener(this);
		
		this.rulesButton = new JButton("Game rules");
		this.rulesButton.setActionCommand(RULES);
		this.rulesButton.addActionListener(this);
		
		this.backButton1 = new JButton("Back");
		this.backButton1.setActionCommand(BACK);
		this.backButton1.addActionListener(this);
		
		this.backButton2 = new JButton("Back");
		this.backButton2.setActionCommand(BACK);
		this.backButton2.addActionListener(this);
		
		this.start.add(this.highScoresButton);
		this.start.add(this.rulesButton);
		this.rules.add(this.backButton1);
		this.scores.add(this.backButton2);
		this.start.add(this.startButton);

		// can we already create GamePanel?
		this.pack();
		this.allPanels = new JPanel(new CardLayout());
		this.allPanels.add(this.start, START_PANEL);
		this.allPanels.add(this.scores, SCORES_PANEL);
		this.allPanels.add(this.rules, RULES_PANEL);
		this.allPanels.add(this.game, GAME_PANEL);

		//this.allPanels.add(game, GAME_PANEL); // ? Or can we add it later, once it is created?
		//this.add(allPanels);
		getContentPane().add(this.allPanels);
		setVisible(true);	
		// Image must go in StartPanel --> no need for container then?
	}
	
	public GamePanel getGamePanel() {
		return this.game;
	}
	
	public String getStoredName() {
		return this.storedName;
	}
	
	public int getInitialStrength() {
		return this.initialStrength;
	}
	
	public int getInitialEndurance() {
		return this.initialEndurance;
	}
	
	public int getInitialEnergy() {
		return this.initialEnergy;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			String storedName = this.start.getNameField().getText();
			this.initialStrength = this.start.strengthEnduranceSlider.getValue();
			this.initialEndurance = 100 - initialStrength;
			this.level = this.start.getLevelNumber();
			this.initialEnergy = 500 - (this.level-1)*200; // Level 1 : 500; 2: 300; 3: 100
			this.game.setGameSettings(storedName, initialStrength, initialEndurance, level, initialEnergy);
			//this.allPanels.add(this.game, GAME_PANEL);
			// --> new GamePanel
			CardLayout cl = (CardLayout)(this.allPanels.getLayout());
			cl.show(this.allPanels, GAME_PANEL);
			//cl.next(this.allPanels);
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
			CardLayout cl = (CardLayout)(this.allPanels.getLayout());
			//cl.show(this.allPanels, SCORES_PANEL);
			cl.next(this.allPanels);
		} else if (action.equals(RULES)) {
			// --> new RulesPanel
			CardLayout cl = (CardLayout)(this.allPanels.getLayout());
			cl.show(this.allPanels, RULES_PANEL);
		} else if (action.equals(BACK)) {
			// --> back to startPanel
			CardLayout cl = (CardLayout)(this.allPanels.getLayout());
			cl.show(this.allPanels, START_PANEL);
		}
	}
			
}
