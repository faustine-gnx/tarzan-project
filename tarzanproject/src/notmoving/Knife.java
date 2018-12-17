package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 * Knives can be found by Tarzan to increase his strength.
 * 
 */
public class Knife extends NotMovings {
	public static final int STRENGTH_GIVEN = 25; // no idea how much
	public Object getKnifePosition;
	
	/**
	 * Constructor.
	 * @param position
	 */
	public Knife (Position2D position) {
		super(position, "KNIFE"); 
	}
	
	/**
	 * Interaction with Tarzan. 
	 * Tarzan's strength is increased.
	 * Knife disappears from the world.   
	 * @param t
	 */	
	@Override
	public void interact(Tarzan t) {
		t.pickKnife();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Wooow, new weapon! Strength increased");
	}
}
