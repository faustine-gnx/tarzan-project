package gui;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.text.JTextComponent;

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
	private JTextComponent highScores;
	private HighScoreManager highScoreManager;

	/**
	 * Constructor. TODO: read text from file.
	 * @throws IOException 
	 */
	public HighScoresPanel() throws IOException {
		// read text from file
		//this.setLayout(new GridLayout());
		highScoreManager = new HighScoreManager();
		String text = highScoreManager.getHighScoreString();
		highScores = new JTextArea(text);
		this.add(highScores);
	}
}
