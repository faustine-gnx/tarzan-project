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
	private JSlider skillsSlider;
	private JComboBox<Integer> levelComboBox;
	private JLabel enterName;
	private JLabel chooseLevel;
	private JLabel chooseStrengthEndurance;
	private int levelNumber = 1; // default level = 1
	
	/**
	 * Constructor. Create the labels, text field, slider, combo box of the start menu.
	 */
	public StartPanel() {	
		welcomeLabel = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!", SwingConstants.CENTER);
		enterName = new JLabel("Enter your name:");
		chooseLevel = new JLabel("Choose a level:");
		chooseStrengthEndurance = new JLabel("Choose the strength-endurance distribution:");
		nameField = new JTextField("Anonymous");
		nameField.setPreferredSize(new Dimension(10,100)); // DOES NOT WORK
		skillsSlider = new JSlider(0,100,50);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		skillsSlider.setLabelTable(labelTable);
		skillsSlider.setPaintLabels(true);
		levelComboBox = new JComboBox<Integer>(new Integer[]{1, 2, 3});

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));		
		skillsSlider.addChangeListener(this);
		levelComboBox.addItemListener(this);
				
		this.add(welcomeLabel);
		this.add(enterName);
		this.add(nameField);
		
		this.add(chooseStrengthEndurance);
		this.add(skillsSlider);
		this.add(chooseLevel);
		this.add(levelComboBox);
		
		setVisible(true);	
	}
	
	/**
	 * Getter.
	 * @return nameField
	 */
	public JTextField getNameField() {
		return this.nameField;
	}
	
	/**
	 * Getter.
	 * @return levelNumber
	 */
	public int getLevelNumber() {
		return this.levelNumber;
	}
	
	/**
	 * Getter.
	 * @return skillsSlider
	 */
	public JSlider getSkillsSlider() {
		return skillsSlider;
	}
	
	/**
	 * Called when a state is changed (slider).
	 * @param arg0
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {}
	
	/**
	 * Called when an item state is changed (level).
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		levelNumber = (Integer) levelComboBox.getSelectedItem();
	}

}

