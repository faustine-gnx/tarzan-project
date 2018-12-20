package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tilegame.Game;

/**
 * @author Faustine & Martina
 * 
 *         The GameApplication class is where the GUI is handled. It extends a
 *         JFrame and implements ActionListener. There a 4 possible panels for
 *         the game: the first one is always the StartPanel. From the
 *         StartPanel, the player can go to the GamePanel, the HighScoresPanel
 *         and the RulesPanel.
 * 
 */

public class GameApplication extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; // added because class is serializable
	public final static int WIDTH = 800; // width of game frame
	public final static int HEIGHT = 950; // height of game frame
	public final static String GAME_PANEL = "Game"; // name of game panel
	public final static String START_PANEL = "Start"; // name of start panel
	public final static String SCORES_PANEL = "Scores"; // name of scores panel
	public final static String RULES_PANEL = "Rules"; // name of rules panel
	private final static String START = "Start"; // name of start button
	private final static String SCORES = "High Scores"; // name of high scores button
	private final static String RULES = "Rules"; // name of rules button
	private final static String BACK = "Back"; // name of back button

	// default values for safety
	private final static int DEFAULT_LEVEL = 1; // default level value
	private final static int DEFAULT_INITIAL_STRENGTH = 50; // default initial strength value
	private final static int DEFAULT_INITIAL_ENDURANCE = 50; //  default initial endurance value
	private final static String DEFAULT_NAME = "Anonymous"; // default player's name string

	// Attributes
	private String playerName = DEFAULT_NAME; // player's name attribute
	private int initialStrength = DEFAULT_INITIAL_STRENGTH; // initial strength attribute
	private int initialEndurance = DEFAULT_INITIAL_ENDURANCE; // initial endurance attribute
	private int level = DEFAULT_LEVEL; // level attribute

	private Game game;// game attribute
	private JPanel allPanels; // allPanels: group the 3 panels
	private StartPanel startPanel; // start panel = menu + settings choice
	private GamePanel gamePanel; // game panel = canvas showing game + current game parameters/objectives
	private HighScorePanel scoresPanel; // high scores panel = max. 10 best scores with player name
	private RulesPanel rulesPanel; // rules panel = explain game

	private boolean gamePlaying = false; // boolean if game is currently being played
	private JButton startButton; // start button
	private JButton highScoresButton; // high score button
	private JButton rulesButton; // rules button
	private JButton backButton1; // back button (of high scores panel)
	private JButton backButton2; // back button (of rules panel)

	/**
	 * Constructor. Initialize the GUI.
	 * @param game
	 * @throws IOException 
	 */
	public GameApplication(Game game) throws IOException {
		initUI(playerName);
		this.game = game;
	}

	/**
	 * Constructor. Initialize the GUI.
	 * @param game, player
	 * @throws IOException 
	 */
	public GameApplication(Game game, String player) throws IOException {
		initUI(player);
		this.game = game;
	}

	/**
	 * Initialize the frame of the game.
	 * @throws IOException 
	 */
	public void initUI(String player) throws IOException {
		System.out.println("Initializing the GUI...");
		gamePlaying = false;
		setTitle("Tarzan - The Lost Adventure");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		startPanel = new StartPanel(player);
		rulesPanel = new RulesPanel();
		scoresPanel = new HighScorePanel();
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

	/**
	 * Getter.
	 * @return gamePanel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * Getter.
	 * @return storedName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Getter.
	 * @return initialStrength
	 */
	public int getInitialStrength() {
		return initialStrength;
	}

	/**
	 * Getter.
	 * @return initialEndurance
	 */
	public int getInitialEndurance() {
		return initialEndurance;
	}

	/**
	 * Getter.
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Getter.
	 * @return allPanels
	 */
	public JPanel getAllPanels() {
		return allPanels;
	}

	/**
	 * Returns true if the game has started (GamePanel accessed).
	 *  @return gamePlaying
	 */
	public boolean isGamePlaying() {
		return gamePlaying;
	}

	/**
	 * Shows a message dialog. Called when the game is ended.
	 */
	public void newJOptionPane(String text) {
		JOptionPane.showMessageDialog(this, text, "Game over", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Game over");
	}

	/**
	 * Performs the actions when a button is clicked.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) { // start game with settings chosen in start panel
			playerName = startPanel.getNameField().getText();
			initialEndurance = startPanel.getSkillsSlider().getValue();
			initialStrength = 100 - initialEndurance;
			level = startPanel.getLevelNumber();
			gamePanel.initGameSettings(playerName, initialStrength, initialEndurance, level);
			gamePlaying = true;
			game.initGame();
			// --> GamePanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, GAME_PANEL);
			System.out.println("Start playing " + playerName + "!");
		} else if (action.equals(SCORES)) {
			// --> HighScoresPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, SCORES_PANEL);
			System.out.println("Showing high scores");
		} else if (action.equals(RULES)) {
			// --> RulesPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, RULES_PANEL);
			System.out.println("Showing rules");
		} else if (action.equals(BACK)) {
			// --> back to startPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, START_PANEL);
			System.out.println("Back to start panel");
		}
	}
}
