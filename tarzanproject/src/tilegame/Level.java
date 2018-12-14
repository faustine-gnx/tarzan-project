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
		this.initialEnergy = 500 - (lvl-1)*200; // Level 1 : 500; 2: 300; 3: 100
		
		switch(lvl) {
		case 1:
			this.visibilitySize = (int) sizeMap;
			goal = new Goal(1, 1, 50, 50); // animalKilled, flowerPickedUp,	fightingStrength, mobilityEndurance
			this.numberOfOpponents = 4; // 1 to have of each; for now 5 tigers
			this.numberOfBananas = (int) sizeMap/2;
			this.numberOfFlowers = (int) sizeMap/2;
			this.numberOfHuts = (int) sizeMap/4;
			this.numberOfKavurus = (int) sizeMap/2; 
			this.numberOfKnives = (int) sizeMap/2;
			break;
		case 2:
			this.visibilitySize = sizeMap/2;
			goal = new Goal(sizeMap/2, sizeMap/2, 100, 100);
			this.numberOfOpponents = 8; // 1 to have of each; for now 5 tigers
			this.numberOfBananas = (int) sizeMap/2;
			this.numberOfFlowers = (int) sizeMap/2;
			this.numberOfHuts = (int) sizeMap/8;
			this.numberOfKavurus = (int) sizeMap/4; 
			this.numberOfKnives = (int) sizeMap/2;
			break;
		case 3:
			this.visibilitySize = 1;
			goal = new Goal(sizeMap, sizeMap, 150, 150);
			this.numberOfOpponents = 16; // 1 to have of each; for now 5 tigers
			this.numberOfBananas = (int) sizeMap/2;
			this.numberOfFlowers = (int) sizeMap/2;
			this.numberOfHuts = (int) sizeMap/8;
			this.numberOfKavurus = (int) sizeMap/8; 
			this.numberOfKnives = (int) sizeMap/2;
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