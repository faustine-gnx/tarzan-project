package gui;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import imageloader.ImageLoader;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Faustine & Martina
 * 
 *         The StartPanel class handles the menu of the game. When the
 *         application is launched, this is the first panel that the player
 *         sees. The player must enter his/her name, choose the game settings
 *         (level & skills distribution). He can then start the game. The high
 *         scores and rules can also be accessed.
 * 
 */

public class StartPanel extends JPanel implements ItemListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel welcomeMessage; // variable for welcome level
	private JLabel imageLabel;
	private JTextField nameField; // variable for the text field where player need to enter their names
	private JSlider skillsSlider; // variable for the skills slider
	private JComboBox<Integer> levelComboBox; // variable for the combo box of the levels
	private JLabel enterName; // variable for the label which permits to enter the name
	private JLabel chooseLevel; // variable for the label which permits to choose level
	private JLabel chooseSkills; // variable of the label which choose the endurance
	private int levelNumber = 1; // default level = 1
	
	/**
	 * Constructor. Create the labels, text field, slider, combo box of the start
	 * menu.
	 */
	public StartPanel() {
		welcomeMessage = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!");
		welcomeMessage.setFont(new Font("TimesRoman", Font.BOLD, 32));
		BufferedImage image = ImageLoader.loadImage("/textures/tarzan_jane.jpg");
	    imageLabel = new JLabel(new ImageIcon(image), SwingConstants.CENTER);
	    
		enterName = new JLabel("Enter your name:");
		chooseLevel = new JLabel("Choose a level:");
		chooseSkills = new JLabel("Choose the strength-endurance distribution:");
		nameField = new JTextField("Anonymous");
		skillsSlider = new JSlider(0, 100, 50);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		skillsSlider.setLabelTable(labelTable);
		skillsSlider.setPaintLabels(true);
		levelComboBox = new JComboBox<Integer>(new Integer[] { 1, 2, 3 });

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		    
		skillsSlider.addChangeListener(this);
		levelComboBox.addItemListener(this);

		this.add(welcomeMessage);
		this.add(imageLabel);
		this.add(enterName);
	    enterName.setLabelFor(nameField);
	    this.add(nameField);
		this.add(chooseSkills);
		chooseSkills.setLabelFor(skillsSlider);
		this.add(skillsSlider);
		this.add(chooseLevel);
		chooseLevel.setLabelFor(levelComboBox);
		this.add(levelComboBox);

		setVisible(true);
	}
	
	/**
	 * Constructor. Create the labels, text field, slider, combo box of the start
	 * menu.
	 * @param player
	 */
	public StartPanel(String player) {
		welcomeMessage = new JLabel("Welcome to our game: Tarzan - The Lost Adventure!");
		welcomeMessage.setFont(new Font("TimesRoman", Font.BOLD, 32));
		BufferedImage image = ImageLoader.loadImage("/textures/tarzan_jane.jpg");
	    imageLabel = new JLabel(new ImageIcon(image), SwingConstants.CENTER);
	    
		enterName = new JLabel("Enter your name:");
		chooseLevel = new JLabel("Choose a level:");
		chooseSkills = new JLabel("Choose the strength-endurance distribution:");
		nameField = new JTextField(player);
		skillsSlider = new JSlider(0, 100, 50);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("Strength"));
		labelTable.put(new Integer(100), new JLabel("Endurance"));
		skillsSlider.setLabelTable(labelTable);
		skillsSlider.setPaintLabels(true);
		levelComboBox = new JComboBox<Integer>(new Integer[] { 1, 2, 3 });

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		    
		skillsSlider.addChangeListener(this);
		levelComboBox.addItemListener(this);

		this.add(welcomeMessage);
		this.add(imageLabel);
		this.add(enterName);
	    enterName.setLabelFor(nameField);
	    this.add(nameField);
		this.add(chooseSkills);
		chooseSkills.setLabelFor(skillsSlider);
		this.add(skillsSlider);
		this.add(chooseLevel);
		chooseLevel.setLabelFor(levelComboBox);
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
	public void stateChanged(ChangeEvent arg0) {
	}

	/**
	 * Called when an item state is changed (level).
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		levelNumber = (Integer) levelComboBox.getSelectedItem();
	}

}
