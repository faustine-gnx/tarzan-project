package tilegame;

public class Level {
	public final int levelNumber;
	// public final int percentGrass; etc.
	public final int visibilitySize;
	public final int numberOfOpponents;
	public final int initialEnergy;
	public final Goal goal;
	// public final int mapSize;
	
	
	Level(int lvl){
		this.levelNumber = lvl;
		
		switch(lvl) {
			case 0:
				this.visibilitySize = 10;
				goal = new Goal(1, 1, 10, 10); // animalKilled, flowerPickedUp,	fightingStrength, mobilityEndurance
				this.numberOfOpponents = 1; // 1 of each
				this.initialEnergy = 500;
				break;
			case 1:
				this.visibilitySize = 5;
				goal = new Goal(5, 5, 50, 50);
				this.numberOfOpponents = 2; // 5 of each
				this.initialEnergy = 400;
				break;
			case 2:
				this.visibilitySize = 1;
				goal = new Goal(10, 10, 100, 100);
				this.numberOfOpponents = 4; // 10 of each
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
	
	public int getInitialEnergy() {
		return this.initialEnergy;
	}
	
	public Goal getGoal() {
		return this.goal;
	}
}