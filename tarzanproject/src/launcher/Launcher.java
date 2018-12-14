package launcher;

import javax.swing.SwingUtilities;

import gui.GameApplication;
import tilegame.Game2;

public class Launcher {
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {                 
				   Game2 newGame = new Game2();
				   newGame.start();
			   }
		});

	}

}
