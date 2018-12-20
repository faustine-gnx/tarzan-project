package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         Bananas can be eaten by Tarzan to increase his endurance.
 * 
 */

public class Banana extends NonMoving {

	public static final int ENDURANCE_GIVEN = 25; /**< endurance given */

	/**
	 * Constructor.
	 * @param position
	 */
	public Banana(Position2D position) {
		super(position, "BANANA");
	}

	/**
	 * Interaction with Tarzan. Tarzan's endurance is increased. Banana disappears
	 * from the world.
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.eatBanana();
		t.getHandler().getHandlerWorld().setWorldNonMovingNull(nonMovingPosition);
		System.out.println("Yummy yummy, banana eaten! Endurance increased");
	}
}
