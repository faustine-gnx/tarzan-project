package tarzan;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import tilegame.Position2D;

//import com.sun.tools.javac.Main;
import animals.Animal;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Hut;
import notmoving.Kavurus;
import notmoving.Knife;
import tilegame.Goal;
import tilegame.Level;
import tilegame.Settings; 

public class Tarzan { 

	// I would set everything private? since there is no subclass - true 
	// + make getters
	private final int INITIAL_ENERGY;
	protected String name; 
	protected int energy;
	protected int endurance;
	protected int level; // M: i added the protected int level if not used we can delete it later 
	protected int strength;
	protected boolean isAlive ; 
	// attribute or method depending on energy? M:--> true : but in order to make the game fails if tarzan is killed? 
	// lol the question was: is it an attribute OR is it a method? ^^
	protected int numberOfOpponentsKilled;
	protected int numberOfFlowersPicked;
	private Position2D tarzanPosition;
	protected int fieldOfViewRadius;
	protected String movingState; // walking or swimming
	// variable for the pond in swim method --> Actually I don't think we need this
	/*
	private double angle = 0; // why do we need this?
	private double speed = 0; // why do we need this?
	private double x = 0; // we should use Position
	private double y = 0; // we should use Position
	*/

	// Constructors
	// Constructor called in game:
	// lvl and setg passed as arguments are attributes of game
	public Tarzan(Position2D pos, Level lvl, Settings setg){
		this.name = "Tarzan";
		this.tarzanPosition = pos;
		this.INITIAL_ENERGY = lvl.getInitialEnergy();
		this.energy = lvl.getInitialEnergy();
		this.strength = setg.getInitialStrength();
		this.endurance = setg.getInitialEndurance();
		this.isAlive = true;
		this.numberOfOpponentsKilled = 0;
		this.numberOfFlowersPicked = 0;
		this.fieldOfViewRadius = lvl.getVisibilitySize();
		//this.animalPosition = animalPosition;
		//this.level = setg.getLevel();		
	}
	
	public Tarzan(Position2D pos){
		this.name = "Tarzan";
		this.tarzanPosition = pos;
		this.INITIAL_ENERGY = 300;
		this.energy = 300;
		this.strength = 50;
		this.endurance = 50;
		this.isAlive = true;
		this.numberOfOpponentsKilled = 0;
		this.numberOfFlowersPicked = 0;
		this.fieldOfViewRadius = 5;
		//this.animalPosition = animalPosition;
		//this.level = setg.getLevel();		
	}
	
	public Tarzan(Position2D pos, Level lvl, int s, int e){
		this.name = "Tarzan";
		this.tarzanPosition = pos;
		this.INITIAL_ENERGY = lvl.getInitialEnergy();
		this.energy = lvl.getInitialEnergy();
		this.endurance = e;
		this.strength = s;
		this.isAlive = true;
		this.numberOfOpponentsKilled = 0;
		this.numberOfFlowersPicked = 0;
		this.fieldOfViewRadius = lvl.getVisibilitySize();
		//this.animalPosition = animalPosition;
		//this.level = setg.getLevel();		
	}
	
	public Position2D getTarzanPosition() {
		return this.tarzanPosition;
	}
	
	/*public int[2] getTarzanPositionXY() { // not sure it will work
		new int[2]={this.tarzanPosition.getX(), this.tarzanPosition.getY()};
		return 
	}*/
	
	public int getFieldOfViewRadius() {
		return this.fieldOfViewRadius;
	}

	public void fight(Animal a) throws Throwable {
		//this.energy -= a.getAnimalStrength();
		takeDamage(a.getAnimalStrength());
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		Random rand = new Random();
		int winningChance = this.strength*rand.nextInt(10);
		if (winningChance > 100) {
			this.numberOfOpponentsKilled += 1;
			a.finalize();
		} else {
			// what happens if Tarzan looses ?
		}
	}	

	// I added a method dead and a method to check conditions
	// if it's dead or alive
	
	public void takeDamage(int damage) {
		//manipulate the amount of damage taken
		if(damage >= this.energy) { // F: THIS SHOULD BE ENERGY AND NOT ENDURANCE?
			energy = 0;
			isAlive = false;
			System.out.println("This is the end of the game!");
		} else {
			energy -= damage;
		}
	}

	// is showAbility for swim? if it is we can deleted --> create void move for swim  
	// I have no idea what this is either... I deleted it

	// i create a void method for swim in the pond anyway we should find a way 
	// to link with the keyboard or not? 
				// I think it does not change anything, we still move with the arrows of the keyboard, just the energy
				// decrease is larger than when walking
				// I think our map is too small to also have a forest and that we should just have water and grass,
				// no forest. We can have rock too if we implement that we cannot walk/swim on it; if it's taking too
				// much time I think we shouldn't do it right now
	// is tarzan able just to swim and get outside of the pond by itself?
				// yes? like I wrote above, swim only changes the energy lost at each time step?
	// also we should link the pond that I put in this method to the random one
	// generated in the map 
				// I don't think we need a special method for swim :( we cannot have a proper pond; just some tiles are
				// grass, and some are water. Some method in map (or game?) should determine whether Tarzan is on grass
				// or water, and this will influence the energy decrease per time step
				// --> We cannot have a round pond because our tiles are squared
				
