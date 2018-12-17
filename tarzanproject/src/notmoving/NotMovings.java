package notmoving;

import tarzan.Tarzan;

//import java.awt.Rectangle;

import tilegame.Position2D;

/**
 * @author Faustine & Martina
 * 
 * The NotMovings class is an abstract class for all entities with a position and that don't move.
 * 
 */
public abstract class NotMovings { // = non moving
	protected final Position2D notMovingsPosition; 
	protected final String notMovingsName;
	static int height;
	static int width;
	static protected int x;
	static protected int y;
	
	/**
	 * Constructor. 
	 * @param position, name
	 */
	NotMovings (Position2D position, String name) {
		notMovingsName = name;  
		notMovingsPosition = position;  
	}
	
	/**
	 * Getter.
	 * @return notMovingsPosition
	 */
	public Position2D getNotMovingsPosition() {
		return notMovingsPosition;
	}
	
	/**
	 * Getter.
	 * @return notMovingsName
	 */
	public String getName() {
		return notMovingsName;
	}
	
	/**
	 * Abstract method for interaction with Tarzan. Defined in subclasses.   
	 * @param t
	 */
	public abstract void interact(Tarzan t);

}
	 
	 
