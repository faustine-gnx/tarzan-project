package gui;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

// Game display
public class GamePanel extends JPanel {
	private JLabel levelLabel; 
	private JLabel strengthLabel; 
	private JLabel enduranceLabel; 
	private JLabel playerLabel; 
	private Canvas gameCanvas;
	private JLabel energyLabel;
	
	private JLabel animalLabel;
	private String player;
	private int strength;
	private int endurance;
	private int energy;
	private int animalsKilled;
	private int level;

	public GamePanel() {	
		gameCanvas = new Canvas();
		gameCanvas.setPreferredSize(new Dimension(500,500));
		gameCanvas.setMinimumSize(new Dimension(500,500));
		gameCanvas.setMaximumSize(new Dimension(500,500));
		gameCanvas.setFocusable(false);
		this.add(gameCanvas);
	}
	
	public Canvas getGameCanvas() {
		return gameCanvas;
	}
	
	public void initGameSettings(String name, int strength, int endurance, int level, int energy) {
		levelLabel = new JLabel("Level: " + String.valueOf(level));
		strengthLabel = new JLabel("Strength: " + String.valueOf(strength));
		enduranceLabel = new JLabel("Endurance: " + String.valueOf(endurance));
		playerLabel = new JLabel("Player: " + name);
		animalLabel = new JLabel("Animals killed: " + String.valueOf(0));
		energyLabel = new JLabel("Energy left: " + String.valueOf(energy));
		player = name;
		this.strength = strength;
		this.endurance = endurance;
		this.energy = energy;
		this.animalsKilled = 0;
		this.level = level;
		this.add(playerLabel);
		this.add(levelLabel);
		this.add(strengthLabel);
		this.add(enduranceLabel);
		this.add(animalLabel);
		this.add(energyLabel);
	}
	
	public void updateGameSettings(int newStrength, int newEndurance, int newEnergy, int animals) {
		strengthLabel.setText("Strength: " + String.valueOf(newStrength));
		enduranceLabel.setText("Endurance: " + String.valueOf(newEndurance));
		animalLabel.setText("Animals killed: " + String.valueOf(animals));
		energyLabel.setText("Energy left: " + String.valueOf(newEnergy));
		strength = newStrength;
		endurance = newEndurance;
		animalsKilled = animals;
		energy = newEnergy;
		
		/*this.add(this.strengthLabel);
		this.add(this.enduranceLabel);
		this.add(this.animalLabel);
		this.add(this.energyLabel);*/ // I don't think need to be called again?
	}
	
	public void updateStrengthLabel(int newStrength) {
		strengthLabel.setText("Strength: " + String.valueOf(newStrength));
		strength = newStrength;
		//this.add(this.strengthLabel); // I don't think need to be called again?
	}
	
	public void updateEnduranceLabel(int newEndurance) {
		strengthLabel.setText("Endurance: " + String.valueOf(newEndurance));
		strength = newEndurance;
		//this.add(this.enduranceLabel); // I don't think need to be called again?
	}

	public void updateAnimalLabel(int animals) {
		animalLabel.setText("Animals killed: " + String.valueOf(animals));
		animalsKilled = animals;
		//this.add(this.animalLabel); // I don't think need to be called again?
	}
	
	public void updateEnergyLabel(int newEnergy) {
		energyLabel.setText("Initial strength: " + String.valueOf(newEnergy));
		energy = newEnergy;
		//this.add(this.energyLabel); // I don't think need to be called again?
	}
	
	public void setGamePanelFocusable() { // not working as i want
		this.setFocusable(true);
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}

