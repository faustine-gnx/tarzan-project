package tilegame;

import map.Map;

/**
 * @author Faustine & Martina
 * 
 *         The Level class determines the number of Jaguars, Bananas, Kavurus
 *         and Knives in the Map. It also determines the goal: number of Jaguars
 *         to be killed and skills to have.
 * 
 */

public class Level {
	public final int levelNumber; /**< level: 1, 2 or 3 */
	public final int numberOfJaguars; /**< number of jaguars to create */
	public final int numberOfBananas; /**< number of bananas to create */
	public final int numberOfKavurus; /**< number of kavurus to create */
	public final int numberOfKnives; /**< number of knives to create */
	public final int goalStrength; /**< strength that must be reached to win game */
	public final int goalEndurance; /**< endurance that must be reached to win game */
	public final int goalJaguars; /**< jaguars that must be killed to win game */

	/**
	 * Constructor.
	 * @param level
	 */
	public Level(int level) {
		levelNumber = level;

		switch (level) {
		case 1:
			goalJaguars = 1;
			goalStrength = 75;
			goalEndurance = 75;
			numberOfJaguars = (int) Map.SIZE_MAP / 4;
			numberOfBananas = (int) Map.SIZE_MAP / 2;
			numberOfKavurus = (int) Map.SIZE_MAP / 2;
			numberOfKnives = (int) Map.SIZE_MAP / 2;
			break;
		case 2:
			goalJaguars = (int) Map.SIZE_MAP / 8;
			goalStrength = 100;
			goalEndurance = 100;
			numberOfJaguars = (int) Map.SIZE_MAP / 2;
			numberOfBananas = (int) Map.SIZE_MAP / 2;
			numberOfKavurus = (int) Map.SIZE_MAP / 2;
			numberOfKnives = (int) Map.SIZE_MAP / 2;
			break;
		case 3:
			goalJaguars = (int) Map.SIZE_MAP / 4;
			goalStrength = 150;
			goalEndurance = 150;
			numberOfJaguars = (int) Map.SIZE_MAP;
			numberOfBananas = (int) Map.SIZE_MAP / 2;
			numberOfKavurus = (int) Map.SIZE_MAP / 4;
			numberOfKnives = (int) Map.SIZE_MAP / 2;
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