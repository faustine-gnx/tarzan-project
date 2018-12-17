package gui;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tarzan.Tarzan;

/**
 * @author Faustine & Martina
 * 
 * The GamePanel class handles the display of the game when the player is playing.
 * It has a canvas to show the Map/World and labels to show attributes values.
 * 
 */

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel levelLabel; 
	private JLabel strengthLabel; 
	private JLabel enduranceLabel; 
	private JLabel playerLabel; 
	private Canvas gameCanvas;
	private JLabel energyLabel;
	private JLabel animalLabel;

	public GamePanel() {	
		gameCanvas = new Canvas();
		gameCanvas.setPreferredSize(new Dimension(GameApplication.WIDTH,GameApplication.WIDTH));
		gameCanvas.setMinimumSize(new Dimension(GameApplication.WIDTH,GameApplication.WIDTH));
		gameCanvas.setMaximumSize(new Dimension(GameApplication.WIDTH,GameApplication.WIDTH));
		gameCanvas.setFocusable(false);
		this.add(gameCanvas);
	}
	
	public Canvas getGameCanvas() {
		return gameCanvas;
	}
	
	public void initGameSettings(String name, int strength, int endurance, int level) {
		levelLabel = new JLabel("Level: " + String.valueOf(level));
		strengthLabel = new JLabel("Strength: " + String.valueOf(strength));
		enduranceLabel = new JLabel("Endurance: " + String.valueOf(endurance));
		playerLabel = new JLabel("Player: " + name);
		animalLabel = new JLabel("Jaguars killed: " + String.valueOf(0));
		energyLabel = new JLabel("Energy left: " + String.valueOf(Tarzan.INITIAL_ENERGY));
		this.add(playerLabel);
		this.add(levelLabel);
		this.add(strengthLabel);
		this.add(enduranceLabel);
		this.add(animalLabel);
		this.add(energyLabel);
	}
	
	public void updateGameSettings(int newStrength, int newEndurance, int newEnergy, int animals, int goalStrength, int goalEndurance, int goalJaguars) {
		energyLabel.setText("Energy left: " + String.valueOf(newEnergy));
		strengthLabel.setText("Strength: " + String.valueOf(newStrength) + "/" + String.valueOf(goalStrength));
		enduranceLabel.setText("Endurance: " + String.valueOf(newEndurance) + "/" + String.valueOf(goalEndurance));
		animalLabel.setText("Jaguars killed: " + String.valueOf(animals) + "/" + String.valueOf(goalJaguars));
	}
}

