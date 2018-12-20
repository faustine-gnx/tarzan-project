package gui;

import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import highscores.HighScoreManager;

/**
 * @author Faustine & Martina
 * 
 *         The HighScoresPanel class handles the display of the high scores. It
 *         is possible to go back to the StartPanel from there.
 * 
 */

public class HighScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextComponent highScores;
	private HighScoreManager highScoreManager;

	/**
	 * Constructor.
	 * @throws IOException 
	 */
	public HighScorePanel() throws IOException {
		System.out.println("HS Panel constructor");
		
		highScoreManager = new HighScoreManager();
		String text = highScoreManager.getHighScoreString();
		System.out.println("High score string : "+text);
		if (text == null) {
			text = "No high scores yet";
		}/* else {
			text = highScoreManager.getHighScoreString();
		}*/

		System.out.println("High score string2 : "+text);
		highScores = new JTextArea(text);
		this.add(highScores);
		System.out.println("end HS Panel constructor");
	}
}
