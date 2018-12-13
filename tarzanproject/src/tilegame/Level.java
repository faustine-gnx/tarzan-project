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


	public Level(int lvl, int sizeMap){
		this.levelNumber = lvl;

		switch(lvl) {
		case 1:
			this.visibilitySize = (int) sizeMap;
			goal = new Goal(1, 1, 10, 10); // animalKilled, flowerPickedUp,	fightingStrength, mobilityEndurance
			this.numberOfOpponents = 1; // 1 of each
			this.numberOfBananas = (int) sizeMap/2;
			this.numberOfFlowers = (int) sizeMap/2;
			this.numberOfHuts = (int) sizeMap/4;
			this.numberOfKavurus = (int) sizeMap/2; 
			this.numberOfKnives = (int) sizeMap/2;
			this.initialEnergy = 500;
			break;
		case 2:
			this.visibilitySize = sizeMap/2;
			goal = new Goal(5, 5, 50, 50);
			this.numberOfOpponents = 2; // 2 of each
			this.numberOfBananas = 7;
			this.numberOfFlowers = 7;
			this.numberOfHuts = 3;
			this.numberOfKavurus = 7; 
			this.numberOfKnives = 7;
			this.initialEnergy = 400;
			break;
		case 3:
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