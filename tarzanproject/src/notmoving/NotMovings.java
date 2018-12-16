package notmoving;

import tarzan.Tarzan;

//import java.awt.Rectangle;

import tilegame.Position2D;

public abstract class NotMovings { // = non moving
	protected final Position2D notMovingsPosition; 
	protected final String notMovingsName;
	static int height;
	static int width;
	static protected int x;
	static protected int y;
 
	
	/*NotMovings (int[][] position) { 
		notLivingPosition2D = position;  
	}*/
	
	NotMovings (Position2D position, String name) {
		notMovingsName = name;  
		notMovingsPosition = position;  
	}
	
	public Position2D getNotMovingsPosition() {
		return this.notMovingsPosition;
	}
	
	public String getName() {
		return this.notMovingsName;
	}
	
	// every not movings have a rectangle to check for collision
	//public static Rectangle getBounds() {
		//return new Rectangle(x, y, width, height);
	//}
	
	public abstract int getNotMovingsType();
		// 0 for Jaguar
		// 1 for Banana
		// 4 for Jane
		// 5 for Kavurus
		// 6 for Knife
	
	public abstract void interact(Tarzan t);
	
	//public static void setVisible(boolean b) {
		
	//}

}
	 
	 
