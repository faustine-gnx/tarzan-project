package notmoving;

import java.io.IOException;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         Jane must be found by Tarzan to win the game.
 * 
 */
public class Jane extends NotMovings {

	/**
	 * Constructor.
	 * @param position
	 */
	public Jane(Position2D position) {
		super(position, "JANE");
	}

	/**
	 * Interaction with Tarzan. May end the game.
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		try {
			t.janeFound();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}