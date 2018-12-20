package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Faustine & Martina
 * 
 *         The RulesPanel class handles the display of the rules of the game. It
 *         is possible to go back to the StartPanel from there.
 * 
 */

public class RulesPanel extends JPanel {
	private static final long serialVersionUID = 1L; /**< added because class is serializable */
	private final JLabel rules; /**< text area for the rules*/

	/**
	 * Constructor. 
	 */
	public RulesPanel() {
		// just text with rules
		String text = "<html>You play Tarzan and your goal is to save Jane. <br> "
				+ "You start with an initial energy of 500 and the game is lost if you have no more. <br>"
				+ "To increase your energy, you can take Kavuru's pills. <br>"
				+ "Wlaking on grass costs you less energy than swimming. <br>"
				+ "Eating bananas increases your endurance, so decreases your energy loss per move. <br>"
				+ "Fighting jaguars costs you energy, but you need to have beaten some before saving Jane. <br>"
				+ "Winning a fight is somewhat random, but the chances increase if your stronger. <br>"
				+ "Picking up knives increases your strength. <br>"
				+ "Once your are sufficiently strong, have a high endurance, and have killed enough jaguars, <br>"
				+ "you can go find Jane and win the game. <br> <br> "
				+ "Good luck! </html>";
		rules = new JLabel(text);
		this.add(rules);
	}
}
