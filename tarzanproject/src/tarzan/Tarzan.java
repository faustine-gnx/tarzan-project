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
import tilegame.Game2;
import tilegame.Goal;
import tilegame.Level;
import tilegame.Settings; 

public class Tarzan { 
	// I would set everything private? since there is no subclass - true 
	// + make getters
	private final static int SPEED = 1;
	private final int INITIAL_ENERGY;
	private String name; 
	private int energy;
	private int endurance;
	private int level; // M: i added the protected int level if not used we can delete it later 
	private int strength;
	private boolean isAlive ; 
	// attribute or method depending on energy? M:--> true : but in order to make the game fails if tarzan is killed? 
	// lol the question was: is it an attribute OR is it a method? ^^
	private int animalsKilled;
	private int numberOfFlowersPicked;
	private Position2D tarzanPosition;
	private int fieldOfViewRadius;
	private String movingState; // walking or swimming
	private Game2 game;
	private int speed;
	private int xMove;
	private int yMove;

	// Constructors
	// Constructor called in game:
	// lvl and setg passed as arguments are attributes of game

	public Tarzan(Position2D pos, Game2 game){
		name = "Tarzan";
		this.game = game;
		tarzanPosition = pos;
		INITIAL_ENERGY = 300;
		energy = 300;
		strength = 50;
		endurance = 50;
		isAlive = true;
		animalsKilled = 0;
		numberOfFlowersPicked = 0;
		fieldOfViewRadius = 5;
		speed = SPEED;
		//animalPosition = animalPosition;
		//level = setg.getLevel();		
	}

	public Tarzan(Position2D pos, Level lvl, int strength, int endurance){
		name = "Tarzan";
		tarzanPosition = pos;
		INITIAL_ENERGY = lvl.getInitialEnergy();
		energy = lvl.getInitialEnergy();
		this.endurance = endurance;
		this.strength = strength;
		isAlive = true;
		animalsKilled = 0;
		numberOfFlowersPicked = 0;
		fieldOfViewRadius = lvl.getVisibilitySize();
		//animalPosition = animalPosition;
		//level = setg.getLevel();		
	}

	public Position2D getTarzanPosition() {
		return tarzanPosition;
	}

	public void setTarzanPosition(Position2D pos) {
		tarzanPosition = pos;
	}
	
	public void setTarzanPosition(int x, int y) {
		tarzanPosition.set(x,y);
	}

	// getters and setters for energy, strength, endurance, animalsKilled

	/*public int[2] getTarzanPositionXY() { // not sure it will work
		new int[2]={this.tarzanPosition.getX(), this.tarzanPosition.getY()};
		return 
	}*/

	public int getFieldOfViewRadius() {
		return fieldOfViewRadius;
	}

	public void fight(Animal a) throws Throwable {
		//this.energy -= a.getAnimalStrength();
		takeDamage(a.getAnimalStrength());
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		Random rand = new Random();
		int winningChance = strength*rand.nextInt(10);
		if (winningChance > 100) {
			animalsKilled += 1;
			a.finalize();
		} else {
			// what happens if Tarzan looses ?
		}
	}	

	// I added a method dead and a method to check conditions
	// if it's dead or alive

	public void takeDamage(int damage) {
		//manipulate the amount of damage taken
		if(damage >= energy) { // F: THIS SHOULD BE ENERGY AND NOT ENDURANCE?
			energy = 0;
			isAlive = false;
			System.out.println("This is the end of the game!");
		} else {
			energy -= damage;
		}
	}
	
	public void move() {
		setTarzanPosition(tarzanPosition.getX() + xMove, tarzanPosition.getY() + yMove);
		//xMove = 0;
		//yMove = 0;
	}
	
	private void getMoves() { //call with this in Game2
		xMove = 0;
		yMove = 0;
		if (game.getKeyManager().up) {
			yMove = -speed;
		}
		if (game.getKeyManager().down) {
			yMove = speed;
			System.out.println("move down");
			System.out.println(tarzanPosition.getY());
		}
		if (game.getKeyManager().left) {
			xMove = -speed;
		}
		if (game.getKeyManager().right) {
			xMove = speed;
		}
	}
	
	public void tick() {
		getMoves();
		move();
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
		endurance += Banana.getEnduranceGiven();
		b.finalize(); // destroy banana
	}

	void pickFlower(Flower f) throws Throwable{
		numberOfFlowersPicked += 1;
		f.finalize(); // destroy flower
	}

	void pickKnife(Knife k) throws Throwable{
		strength += Knife.getStrengthGiven();
		k.finalize(); // destroy knife
	}

	void takePill(Kavurus k) throws Throwable {
		energy += Kavurus.getEnergyGiven(); // no idea how much
		k.finalize(); // destroy pill
	}

	// Hut --> refill energy
	void insideHut(Hut hut) {
		energy = INITIAL_ENERGY; 
	}

	// M: should we add a method also isJaneFound = end of the game in Tarzan class?
	// I think that we could have a method findingJane that checks if the goals are met, and if they are, 
	// it ends the game, if not, it reminds the player how much opponents he still has to kill / flowers 
	// to pick / bananas to eat / knives to find

	void fieldOfView() {} // M: what is this for ? 
	// --> to know which animals / notlivings are in Tarzan's sight = to know whether to draw them or not
	// But I think it should go in Map / Game

	boolean hasReachedGoal(Goal g) {
		if ((animalsKilled >= g.getAnimalKilled()) 
				//&& (this.numberOfFlowersPicked >= g.getFlowerPickedUp())
				&& (strength >= g.getFightingStrength())
				&& (endurance)>= g.getMobilityEndurance() ) {
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

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAnimalsKilled() {
		return animalsKilled;
	}

	public void setAnimalsKilled(int animalsKilled) {
		this.animalsKilled = animalsKilled;
	}

	public String getMovingState() {
		return movingState;
	}

	public void setMovingState(String movingState) {
		this.movingState = movingState;
	}

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}


}


