package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 * @author Faustine & Martina
 * 
 *         The RulesPanel class handles the display of the rules of the game. It
 *         is possible to go back to the StartPanel from there.
 * 
 */

public class RulesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextComponent rules;

	/**
	 * Constructor. TODO: write rules.
	 */
	public RulesPanel() {
		// just text with rules
		String text = " You play Tarzan and your goal is to save Jane. \n "
				+ "You start with an initial energy of 500 and the game is lost if you have no more. \n"
				+ "To increase your energy, you can take Kavuru's pills. \n"
				+ "Wlaking on grass costs you less energy than swimming. \n"
				+ "Eating bananas increases your endurance, so decreases your energy loss per move. \n"
				+ "Fighting jaguars costs you energy, but you need to have fought some before saving Jane. \n"
				+ "Winning a fight is somewhat random, but the chances increase if your stronger. \n"
				+ "Picking up knives increase your strength. \n"
				+ "Once your are sufficiently strong and endurant, and that you have killed enough jaguars, \n"
				+ "you can go find Jane and win the game. \n \n "
				+ "Good luck! \n"; // get text from file?
		rules = new JTextArea(text);
		this.add(rules);
	}
}
