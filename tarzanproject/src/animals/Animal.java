package animals;
import sun.applet.Main;

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
	
	public String getAnimalName() {
		return this.animalName;
	}
	
	 //overriding finalize method 
	 @Override
	 protected void finalize () throws Throwable { 
		 System.out.println("Animal eliminated!");
} 
}