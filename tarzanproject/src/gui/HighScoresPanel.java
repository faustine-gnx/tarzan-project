package gui;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import highscores.HighScoreManager;

/**
 * @author Faustine & Martina
 * 
 *         The HighScoresPanel class handles the display of the high scores. It
 *         is possible to go back to the StartPanel from there.
 * 
 */

public class HighScoresPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel highScores;
	private HighScoreManager highScoreManager;

	/**
	 * Constructor. TODO: read text from file.
	 * @throws IOException 
	 */
	public HighScoresPanel() throws IOException {
		// read text from file
		highScoreManager = new HighScoreManager();
		//String text = " THE HIGH SCORES WILL COME HERE "; // get text from file
		String text = highScoreManager.getHighScoreString();
		highScores = new JLabel(text);
		
		this.add(highScores);
	}
}
