package launcher;

import javax.swing.SwingUtilities;
import tilegame.Game;

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
