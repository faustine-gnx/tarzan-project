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

	private static final long serialVersionUID = 1L; // added because class is serializable
	private JLabel welcomeMessage; // for welcome level
	private JLabel imageLabel; // for Tarzan and Jane picture
	private JLabel chooseSkills; // label before skills slider
	private JSlider skillsSlider; // skills slider to choose strength-endurance repartition
	private JLabel chooseLevel; // label before combobox
	private JComboBox<Integer> levelComboBox; // combo box of the levels: 1, 2 or 3
	private JLabel enterName; // label before textField
	private JTextField nameField; // text field where players can enter their names
	private int levelNumber = 1; // default level = 1

	/**
	 * Constructor. Create the labels, text field, slider, combo box of the start menu.
	 * Used with the name of the previous player or "Anonymous" if first game.
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
	 * @param e
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
	}

	/**
	 * Called when an item state is changed (level).
	 * @param e
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		levelNumber = (Integer) levelComboBox.getSelectedItem();
	}

}
