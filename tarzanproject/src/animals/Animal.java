package animals;

public abstract class Animal {
	protected final Position animalPosition; // don't move for now
	protected final int animalStrength ;
	protected final String animalName ; 
	
	Animal (Position position, int strength, String name) {	
		 this.animalName = name;  
		 this.animalPosition = position; 
		 this.animalStrength = strength; 
	}
	 
	public int getAnimalStrength() {
		return this.animalStrength;
	}
	
	public Position getAnimalPosition() {
		return this.animalPosition;
	}
	
	public String getAnimalName() {
		return this.animalName;
	}
	
	public abstract int getAnimalType();
		// 11 for crocodile
		// 12 for elephant
		// 13 for lion
		// 14 for snake
		// 15 for tiger
	
	 //overriding finalize method 
	 @Override
	 public void finalize () throws Throwable { //changed to public to be called in Tarzan
		 System.out.println("Animal eliminated!");
} 
}