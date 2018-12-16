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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tilegame.Game;

public class GameApplication extends JFrame implements ActionListener {
	public final static int WIDTH = 500;
	public final static int HEIGHT = 800;
	private JPanel allPanels;
	public final static String GAME_PANEL = "Game";
	public final static String START_PANEL = "Start";
	public final static String SCORES_PANEL = "Scores";
	public final static String RULES_PANEL = "Rules";
	private Game game;
	private StartPanel startPanel;
	private GamePanel gamePanel;
	private HighScoresPanel scoresPanel;
	private RulesPanel rulesPanel;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";
	private String BACK = "Back";
	private boolean gamePlaying = false;
	public JPanel getAllPanels() {
		return allPanels;
	}

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
	
	public GameApplication(Game game) {
		initUI();
		this.game = game;
	}   
	
	private void initUI() {
		setTitle("Tarzan - The Lost Adventure");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		setResizable(false);
		startPanel = new StartPanel();
		rulesPanel = new RulesPanel();
		scoresPanel = new HighScoresPanel();
		gamePanel = new GamePanel();
		
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
		
		startPanel.add(highScoresButton);
		startPanel.add(rulesButton);
		rulesPanel.add(backButton1);
		scoresPanel.add(backButton2);
		startPanel.add(startButton);

		// can we already create GamePanel?
		
		allPanels = new JPanel(new CardLayout());
		allPanels.add(startPanel, START_PANEL);
		allPanels.add(scoresPanel, SCORES_PANEL);
		allPanels.add(rulesPanel, RULES_PANEL);
		allPanels.add(gamePanel, GAME_PANEL);

		//this.allPanels.add(game, GAME_PANEL); // ? Or can we add it later, once it is created?
		//this.add(allPanels);
		getContentPane().add(allPanels);
		setVisible(true);	
		// Image must go in StartPanel --> no need for container then?
		this.pack();
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
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
	
	public boolean isGamePlaying() {
		return gamePlaying;
	}
	
	public void newJOptionPane(String text) {
		JOptionPane.showMessageDialog(this, text, "Game over", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			String storedName = startPanel.getNameField().getText();
			initialStrength = startPanel.strengthEnduranceSlider.getValue();
			initialEndurance = 100 - initialStrength;
			level = startPanel.getLevelNumber();
			initialEnergy = 500 - (level-1)*100; // Level 1 : 500; 2: 300; 3: 100
			gamePanel.initGameSettings(storedName, initialStrength, initialEndurance, level, initialEnergy);
			gamePlaying = true;
			game.initGame();
			//this.allPanels.add(this.game, GAME_PANEL);
			// --> new GamePanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(allPanels, GAME_PANEL);
			//cl.next(this.allPanels);
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(allPanels, SCORES_PANEL);
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
