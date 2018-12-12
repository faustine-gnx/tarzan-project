package tilegame;

import javax.swing.JFrame;

import gui.GamePanel;

public class Launcher {
	public static void main(String[] args) {
		//call constructor and variable 
		
		Game game = new Game();
		// start game
		game.start(); 
		
		GamePanel frame = new GamePanel();
        frame.setVisible(true);
	}
	
}

