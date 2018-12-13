package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

// Game display
public class RulesPanel extends JPanel {
	JLabel rules;
	
	public RulesPanel() {	
		// just text with rules
		String text = " THE RULES WILL COME HERE "; // get text from file
		this.rules = new JLabel(text);
		this.add(this.rules);
	}
}

