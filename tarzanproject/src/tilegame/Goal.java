package tilegame;

public class Goal {
	public final int animalKilled;
	public final int flowerPickedUp;
	public final int fightingStrength;
	public final int mobilityEndurance;
	
	Goal(int aK, int fPU, int fS, int mE){
		this.animalKilled = aK;
		this.flowerPickedUp = fPU;
		this.fightingStrength = fS;
		this.mobilityEndurance = mE;
	}
	
	public int getAnimalKilled() {
		return this.animalKilled;
	}
	
	public int getFlowerPickedUp() {
		return this.flowerPickedUp;
	}
	
	public int getFightingStrength() {
		return this.fightingStrength;
	}
	
	public int getMobilityEndurance() {
		return this.mobilityEndurance;
	}

}