package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 * Kavuru's pills can be taken by Tarzan to increase his energy.
 * 
 */
public class Kavurus extends NotMovings {
	public static final int ENERGY_GIVEN = 50; // No idea how much
	public Object getAnimalPosition;
	public Kavurus getKavurusPosition;
	
	/**
	 * Constructor.   
	 * @param position
	 */
	public Kavurus (Position2D position) {
		super(position, "KAVURUS"); 
	}

	/**
	 * Interaction with Tarzan. 
	 * Tarzan's energy is increased.
	 * Kavurus disappears from the world.   
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.takePill();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Yeeaah, feeling better! Energy increased");	
	}
}