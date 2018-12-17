package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Game display
public class HighScoresPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel highScores;
	
	public HighScoresPanel() {	
		// read text from file
		String text = " THE HIGH SCORES WILL COME HERE "; // get text from file
		this.highScores = new JLabel(text);
		this.add(this.highScores);
	}
}

