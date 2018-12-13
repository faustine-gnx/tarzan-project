package gui;
import java.awt.BorderLayout;
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
	String player;
	int initialStrength;
	int initialEndurance;
	int level;
	
	protected Map map; 
	
	public GamePanel(String name, int strength, int endurance, int lvl) {	
		this.levelLabel = new JLabel("Current level: " + String.valueOf(lvl));
		this.strengthLabel = new JLabel("Initial strength: " + String.valueOf(strength));
		this.enduranceLabel = new JLabel("Initial endurance: " + String.valueOf(endurance));
		this.playerLabel = new JLabel("Player: " + name);
		this.player = name;
		this.initialStrength = strength;
		this.initialEndurance = endurance;
		this.level = lvl;
		this.add(this.playerLabel);
		this.add(this.levelLabel);
		this.add(this.strengthLabel);
		this.add(this.enduranceLabel);
		//setVisible(true);
		this.map = new Map(strength, endurance, lvl);
		Graphics2D g = this.map.getMapDrawing();
		this.paintComponent(g);
	}
}

