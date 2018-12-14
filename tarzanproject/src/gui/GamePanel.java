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

import map.Map;

// Game display
public class GamePanel extends JPanel {
	protected JLabel levelLabel; 
	protected JLabel strengthLabel; 
	protected JLabel enduranceLabel; 
	protected JLabel playerLabel; 
	protected Canvas gameCanvas;
	protected JLabel energyLabel;
	protected JLabel animalLabel;
	String player;
	int strength;
	int endurance;
	int energy;
	int animalsKilled;
	int level;

	public GamePanel() {	

		this.gameCanvas = new Canvas();
		this.gameCanvas.setPreferredSize(new Dimension(500,500));
		this.gameCanvas.setMinimumSize(new Dimension(500,500));
		this.gameCanvas.setMaximumSize(new Dimension(500,500));
		this.add(this.gameCanvas);
	}
	
	public Canvas getGameCanvas() {
		return this.gameCanvas;
	}
	
	public void setGameSettings(String name, int strength, int endurance, int lvl, int energy) {
		this.levelLabel = new JLabel("Level: " + String.valueOf(lvl));
		this.strengthLabel = new JLabel("Strength: " + String.valueOf(strength));
		this.enduranceLabel = new JLabel("Endurance: " + String.valueOf(endurance));
		this.playerLabel = new JLabel("Player: " + name);
		this.animalLabel = new JLabel("Animals killed: " + String.valueOf(0));
		this.energyLabel = new JLabel("Energy left: " + String.valueOf(energy));
		this.player = name;
		this.strength = strength;
		this.endurance = endurance;
		this.energy = energy;
		this.animalsKilled = 0;
		this.level = lvl;
		this.add(this.playerLabel);
		this.add(this.levelLabel);
		this.add(this.strengthLabel);
		this.add(this.enduranceLabel);
		this.add(this.animalLabel);
		this.add(this.energyLabel);
	}
	
	public void updateGameSettings(int newStrength, int newEndurance, int newEnergy, int animals) {
		this.strengthLabel.setText("Strength: " + String.valueOf(newStrength));
		this.enduranceLabel.setText("Endurance: " + String.valueOf(newEndurance));
		this.animalLabel.setText("Animals killed: " + String.valueOf(animals));
		this.energyLabel.setText("Energy left: " + String.valueOf(newEnergy));
		this.strength = newStrength;
		this.endurance = newEndurance;
		this.animalsKilled = animals;
		this.energy = newEnergy;
		
		/*this.add(this.strengthLabel);
		this.add(this.enduranceLabel);
		this.add(this.animalLabel);
		this.add(this.energyLabel);*/ // I don't think need to be called again?
	}
	
	public void updateStrengthLabel(int newStrength) {
		this.strengthLabel.setText("Strength: " + String.valueOf(newStrength));
		this.strength = newStrength;
		//this.add(this.strengthLabel); // I don't think need to be called again?
	}
	
	public void updateEnduranceLabel(int newEndurance) {
		this.strengthLabel.setText("Endurance: " + String.valueOf(newEndurance));
		this.strength = newEndurance;
		//this.add(this.enduranceLabel); // I don't think need to be called again?
	}

	public void updateAnimalLabel(int animals) {
		this.animalLabel.setText("Animals killed: " + String.valueOf(animals));
		this.animalsKilled = animals;
		//this.add(this.animalLabel); // I don't think need to be called again?
	}
	
	public void updateEnergyLabel(int newEnergy) {
		this.energyLabel.setText("Initial strength: " + String.valueOf(newEnergy));
		this.energy = newEnergy;
		//this.add(this.energyLabel); // I don't think need to be called again?
	}
}

