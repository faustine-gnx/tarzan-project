package tilegame;

public class Level {
	public final int levelNumber;
	// public final int percentGrass; etc.
	public final int visibilitySize;
	public final int numberOfOpponents;
	public final int initialEnergy;
	public final int numberOfFlowers;
	public final int numberOfBananas;
	public final int numberOfKavurus;
	public final int numberOfHuts;
	public final int numberOfKnives;
	public final Goal goal;
	// public final int mapSize;
	
	
	Level(int lvl){
		this.levelNumber = lvl;
		
		switch(lvl) {
			case 0:
				this.visibilitySize = 10;
				goal = new Goal(1, 1, 10, 10); // animalKilled, flowerPickedUp,	fightingStrength, mobilityEndurance
				this.numberOfOpponents = 1; // 1 of each
				this.numberOfBananas = 10;
				this.numberOfFlowers = 10;
				this.numberOfHuts = 5;
				this.numberOfKavurus = 10; 
				this.numberOfKnives = 10;
				this.initialEnergy = 500;
				break;
			case 1:
				this.visibilitySize = 5;
				goal = new Goal(5, 5, 50, 50);
				this.numberOfOpponents = 2; // 2 of each
				this.numberOfBananas = 7;
				this.numberOfFlowers = 7;
				this.numberOfHuts = 3;
				this.numberOfKavurus = 7; 
				this.numberOfKnives = 7;
				this.initialEnergy = 400;
				break;
			case 2:
				this.visibilitySize = 1;
				goal = new Goal(10, 10, 100, 100);
				this.numberOfOpponents = 4; // 4 of each
				this.numberOfBananas = 5;
				this.numberOfFlowers = 5;
				this.numberOfHuts = 1;
				this.numberOfKavurus = 5; 
				this.numberOfKnives = 5;
				this.initialEnergy = 300;
				break;
			default: 
				throw new IllegalArgumentException("Unknown level");
		}
			
	}
	
	public int getLevelNumber() {
		return this.levelNumber;
	}
	
	public int getVisibilitySize()  {
		return this.visibilitySize;
	}
	
	public int getNumberOfOpponents() {
		return this.numberOfOpponents;
	}
	
	public int getNumberOfBananas() {
		return this.numberOfBananas;
	}
	
	public int getNumberOfFlowers() {
		return this.numberOfFlowers;
	}
	
	public int getNumberOfHuts() {
		return this.numberOfHuts;
	}
	
	public int getNumberOfKavurus() {
		return this.numberOfKavurus;
	}
	
	public int getNumberOfKnives() {
		return this.numberOfKnives;
	}
	public int getInitialEnergy() {
		return this.initialEnergy;
	}
	
	public Goal getGoal() {
		return this.goal;
	}
}