	/*void moveSwim() {
		// If he swims at this angle, this is where he'll end up
		double newX = x + Math.cos(angle) * speed;
		double newY = y + Math.sin(angle) * speed;

		if (isInPond(newX, newY)) {
			// That's still in the pond, go there
			x = newX;
			y = newY;
		} else {
			// That's outside the pond, change direction
			angle = Math.random() * 2 * Math.PI;
		}
		// i added this method but i am not sure it is necessary for the pond
		// so i put it as a comment 
		// if it doesn't work, try to add it 
		//public String toString() {
		//return String.format(
		// "Position: %.0f,%.0f. Angle: %.0f degrees.",
		// x, y, angle * 180/Math.PI);
	}
	// Check whether some coordinates are within a circular pond with radius 100
	private boolean isInPond(double newX, double newY) {
		return Math.sqrt(x*x+y*y) < 100;
	}*/

	void eatBanana(Banana b) throws Throwable{
		this.endurance += Banana.getEnduranceGiven();
		b.finalize(); // destroy banana
	}

	void pickFlower(Flower f) throws Throwable{
		this.numberOfFlowersPicked += 1;
		f.finalize(); // destroy flower
	}

	void pickKnife(Knife k) throws Throwable{
		this.strength += Knife.getStrengthGiven();
		k.finalize(); // destroy knife
	}

	void takePill(Kavurus k) throws Throwable {
		this.energy += Kavurus.getEnergyGiven(); // no idea how much
		k.finalize(); // destroy pill
	}

	// Hut --> refill energy
	void insideHut(Hut hut) {
		this.energy = this.INITIAL_ENERGY; 
	}

	// M: should we add a method also isJaneFound = end of the game in Tarzan class?
			// I think that we could have a method findingJane that checks if the goals are met, and if they are, 
			// it ends the game, if not, it reminds the player how much opponents he still has to kill / flowers 
			// to pick / bananas to eat / knives to find

	void fieldOfView() {} // M: what is this for ? 
		// --> to know which animals / notlivings are in Tarzan's sight = to know whether to draw them or not
		// But I think it should go in Map / Game

	boolean hasReachedGoal(Goal g) {
		if ((this.numberOfOpponentsKilled >= g.getAnimalKilled()) 
				&& (this.numberOfFlowersPicked >= g.getFlowerPickedUp())
				&& (this.strength >= g.getFightingStrength())
				&& (this.endurance)>= g.getMobilityEndurance() ) {
			return true;
		} else {
			return false;
		}
	}

	/*void update() { // DO THIS IN GAME: CHECK IF TARZANPOSITION == ANIMAL/NOTLIVINGSPOSITION

		// called at each time step --> M: what does it mean? 


		// if position of animal or not living = position of tarzan
		// call fight 
		if (animalPosition == tarzanPosition);
		{
			try {
				fight(null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	

		// several methods to check the position of not livings == tarzan then method associated 

		Position kavarusPosition = null ; // why ???
		if (kavarusPosition == tarzanPosition)
			try {
				takePill (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		Position flowerPosition = null ;
		if (flowerPosition == tarzanPosition)
			try {
				pickFlower (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Position bananaPosition = null ;
		if (bananaPosition == tarzanPosition)
			try {
				eatBanana (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Position knifePosition = null ;
		if (knifePosition == tarzanPosition)
			try {
				pickKnife (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// if goals are reached
		// --> message stating goals are reached
		if (hasReachedGoal(null)== true) {
			System.out.println("The goals are reached ! Congratulations");
		}

		// decrease energy by value
		// --> value depends on mode: wailing, swimming, swinging
		// M: should we add a class mode or just made the methods inside Tarzan class? 
	}
*/
	// CREATE getTexture / get Sound? M: yes better use create I modified it 
	void createTexture(){ 
		//texture 
		try {
			URL imageUrl = new URL("https://vignette.wikia.nocookie.net/disney/images/2/2c/Tarzan_transparent.png/revision/latest?cb=20151031043816");
			InputStream in = imageUrl.openStream();
			BufferedImage image = ImageIO.read(in);
			in.close();
		} catch(IOException ioe) {
			//log the error
		}
	}

	public void run() {
	/*	try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
					Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=6O0lLgV6KpQ") ));
			clip.open(inputStream);
			clip.start(); 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
	}

	// move with keyboard  
	public void logic(long delta) {
		float dx = 0; // shouldn't it be this.
		float dy = 0;
		boolean left = false;
		if (left) {
			dx -= 1;
		}
		boolean right = false;
		if (right) {
			dx += 1;
		}
		boolean up = false;
		if (up) {
			dy -= 1;
		}
		boolean down = false;
		if (down) {
			dy += 1;
		}

		if ((dx != 0) || (dy != 0)) {
			playerMove(dx * delta * 0.003f, dy * delta * 0.003f);
		}
	}

	// listen to the player move and check if it is valid location on the map
	// F: I think this should be done in Map
	public boolean playerMove(float dx, float dy) {
		float x = 0;
		float nx = x + dx; 
		float y = 0;
		float ny = y + dy; 
		if (validLocation(nx, ny)) { 
			x = nx; y = ny; 
			return true; 
		} else { 
			return false; 
		}	
	}

	// i have a doubt with validLocation. did we say that rock are invalid location?
	// should we check if it's valid location in this case? 
	// should we create "blocked location"?
			// F: I think it's sufficient to have freePositions / isPositionFree in Map
	private boolean validLocation(float nx, float ny) {
		return true;
	}

}


