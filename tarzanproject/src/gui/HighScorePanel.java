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

public class HighScorePanel extends JPanel {
	private static final long serialVersionUID = 1L; /**<  added because class is serializable */
	private JLabel highScores; /**< text area with high scores (max 10) */
	private HighScoreManager highScoreManager; /**< to get scores from file */

	/**
	 * Constructor.
	 * @throws IOException 
	 */
	public HighScorePanel() throws IOException {
		highScoreManager = new HighScoreManager();
		String text = highScoreManager.getHighScoreString();
		if (text == null) {
			text = "No high scores yet"; // if file is empty
		} else {
			text =  "<html> " + text + "</html> ";
		}
		
		highScores = new JLabel(text);
		this.add(highScores);
	}
}
