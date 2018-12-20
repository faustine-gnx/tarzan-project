package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         Kavuru's pills can be taken by Tarzan to increase his energy.
 * 
 */
public class Kavurus extends NonMoving {
	public static final int ENERGY_GIVEN = 50; /**< energy given */

	/**
	 * Constructor.
	 * @param position
	 */
	public Kavurus(Position2D position) {
		super(position, "KAVURUS");
	}

	/**
	 * Interaction with Tarzan. Tarzan's energy is increased. Kavurus disappears
	 * from the world.
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.takePill();
		t.getHandler().getHandlerWorld().setWorldNonMovingNull(nonMovingPosition);
		System.out.println("Yeeaah, feeling better! Energy increased");
	}
}