package gui;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// start our game
public class GameFrame extends JFrame {

	
	public GameFrame () {	
		setPreferredSize(new Dimension(1000, 1000));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setTitle("Tarzan - The Lost Adventure");
	    pack();
	    setLayout (new FlowLayout());
	    JLabel label = new JLabel("Welcome to our game!");
	    add (label);
	    setVisible(true);
	}
}
	     
