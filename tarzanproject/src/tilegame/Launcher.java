package tilegame;

import javax.swing.JFrame;

import gui.GameFrame;

public class Launcher {
	public static void main(String[] args) {
		//call constructor and variable 
		
		Game game = new Game();
		// start game
		game.start(); 
		
		GameFrame frame = new GameFrame();
        frame.setVisible(true);
	}
	
}

