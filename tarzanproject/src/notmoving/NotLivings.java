package notmoving;

import tilegame.Position2D;

public abstract class NotLivings { // = non moving
	protected final Position2D notLivingsPosition; 
	protected final String notLivingsName;
	
	/*NotLivings (int[][] position) { 
		notLivingPosition2D = position;  
	}*/
	
	NotLivings (Position2D position, String name) {
		notLivingsName = name;  
		notLivingsPosition = position;  
	}
	
	public Position2D getNotLivingsPosition() {
		return this.notLivingsPosition;
	}
	
	public String getName() {
		return this.notLivingsName;
	}
	
	public abstract int getNotLivingsType();
		// 1 for banana
		// 2 for flower
		// 3 for hut
		// 4 for Jane
		// 5 for Kavurus
		// 6 for Knife

}
	 
	 
