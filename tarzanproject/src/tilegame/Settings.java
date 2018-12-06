package tilegame;
// insert the Gui

public class Settings {
	private final int initialStrength;
	private final int initialEndurance;
	//private final int initialEnergy; // depends on level NOT A SETTING, DEPENDS ON LEVEL
	// --> INITIALIZED IN TARZAN CONSTRUCTOR
	private final int level;
	
	// Constructor with ints or setters ?
	// get these values from GUI --> how?
	
	Settings(int s, int ee, int l){ // Called when the player selects settings; in Game
		this.initialStrength = s;
		this.initialEndurance = ee;
		this.level = l;
		//this.initialEnergy = ey; // when called uselevel.getInitialEnergy() ? 
	}
	
	public int getInitialEndurance() {
		return initialEndurance;
	}
	
	public int getInitialStrength() {
		return initialStrength;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getInitialEnergy() {
		return initialEnergy;
	}
}
