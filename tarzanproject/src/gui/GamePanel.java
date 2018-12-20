package gui;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tarzan.Tarzan;

/**
 * @author Faustine & Martina
 * 
 *         The GamePanel class handles the display of the game when the player
 *         is playing. It has a canvas to show the Map/World and labels to show
 *         attributes values.
 * 
 */

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L; /**< added because class is serializable  */
	// Components of the game panel
	private Canvas gameCanvas; /**< canvas for the display of game */
	private JLabel levelLabel; /**< to show current level */
	private JLabel strengthLabel; /**< to show current strength */
	private JLabel enduranceLabel; /**< to show current endurance */
	private JLabel playerLabel; /**< to show player name */
	private JLabel energyLabel; /**< to show current energy */
	private JLabel jaguarLabel; /**< to show current jaguars killed */
	private JLabel scoreLabel; /**< to show current score */

	/**
	 * Constructor. Add a canvas to the GamePanel.
	 */
	public GamePanel() {
		gameCanvas = new Canvas();
		// squared canvas
		gameCanvas.setPreferredSize(new Dimension(GameApplication.WIDTH, GameApplication.WIDTH));
		gameCanvas.setMinimumSize(new Dimension(GameApplication.WIDTH, GameApplication.WIDTH));
		gameCanvas.setMaximumSize(new Dimension(GameApplication.WIDTH, GameApplication.WIDTH));
		gameCanvas.setFocusable(false);
		this.add(gameCanvas);
	}

	/**
	 * Getter.
	 * @return gameCanvas
	 */
	public Canvas getGameCanvas() {
		return gameCanvas;
	}

	/**
	 * Create and initialize the labels displayed under the canvas with the initial parameters.
	 * @param name, strength, endurance, level
	 */
	public void initGameSettings(String name, int strength, int endurance, int level) {
		levelLabel = new JLabel("Level: " + String.valueOf(level));
		strengthLabel = new JLabel("Strength: " + String.valueOf(strength));
		enduranceLabel = new JLabel("Endurance: " + String.valueOf(endurance));
		playerLabel = new JLabel("Player: " + name);
		jaguarLabel = new JLabel("Jaguars killed: " + String.valueOf(0));
		energyLabel = new JLabel("Energy left: " + String.valueOf(Tarzan.INITIAL_ENERGY));
		scoreLabel = new JLabel("Score: 0");
		this.add(playerLabel);
		this.add(levelLabel);
		this.add(strengthLabel);
		this.add(enduranceLabel);
		this.add(jaguarLabel);
		this.add(energyLabel);
		this.add(scoreLabel);
	}

	/**
	 * Update the labels displayed under the canvas with the current game parameters.
	 * @param newStrength, newEndurance, newEnergy, animals, goalStrength, goalEndurance, goalJaguars, score
	 */
	public void updateGameSettings(int newStrength, int newEndurance, int newEnergy, int animals, int goalStrength,
			int goalEndurance, int goalJaguars, int score) {
		energyLabel.setText("Energy left: " + String.valueOf(newEnergy));
		strengthLabel.setText("Strength: " + String.valueOf(newStrength) + "/" + String.valueOf(goalStrength));
		enduranceLabel.setText("Endurance: " + String.valueOf(newEndurance) + "/" + String.valueOf(goalEndurance));
		jaguarLabel.setText("Jaguars killed: " + String.valueOf(animals) + "/" + String.valueOf(goalJaguars));
		scoreLabel.setText("Score: " + String.valueOf(score));
	}
}
