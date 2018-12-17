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
	private static final long serialVersionUID = 1L;
	private JLabel rules;

	/**
	 * Constructor. TODO: write rules.
	 */
	public RulesPanel() {
		// just text with rules
		String text = " THE RULES WILL COME HERE "; // get text from file
		rules = new JLabel(text);
		this.add(rules);
	}
}
