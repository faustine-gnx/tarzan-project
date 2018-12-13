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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameApplication extends JFrame implements ActionListener {
	JPanel allPanels;
	final static String GAME_PANEL = "Game";
	final static String START_PANEL = "Start";
	final static String SCORES_PANEL = "Scores";
	final static String RULES_PANEL = "Rules";
	public StartPanel start;
	public GamePanel game;
	public HighScoresPanel scores;
	public RulesPanel rules;
	public String player;
	private String START = "Start";
	private String SCORES = "High Scores";
	private String RULES = "Rules";

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
		this.rules = new RulesPanel();
		this.scores = new HighScoresPanel();
		// can we already create GamePanel?
		this.pack();
		this.allPanels = new JPanel(new CardLayout());
		this.allPanels.add(start, START_PANEL);
		this.allPanels.add(scores, SCORES_PANEL);
		this.allPanels.add(rules, RULES_PANEL);
		//this.allPanels.add(game, GAME_PANEL); // ? Or can we add it later, once it is created?
		this.add(allPanels);
		setVisible(true);	
		// Image must go in StartPanel --> no need for container then?
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {                 
				   GameApplication newApp = new GameApplication();
				   // call action & change listeners:
				   		// read the name of player
				   		// level
				   		// strength & endurance
				   int initialStrength = newApp.start.strengthEnduranceSlider.getValue(); // call changeListener?
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

	@Override // not working yet
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(START)) {
			String storedName = this.start.getNameField().getText();
			// --> new GamePanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(this.allPanels, "Game");
		} else if (action.equals(SCORES)) {
			// --> new HighScoresPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(this.allPanels, "Scores");
		} else if (action.equals(RULES)) {
			// --> new RulesPanel
			CardLayout cl = (CardLayout)(allPanels.getLayout());
			cl.show(this.allPanels, "Rules");
		}
	}

		
}
