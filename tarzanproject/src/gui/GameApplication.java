package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tilegame.Game;

/**
 * @author Faustine & Martina
 * 
 * The GameApplication class is where the GUI is handled. It extends a JFrame and implements ActionListener.
 * There a 4 possible panels for the game: the first one is always the StartPanel.
 * From the StartPanel, the player can go to the GamePanel, the HighScoresPanel and the RulesPanel.
 * 
 */

public class GameApplication extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; // added because class is serializable (because of button?)
	public final static int WIDTH = 800;
	public final static int HEIGHT = 1000;
	public final static String GAME_PANEL = "Game";
	public final static String START_PANEL = "Start";
	public final static String SCORES_PANEL = "Scores";
	public final static String RULES_PANEL = "Rules";

	// default values for safety
	private final static int DEFAULT_LEVEL = 1;	
	private final static int DEFAULT_INITIAL_STRENGTH = 50;	
	private final static int DEFAULT_INITIAL_ENDURANCE = 50;
	private final static String DEFAULT_NAME = "Anonymous";	
	private String storedName = DEFAULT_NAME;
	private int initialStrength = DEFAULT_INITIAL_STRENGTH;
	private int initialEndurance = DEFAULT_INITIAL_ENDURANCE;
	private int level = DEFAULT_LEVEL;

	private Game game;
	private JPanel allPanels;
	private StartPanel startPanel;
	private GamePanel gamePanel;
	private HighScoresPanel scoresPanel;
	private RulesPanel rulesPanel;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";
	private String BACK = "Back";
	private boolean gamePlaying = false;

	private JButton startButton;
	private JButton highScoresButton;
	private JButton rulesButton;
	private JButton backButton1;
	private JButton backButton2;

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

		allPanels = new JPanel(new CardLayout());
		allPanels.add(startPanel, START_PANEL);
		allPanels.add(scoresPanel, SCORES_PANEL);
		allPanels.add(rulesPanel, RULES_PANEL);
		allPanels.add(gamePanel, GAME_PANEL);

		getContentPane().add(allPanels);
		setVisible(true);	
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

	public int getLevel() {
		return level;
	}

	public JPanel getAllPanels() {
		return allPanels;
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
			initialStrength = startPanel.getStrengthEnduranceSlider().getValue();
			initialEndurance = 100 - initialStrength;
			level = startPanel.getLevelNumber();
			gamePanel.initGameSettings(storedName, initialStrength, initialEndurance, level);
			gamePlaying = true;
			game.initGame();
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
