package notmoving;

import tarzan.Tarzan;

//import java.awt.Rectangle;

import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 *         The NonMovings class is an abstract class for all entities with a
 *         position and that don't move.
 * 
 */
public abstract class NonMoving {
	protected final Position2D nonMovingPosition; /**< position in 2D */
	protected final String nonMovingName; /**< name */

	/**
	 * Constructor.
	 * @param position, name
	 */
	NonMoving(Position2D position, String name) {
		nonMovingName = name;
		nonMovingPosition = position;
	}

	/**
	 * Getter.
	 * @return nonMovingsPosition
	 */
	public Position2D getNonMovingPosition() {
		return nonMovingPosition;
	}

	/**
	 * Getter.
	 * @return nonMovingsName
	 */
	public String getName() {
		return nonMovingName;
	}

	/**
	 * Abstract method for interaction with Tarzan. Defined in subclasses.
	 * @param t
	 */
	public abstract void interact(Tarzan t);
}
