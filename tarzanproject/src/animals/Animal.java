package animals;

public abstract class Animal {
	protected final int[][] animalPosition; // don't move for now
	protected final int animalStrength ;
	protected final String animalName ; 
	
	Animal (int[][] position, int strength, String name) {	
		 this.animalName = name;  
		 this.animalPosition = position; 
		 this.animalStrength = strength; 
	}
	 
	public int getAnimalStrength() {
		return this.animalStrength;
	}
	
	public int[][] getAnimalPosition() {
		return this.animalPosition;
	}
	
	public String getAnimalName() {
		return this.animalName;
	}
	
	 //overriding finalize method 
	 @Override
	 public void finalize () throws Throwable { //changed to public to be called in Tarzan
		 System.out.println("Animal eliminated!");
} 
}