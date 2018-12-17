package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         Bananas can be eaten by Tarzan to increase his endurance.
 * 
 */

public class Banana extends NotMovings {

	public static final int ENDURANCE_GIVEN = 25; // variable to set the endurance given to Tarzan by eating the banana
	public Object getBananaPosition; // object to get banana position

	/**
	 * Constructor.
	 * 
	 * @param position
	 */
	public Banana(Position2D position) {
		super(position, "BANANA");
	}

	/**
	 * Interaction with Tarzan. Tarzan's endurance is increased. Banana disappears
	 * from the world.
	 * 
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.eatBanana();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Yummy yummy, banana eaten! Endurance increased");
	}
}
