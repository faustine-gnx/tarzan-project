package notmoving;

import tilegame.Position2D;

public abstract class NotMovings { // = non moving
	protected final Position2D notMovingsPosition; 
	protected final String notMovingsName;
	
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
	
	public abstract int getNotMovingsType();
		// 0 for Jaguar
		// 1 for Banana
		// 4 for Jane
		// 5 for Kavurus
		// 6 for Knife

}
	 
	 
