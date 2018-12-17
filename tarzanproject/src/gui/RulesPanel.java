package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Game display
public class RulesPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel rules;
	
	public RulesPanel() {	
		// just text with rules
		String text = " THE RULES WILL COME HERE "; // get text from file
		this.rules = new JLabel(text);
		this.add(this.rules);
	}
}

