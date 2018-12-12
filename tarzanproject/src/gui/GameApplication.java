package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameApplication extends JFrame {
	StartPanel start;
	GamePanel game;
	public JLabel image ;
    public Container c; 
	
	public GameApplication() {
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Tarzan - The Lost Adventure");
		this.start = new StartPanel();
		this.pack();
		this.setLayout(new FlowLayout()); // to change
		this.add(start);
		
		  	c=this.getContentPane();
	        image=new JLabel(new ImageIcon("/Users/martinagalletti/Desktop/AI Leuven/First term/Basic Programming/progetto /ELIAS_GITHUB/repository3/tarzanproject/resources/textures/behang-met-een-houten-brug-door-een-groene-jungle.jpg"));
	        image.setSize(1000, 1000);

	        c.setLayout(new FlowLayout());
	        c.add(image);

	         add(image);
	
		setVisible(true);
		
		
	}   
	public static void main(String[] args) {

		GameApplication newGame = new GameApplication();

	}
}
