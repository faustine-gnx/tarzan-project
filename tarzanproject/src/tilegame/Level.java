package tilegame;

public class Level {
	public final int levelNumber;
	public final int visibilitySize;
	public final int numberOfOpponents;
	public final int initialEnergy;
	public final int numberOfBananas;
	public final int numberOfKavurus;
	public final int numberOfKnives;
	public final Goal goal;
	// public final int mapSize;


	public Level(int lvl, int sizeMap){
		levelNumber = lvl;
		initialEnergy = 500 - (lvl-1)*200; // Level 1 : 500; 2: 300; 3: 100
		
		switch(lvl) {
		case 1:
			visibilitySize = (int) sizeMap;
			goal = new Goal(1, 1, 50, 50); // animalKilled, flowerPickedUp,	fightingStrength, mobilityEndurance
			numberOfOpponents = 4; // 1 to have of each; for now 5 tigers
			numberOfBananas = (int) sizeMap/2;
			numberOfKavurus = (int) sizeMap/2; 
			numberOfKnives = (int) sizeMap/2;
			break;
		case 2:
			visibilitySize = sizeMap/2;
			goal = new Goal(sizeMap/2, sizeMap/2, 100, 100);
			numberOfOpponents = 8; // 1 to have of each; for now 5 tigers
			numberOfBananas = (int) sizeMap/2;
			numberOfKavurus = (int) sizeMap/4; 
			numberOfKnives = (int) sizeMap/2;
			break;
		case 3:
			visibilitySize = 1;
			goal = new Goal(sizeMap, sizeMap, 150, 150);
			numberOfOpponents = 16; // 1 to have of each; for now 5 tigers
			numberOfBananas = (int) sizeMap/2;
			numberOfKavurus = (int) sizeMap/8; 
			numberOfKnives = (int) sizeMap/2;
			break;
		default: 
			throw new IllegalArgumentException("Unknown level");
		}

	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public int getVisibilitySize()  {
		return visibilitySize;
	}

	public int getNumberOfOpponents() {
		return numberOfOpponents;
	}

	public int getNumberOfBananas() {
		return numberOfBananas;
	}

	public int getNumberOfKavurus() {
		return numberOfKavurus;
	}

	public int getNumberOfKnives() {
		return numberOfKnives;
	}
	
	public int getInitialEnergy() {
		return initialEnergy;
	}

	public Goal getGoal() {
		return goal;
	}
}