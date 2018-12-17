package tilegame;

import map.Map;

/**
 * @author Faustine & Martina
 * 
 * The Level class determines the number of Jaguars, Bananas, Kavurus and Knives in the Map.
 * It also determines the goal: number of Jaguars to be killed and skills to have.
 * 
 */

public class Level {
	public final int levelNumber;
	public final int numberOfJaguars;
	public final int goalStrength;
	public final int goalEndurance;
	public final int goalJaguars;
	public final int numberOfBananas;
	public final int numberOfKavurus;
	public final int numberOfKnives;

	/**
	 * Constructor. 
	 * @param level
	 */
	public Level(int level){
		levelNumber = level;
		
		switch(level) {
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

	/**
	 * Getter.
	 * @return goalStrength
	 */
	public int getGoalStrength() {
		return goalStrength;
	}

	/**
	 * Getter.
	 * @return goalEndurance
	 */
	public int getGoalEndurance() {
		return goalEndurance;
	}

	/**
	 * Getter.
	 * @return goalJaguars
	 */
	public int getGoalJaguars() {
		return goalJaguars;
	}

	/**
	 * Getter.
	 * @return levelNumber
	 */
	public int getLevelNumber() {
		return levelNumber;
	}

	/**
	 * Getter.
	 * @return numberOfJaguars
	 */
	public int getNumberOfJaguars() {
		return numberOfJaguars;
	}

	/**
	 * Getter.
	 * @return numberOfBananas
	 */
	public int getNumberOfBananas() {
		return numberOfBananas;
	}

	/**
	 * Getter.
	 * @return numberOfKavurus
	 */
	public int getNumberOfKavurus() {
		return numberOfKavurus;
	}

	/**
	 * Getter.
	 * @return numberOfKnives
	 */
	public int getNumberOfKnives() {
		return numberOfKnives;
	}
}