package tarzan;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tilegame.*;

import map.Map;
import notmoving.*;

public class Tarzan implements KeyListener { 
	// I would set everything private? since there is no subclass - true 
	// + make getters
	private final static int SPEED = 1;
	private final static int ENERGY_LOSS = 1;
	private final static int WATER_ENERGY_LOSS = 10;
	private int energy;
	private int endurance;
	private int strength;
	private boolean isAlive ; 
	private int jaguarsKilled;
	private Position2D tarzanPosition;
	//private Position2D previousPosition;
	private int fieldOfViewRadius;
	private Handler handler;
	private int speed;

	private boolean[] keys;
	private boolean up; // w
	public boolean down; // s
	public boolean left; // a
	public boolean right; // d
	public boolean aUp; // arrows
	public boolean aDown;
	public boolean aLeft;
	public boolean aRight;

	// rectangle to check for collision
	protected Rectangle bounds; 

	// Constructor
	public Tarzan(Position2D pos, Handler handler, Level level, int strength, int endurance){
		this.handler = handler;
		tarzanPosition = pos;
		//previousPosition = pos;
		energy = level.getInitialEnergy();
		this.strength = strength;
		this.endurance = endurance;
		isAlive = true;
		jaguarsKilled = 0;
		fieldOfViewRadius = 5;
		speed = SPEED;
		fieldOfViewRadius = level.getVisibilitySize();
		keys = new boolean[256]; 
	}

	public Position2D getTarzanPosition() {
		return tarzanPosition;
	}

	public void setTarzanPosition(Position2D tarzanPosition) {
		this.tarzanPosition = tarzanPosition;
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


	/*public Position2D getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Position2D previousPosition) {
		this.previousPosition = previousPosition;
	}

	public void backToPreviousPosition() {
		tarzanPosition = previousPosition;
	}*/

	public int getFieldOfViewRadius() {
		return fieldOfViewRadius;
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
		handler.getHandlerGame().getGameApp().getGamePanel().updateGameSettings(strength, endurance, energy, jaguarsKilled,handler.getHandlerMap().getMapLevel().getGoalStrength(), handler.getHandlerMap().getMapLevel().getGoalEndurance(), handler.getHandlerMap().getMapLevel().getGoalJaguars());
	}

	public void eatBanana(){
		endurance += Banana.getEnduranceGiven();
		System.out.println("Endurance: " + endurance);
	}

	public void pickKnife() {
		strength += Knife.getStrengthGiven();
	}

	public void takePill() {
		energy += Kavurus.getEnergyGiven(); // no idea how much
	}

	public void janeFound() {
		if(areGoalsMet()) {
			endOfGameWin();
		} else {
			System.out.println("Meet the goals and come find me later!");
		}
	}

	private boolean areGoalsMet() {
		if (strength >= handler.getHandlerMap().getMapLevel().getGoalStrength() &&
				endurance >= handler.getHandlerMap().getMapLevel().getGoalEndurance() &&
				jaguarsKilled >= handler.getHandlerMap().getMapLevel().getGoalJaguars()) {
			return true;
		} else {
			return false;
		}
	}

	// M: should we add a method also isJaneFound = end of the game in Tarzan class?
	// I think that we could have a method findingJane that checks if the goals are met, and if they are, 
	// it ends the game, if not, it reminds the player how much opponents he still has to kill / flowers 
	// to pick / bananas to eat / knives to find

	void fieldOfView() {} // M: what is this for ? 
	// --> to know which jaguars / notlivings are in Tarzan's sight = to know whether to draw them or not
	// But I think it should go in Map / Game



	/*void update() { // DO THIS IN GAME: CHECK IF TARZANPOSITION == jaguar/NOTLIVINGSPOSITION

		// called at each time step --> M: what does it mean? 


		// if position of jaguar or not living = position of tarzan
		// call fight 
		if (jaguarPosition == tarzanPosition);
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

	public Handler getHandler() {
		return handler;
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

	public int getJaguarsKilled() {
		return jaguarsKilled;
	}

	public void setJaguarsKilled(int jaguarsKilled) {
		this.jaguarsKilled = jaguarsKilled;
	}

	public void killsJaguar() {
		jaguarsKilled += 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;

		//setPreviousPosition(tarzanPosition);

		if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]){
			tarzanPosition.setY(Math.max(0, tarzanPosition.getY()-speed));
			energy -= ENERGY_LOSS*(20-endurance/10);
		}
		if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
			tarzanPosition.setY(Math.min(Map.SIZE_MAP-1, tarzanPosition.getY()+speed));
			energy -= ENERGY_LOSS*(15-endurance/10);
		}
		if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
			tarzanPosition.setX(Math.max(0, tarzanPosition.getX()-speed));
			energy -= ENERGY_LOSS*(15-endurance/10);
		}
		if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
			tarzanPosition.setX(Math.min(Map.SIZE_MAP-1, tarzanPosition.getX()+speed));
			energy -= ENERGY_LOSS*(15-endurance/10);
		}

		if(inTheWater()) {
			energy -= WATER_ENERGY_LOSS;
		}

		if(energy <= 0) {
			endOfGameLost();
		}

		if(handler.getHandlerWorld().getWorldNotMovings(tarzanPosition) != null) {
			System.out.println("Non moving encountered");
			handler.getHandlerWorld().getWorldNotMovings(tarzanPosition).interact(this);
		}
	}


	private void endOfGameLost() {
		handler.getHandlerGame().getGameApp().newJOptionPane("Sorry, you lost :("); // add score
		handler.getHandlerGame().init(); // new Game --> back to start
	}

	private void endOfGameWin() {
		handler.getHandlerGame().getGameApp().newJOptionPane("Congrats, you win :D"); // add score
		handler.getHandlerGame().init(); // new Game --> back to start
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}


}


