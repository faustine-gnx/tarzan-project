package gui;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GameApplication extends JFrame {
	StartPanel start;
	GamePanel game;
	

	public GameApplication() {
		initUI();
	}   
	
	private void initUI() {
		this.setTitle("Tarzan - The Lost Adventure");
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.start = new StartPanel();
		this.pack();
		this.setLayout(new FlowLayout()); // to change
		this.add(start);
		setVisible(true);	
		// Image must go in StartPanel --> no need for container then?
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {                 
				   GameApplication newGame = new GameApplication();
				   // call action & change listeners:
				   		// read the name of player
				   		// level
				   		// strength & endurance
				   int initialStrength = newGame.start.strengthEnduranceSlider.getValue(); // call changeListener?
				   int initialEndurance = 100 - initialStrength;
				   //int level = 
				   // call action & change listeners:
				   		// if start button pressed game start --> GamePanel
				   		// if high score button pressed high scores  --> HighScoresPanel
				   		// if rules button clicked game rules --> RulesPanel
				   		// etc.
			   }
		});

	}

		
}
