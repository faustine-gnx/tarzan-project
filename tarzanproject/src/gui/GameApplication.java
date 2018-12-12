package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class GameApplication extends JFrame {
	StartPanel start;
	GamePanel game;
	
	public GameApplication() {
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Tarzan - The Lost Adventure");
		this.start = new StartPanel();
		this.pack();
		this.setLayout(new FlowLayout()); // to change
		this.add(start);
		setVisible(true);
	}

	public static void main(String[] args) {

		GameApplication newGame = new GameApplication();

	}
}
