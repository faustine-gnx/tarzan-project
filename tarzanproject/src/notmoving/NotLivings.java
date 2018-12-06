package notmoving;
public abstract class NotLivings { // = non moving
	protected final int[][] notLivingPosition; 
	protected final String notLivingName;
	
	/*NotLivings (int[][] position) { 
		notLivingPosition = position;  
	}*/
	
	NotLivings (int[][] position, String name) {
		notLivingName = name;  
		notLivingPosition = position;  
	}
	
	public abstract int getNotLivingsType();
		// 1 for banana
		// 2 for flower
		// 3 for hut
		// 4 for Jane
		// 5 for Kavurus
		// 6 for Knife

}
	 
	 
