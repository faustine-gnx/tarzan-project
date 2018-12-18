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
 *         The GameApplication class is where the GUI is handled. It extends a
 *         JFrame and implements ActionListener. There a 4 possible panels for
 *         the game: the first one is always the StartPanel. From the
 *         StartPanel, the player can go to the GamePanel, the HighScoresPanel
 *         and the RulesPanel.
 * 
 */

public class GameApplication extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L; // added because class is serializable (because of button?)
	public final static int WIDTH = 800; // variable width of game panel
	public final static int HEIGHT = 1000; // variable height of game panel
	public final static String GAME_PANEL = "Game"; // variable name of game panel
	public final static String START_PANEL = "Start"; // variable name of start panel
	public final static String SCORES_PANEL = "Scores"; // variable name of scores panel
	public final static String RULES_PANEL = "Rules"; // variable name of rules panel

	// default values for safety
	private final static int DEFAULT_LEVEL = 1; // variable default level with value
	private final static int DEFAULT_INITIAL_STRENGTH = 50; // variable default initial strength with value
	private final static int DEFAULT_INITIAL_ENDURANCE = 50; // variable default initial endurance with value
	private final static String DEFAULT_NAME = "Anonymous"; // variable default player's name with value
	private String playerName = DEFAULT_NAME; // variable default player's name
	private int initialStrength = DEFAULT_INITIAL_STRENGTH; // declare variable default initial strength
	private int initialEndurance = DEFAULT_INITIAL_ENDURANCE;// declare variable default initial endurance
	private int level = DEFAULT_LEVEL;// declare variable default initial level

	private Game game;// variable game
	private JPanel allPanels;// variable allPanels
	private StartPanel startPanel; // variable start panel
	private GamePanel gamePanel;// variable panel of the game
	private HighScoresPanel scoresPanel;// variable panel of the high scores
	private RulesPanel rulesPanel; // variable panel of the rules
	private String START = "Start"; // variable with string of characters of the start button
	private String SCORES = "High Scores"; // variable with string of characters of the HighScores button
	private String RULES = "Rules";// variable with string of characters of the rules' button
	private String BACK = "Back";// variable with string of character of the back button
	private boolean gamePlaying = false; // variable with game playing equal to false since in the panel the game didn't
											// start yet

	private JButton startButton; // variable for the start button
	private JButton highScoresButton; // variable for the high scores button
	private JButton rulesButton; // variable for the rules button
	private JButton backButton1; // variable for the first back button (back from the panel of high scores)
	private JButton backButton2; // variable for the second back button (back from the panel of rules)

	/**
	 * Constructor. Initialize the GUI.
	 * @param game
	 */
	public GameApplication(Game game) {
		initUI();
		this.game = game;
	}

	/**
	 * Initialize the frame of the game.
	 */
	private void initUI() {
		setTitle("Tarzan - The Lost Adventure");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLocationRelativeTo(null);
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
	}

	/**
	 * Performs the actions when a button is clicked.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			playerName = startPanel.getNameField().getText();
			initialStrength = startPanel.getSkillsSlider().getValue();
			initialEndurance = 100 - initialStrength;
			level = startPanel.getLevelNumber();
			gamePanel.initGameSettings(playerName, initialStrength, initialEndurance, level);
			gamePlaying = true;
			game.initGame();
			// --> new GamePanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, GAME_PANEL);
			// cl.next(this.allPanels);
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, SCORES_PANEL);
		} else if (action.equals(RULES)) {
			// --> new RulesPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, RULES_PANEL);
		} else if (action.equals(BACK)) {
			// --> back to startPanel
			CardLayout cl = (CardLayout) (allPanels.getLayout());
			cl.show(allPanels, START_PANEL);
		}
	}
}
