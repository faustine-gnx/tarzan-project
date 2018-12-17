package tarzan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tilegame.*;

import map.Map;
import notmoving.*;

/**
 * @author Faustine & Martina
 * 
 * The Tarzan class represents the player. It implements KeyListener in order to move him around.
 * Tarzan has skills (endurance and strength), energy, and the number of jaguars (opponents) he has killed.
 * He can move around the board to interact with NotLivings.
 * Moving cost energy, and going on a WaterTile decreases his energy even more (swimming is exhausting!)
 * The game updates (change in position, skills, energy...) are handled here.
 * The the end of the game (victory or defeat) is managed here.
 * He can find knives to increase his strength, eat bananas for endurance, and take Kavuru's pills for energy.
 * To win the game, Tarzan must be strong and endurant enough + have killed enough jaguars (to impress Jane).
 * If Jane is found and the goals are met, the game is won and a new game can be started from the StartPanel.
 * If Tarzan runs out of energy, the game is lost and a new game can be started from the Start panel.
 * 
 * Since a lot of the game is handled here, Tarzan has an Handler attribute to make it easier to access Game, Map,
 * and World attributes/methods for updates. 
 * 
 * Most updates and interactions are handled in the keyPressed method, as when no keys are pressed, nothing should
 * be happening (no need to call the methods at each time step if no movement was detected). 
 * 
 */

//TODO: implement scores for high scores

public class Tarzan implements KeyListener { 
	private final static int SPEED = 1;
	private final static int ENERGY_LOSS = 1;
	private final static int WATER_ENERGY_LOSS = 10;
	public final static int FIELD_OF_VIEW_RADIUS = 2;
	public final static int INITIAL_ENERGY = 500; 
	private int energy;
	private int endurance;
	private int strength;
	private int jaguarsKilled;
	private Position2D tarzanPosition;
	private Handler handler;
	private int speed;

	private boolean[] keys;

	// Constructor
	public Tarzan(Position2D pos, Handler handler, Level level, int strength, int endurance){
		this.handler = handler;
		tarzanPosition = pos;
		energy = INITIAL_ENERGY;
		this.strength = strength;
		this.endurance = endurance;
		jaguarsKilled = 0;
		speed = SPEED;
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

	private boolean inTheWater() { 
		return (handler.getHandlerWorld().getWorldTiles()[tarzanPosition.getY()][tarzanPosition.getX()] == 1);
	}

	public void takeDamage(int damage) {
		//manipulate the amount of damage taken
		if(damage >= energy) { 
			energy = 0;
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

	public int getJaguarsKilled() {
		return jaguarsKilled;
	}

	public void setJaguarsKilled(int jaguarsKilled) {
		this.jaguarsKilled = jaguarsKilled;
	}

	public void killsJaguar() {
		jaguarsKilled += 1;
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
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;

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

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}


}


