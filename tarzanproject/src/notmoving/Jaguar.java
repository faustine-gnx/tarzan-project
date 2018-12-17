package notmoving;

import java.util.Random;
import tarzan.Tarzan;
import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 * Tarzan has to kill some jaguars before meeting with Jane. 
 * Fighting with a jaguar costs energy.
 * Winning or loosing a fight is random. The chance of winning increases with Tarzan's strength.
 * 
 */

public class Jaguar extends NotMovings { // ACTUALLY JAGUAR, NOT TIGER
	public final static int JAGUAR_STRENGTH = 25;

	/**
	 * Constructor.   
	 * @param position
	 */
	public Jaguar(Position2D position) {
		super(position, "JAGUAR"); 
	}
	
	/**
	 * Interaction with Tarzan.   
	 * Tarzan's energy decreases.
	 * If fight is won by Tarzan, Tarzan's method killsJaguar is called (increments number of jaguars killed by 1) and
	 * Jaguar disappears from the world.
	 * @param t
	 */
	@Override
	public void interact(Tarzan t) {
		t.takeDamage();
		Random rand = new Random();
		int winningChance = t.getStrength()*rand.nextInt(5);
		if (winningChance > 100) {
			t.killsJaguar();
			t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		} else {
			System.out.println("Jaguar beat you, keep on moving, you might be luckier next time!");
		}
	}
}