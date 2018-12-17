package tilegame;

import map.Map;

public class Level {
	public final int levelNumber;
	public final int numberOfJaguars;
	public final int goalStrength;
	public final int goalEndurance;
	public final int goalJaguars;
	public final int initialEnergy;
	public final int numberOfBananas;
	public final int numberOfKavurus;
	public final int numberOfKnives;

	public Level(int lvl){
		levelNumber = lvl;
		initialEnergy = 500; // Level 1 : 500; 2: 400; 3: 300
		
		switch(lvl) {
		case 1:
			goalJaguars = 1;
			goalStrength = 75;
			goalEndurance = 75;
			numberOfJaguars = (int) Map.SIZE_MAP/4;
			numberOfBananas = (int) Map.SIZE_MAP/2;
			numberOfKavurus = (int) Map.SIZE_MAP/2; 
			numberOfKnives = (int) Map.SIZE_MAP/2;
			break;
		case 2:
			goalJaguars = (int) Map.SIZE_MAP/8;
			goalStrength = 100;
			goalEndurance = 100;
			numberOfJaguars = (int) Map.SIZE_MAP/2;
			numberOfBananas = (int) Map.SIZE_MAP/2;
			numberOfKavurus = (int) Map.SIZE_MAP/2; 
			numberOfKnives = (int) Map.SIZE_MAP/2;
			break;
		case 3:
			goalJaguars = (int) Map.SIZE_MAP/4;
			goalStrength = 150;
			goalEndurance = 150;
			numberOfJaguars = (int) Map.SIZE_MAP; 
			numberOfBananas = (int) Map.SIZE_MAP/2;
			numberOfKavurus = (int) Map.SIZE_MAP/4; 
			numberOfKnives = (int) Map.SIZE_MAP/2;
			break;
		default: 
			throw new IllegalArgumentException("Unknown level");
		}

	}

	public int getGoalStrength() {
		return goalStrength;
	}

	public int getGoalEndurance() {
		return goalEndurance;
	}

	public int getGoalJaguars() {
		return goalJaguars;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public int getNumberOfJaguars() {
		return numberOfJaguars;
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
}