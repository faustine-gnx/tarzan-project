package gui;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Faustine & Martina
 * 
 * The StartPanel class handles the menu of the game.
 * When the application is launched, this is the first panel that the player sees.
 * The player must enter his/her name, choose the game settings (level & skills distribution).
 * He can then start the game.
 * The high scores and rules can also be accessed.
 * 
 */

public class StartPanel extends JPanel implements ItemListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel welcomeLabel; 
	private JTextField nameField;
	private JSlider strengthEnduranceSlider;
	private JComboBox<Integer> levelComboBox;
	private JLabel enterName;
	private JLabel chooseLevel;
	private JLabel chooseStrengthEndurance;
	private int levelNumber = 1; // default level = 1
	
	// Start panel
	public StartPanel() {	
		this.welcomeLabel = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!", SwingConstants.CENTER);
		this.enterName = new JLabel("Enter your name:");
		this.chooseLevel = new JLabel("Choose a level:");
		this.chooseStrengthEndurance = new JLabel("Choose the strength-endurance distribution:");
		this.nameField = new JTextField("Anonymous");
		this.nameField.setPreferredSize(new Dimension(10,100)); // DOES NOT WORK
		this.strengthEnduranceSlider = new JSlider(0,100,50);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		this.strengthEnduranceSlider.setLabelTable(labelTable);
		this.strengthEnduranceSlider.setPaintLabels(true);
		this.levelComboBox = new JComboBox<Integer>(new Integer[]{1, 2, 3});

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));		
		this.strengthEnduranceSlider.addChangeListener(this);
		this.levelComboBox.addItemListener(this);
				
		this.add(this.welcomeLabel);
		this.add(this.enterName);
		this.add(this.nameField);
		
		this.add(this.chooseStrengthEndurance);
		this.add(this.strengthEnduranceSlider);
		this.add(this.chooseLevel);
		this.add(this.levelComboBox);
		
		setVisible(true);	
	}
	
	public JTextField getNameField() {
		return this.nameField;
	}
	
	public int getLevelNumber() {
		return this.levelNumber;
	}

	public JSlider getStrengthEnduranceSlider() {
		return strengthEnduranceSlider;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		this.levelNumber = (Integer) this.levelComboBox.getSelectedItem();
	}

}

