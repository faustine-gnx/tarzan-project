package tarzan;
import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import gui.GameApplication;
import tilegame.Position2D;

import map.Map;
import notmoving.*;
import tilegame.Goal;
import tilegame.Handler;
import tilegame.Level;

public class Tarzan implements KeyListener { 
	// I would set everything private? since there is no subclass - true 
	// + make getters
	private final static int SPEED = 1;
	private final static int ENERGY_LOSS = 1;
	private final static int WATER_ENERGY_LOSS = 9; // + ENERGY_LOSS = 10
	private String name; 
	private int energy;
	private int endurance;
	private int level; // M: i added the protected int level if not used we can delete it later 
	private int strength;
	private boolean isAlive ; 
	// attribute or method depending on energy? M:--> true : but in order to make the game fails if tarzan is killed? 
	// lol the question was: is it an attribute OR is it a method? ^^
	private int animalsKilled;
	private Position2D tarzanPosition;
	private int fieldOfViewRadius;
	private Handler handler;
	private int speed;
	//protected int width;
	//protected int height; 
	
	private boolean[] keys;
	public boolean up; // w
	public boolean down; // s
	public boolean left; // a
	public boolean right; // d
	public boolean aUp; // arrows
	public boolean aDown;
	public boolean aLeft;
	public boolean aRight;
	
	// rectangle to check for collision
	protected Rectangle bounds; 
	
	// Constructors
	// Constructor called in game:
	// lvl and setg passed as arguments are attributes of game

	public Tarzan(Position2D pos, Handler handler, Level level, int strength, int endurance){
		name = "Tarzan";
		this.handler = handler;
		tarzanPosition = pos;
		energy = level.getInitialEnergy();
		this.strength = strength;
		this.endurance = endurance;
		isAlive = true;
		animalsKilled = 0;
		fieldOfViewRadius = 5;
		speed = SPEED;
		fieldOfViewRadius = level.getVisibilitySize();
		keys = new boolean[256];
		
		//bounds of Tarzan for collision
		//bounds.x = 16;
		//bounds.y = 32; 
		//bounds.width = 32; 
		//bounds.height= 32; 
	}
	// do we need specific collision for each not movings or just one for all of them?
	// i think yes since if collision true --> then energy/strenght different in base of animals, banan
	//check collision method rectangle Tarzan and notmovings
		//public void checkCollisions() {

		    //bounds = NotMovings.getBounds();

		        //if (r3.intersects(r2)) {
		            // not movings not visible any more
		           //NotMovings.setVisible(false);
		            // energy/strenght increased -->specific for each objects?
		            
		       // }
		    //}
		
	public Position2D getTarzanPosition() {
		return tarzanPosition;
	}

	public void setTarzanPosition(Position2D pos) {
		tarzanPosition = pos;
	}
	
	public void setTarzanPosition(int x, int y) {
		if (x > Map.SIZE_MAP) {
			x = Map.SIZE_MAP-1;
		}
		
		if (x < 0) {
			x = 0;
		}
		
		if (y > Map.SIZE_MAP) {
			y = Map.SIZE_MAP-1;
		}
		
		if (y < 0) {
			y = 0;
		}
		
		tarzanPosition.set(x,y);
	}

	public int getFieldOfViewRadius() {
		return fieldOfViewRadius;
	}

			
	public void fight(Jaguar j) throws Throwable {
		
		//this.energy -= a.getAnimalStrength();
		takeDamage(j.getJaguarStrength());
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		Random rand = new Random();
		int winningChance = strength*rand.nextInt(10);
		if (winningChance > 100) {
			animalsKilled += 1;
			j.finalize();
			handler.getHandlerWorld().getTile(j.getNotMovingsPosition().getX(), j.getNotMovingsPosition().getY()).setHasNotMovings(null);
		} else {
			// what happens if Tarzan looses ?
		}
	}	
	
	private boolean inTheWater() { 
		return (handler.getHandlerWorld().getWorldTiles()[tarzanPosition.getY()][tarzanPosition.getX()] == 1);
	}

	public void takeDamage(int damage) {
		//manipulate the amount of damage taken
		if(damage >= energy) { 
			energy = 0;
			isAlive = false;
			System.out.println("This is the end of the game!");
		} else {
			energy -= damage;
		}
	}
	
	public void tick() {
		//if(handler.getHandlerGame().getGameApp().isGamePlaying()) {
			handler.getHandlerGame().getGameApp().getGamePanel().updateGameSettings(strength, endurance, energy, animalsKilled);
		//}
	}

	void eatBanana(Banana b) throws Throwable{
		endurance += Banana.getEnduranceGiven();
		b.finalize(); // destroy banana
		handler.getHandlerWorld().getTile(b.getNotMovingsPosition().getX(), b.getNotMovingsPosition().getY()).setHasNotMovings(null);
	}

	void pickKnife(Knife k) throws Throwable{
		strength += Knife.getStrengthGiven();
		k.finalize(); // destroy knife
		handler.getHandlerWorld().getTile(k.getNotMovingsPosition().getX(), k.getNotMovingsPosition().getY()).setHasNotMovings(null);
	}

	void takePill(Kavurus k) throws Throwable {
		energy += Kavurus.getEnergyGiven(); // no idea how much
		k.finalize(); // destroy pill
		handler.getHandlerWorld().getTile(k.getNotMovingsPosition().getX(), k.getNotMovingsPosition().getY()).setHasNotMovings(null);
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
		
		if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]){
	        tarzanPosition.setY(Math.max(0, tarzanPosition.getY()-speed));
	        energy -= ENERGY_LOSS;
	    }
	    if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
	        tarzanPosition.setY(Math.min(Map.SIZE_MAP-1, tarzanPosition.getY()+speed));
	        energy -= ENERGY_LOSS;
	    }
	    if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
	        tarzanPosition.setX(Math.max(0, tarzanPosition.getX()-speed));
	        energy -= ENERGY_LOSS;
	    }
	    if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
	        tarzanPosition.setX(Math.min(Map.SIZE_MAP-1, tarzanPosition.getX()+speed));
	        energy -= ENERGY_LOSS;
	    }
	    
	    if(inTheWater()) {
	    	energy -= WATER_ENERGY_LOSS;
	    }
	    
	    if (energy <= 0) {
	    	endOfGameLost();
	    }
	}
	
	private void endOfGameLost() {
		handler.getHandlerGame().getGameApp().newJOptionPane("Sorry, you lost :("); // add score
		
		handler.getHandlerGame().init(); // new Game --> back to start
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}


