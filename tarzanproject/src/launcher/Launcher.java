package launcher;

import javax.swing.SwingUtilities;
import tilegame.Game;

/**
 * @author Faustine & Martina
 * 
 * The Launcher class only contains the main to launch and start the game.
 * 
 */

public class Launcher {	

public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {                 
				   Game newGame = new Game();
				   newGame.start();
			   }
		});
	}
}
