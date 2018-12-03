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

}
	 
	 
