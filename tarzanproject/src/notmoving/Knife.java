package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         Knives can be found by Tarzan to increase his strength.
 * 
 */
public class Knife extends NonMoving {
	public static final int STRENGTH_GIVEN = 25; /**< strength given */
	
	/**
	 * Constructor.
	 * @param position
	 */
	public Knife(Position2D position) {
		super(position, "KNIFE");
	}

	/**
	 * Interaction with Tarzan. Tarzan's strength is increased. Knife disappears
	 * from the world.
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.pickKnife();
		t.getHandler().getHandlerWorld().setWorldNonMovingNull(nonMovingPosition);
		System.out.println("Wooow, new weapon! Strength increased");
	}
}
