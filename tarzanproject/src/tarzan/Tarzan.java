package tarzan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import tilegame.*;
import map.Map;
import notmoving.*;

/**
 * @author Faustine & Martina
 * 
 *         The Tarzan class represents the player. It implements KeyListener in
 *         order to move him around. Tarzan has skills (endurance and strength),
 *         energy, and the number of jaguars (opponents) he has killed. He can
 *         move around the board to interact with NonMovings. Moving costs
 *         energy, and going on a WaterTile decreases his energy even more
 *         (swimming is exhausting!) The game updates (change in position,
 *         skills, energy...) are handled here. The end of the game (victory
 *         or defeat) is managed here. Tarzan can find knives to increase his
 *         strength, eat bananas for endurance, and take Kavuru's pills for
 *         energy. To win the game, Tarzan must be strong and endurant enough +
 *         have killed enough jaguars (to impress Jane). If Jane is found and
 *         the goals are met, the game is won and a new game can be started from
 *         the StartPanel. If Tarzan runs out of energy, the game is lost and a
 *         new game can be started from the Start panel. The final score depends
 *         on the number of bananas eaten, the number of knives found, the
 *         number of jaguars killed and the energy remaining at the end of the
 *         game, all multiplied by a factor level.
 * 
 *         Since a lot of the game is handled here, Tarzan has an Handler
 *         attribute to make it easier to access Game, Map, and World
 *         attributes/methods for updates.
 * 
 *         Most updates and interactions are handled in the keyPressed method,
 *         as when no keys are pressed, nothing should be happening (no need to
 *         call the methods at each time step if no movement was detected).
 */

public class Tarzan implements KeyListener {
	// Constants
	private final static int SPEED = 1; /**< moves 1 tile at a time */
	private final static int ENERGY_LOSS = 1; /**< One move = decrease of factor 1 in energy (actual number depends on endurance) */
	private final static int WATER_ENERGY_LOSS = 5; /**< Extra decrease of factor 5 when moving in water */
	public final static int FIELD_OF_VIEW_RADIUS = 2; /**< the player sees all tiles accessible in 2 moves */
	public final static int INITIAL_ENERGY = 500; /**< initial energy */
	public final static int WINNING_BONUS = 500; /**< extra points when winning the game */

	// Attributes
	private int energy; /**< current energy */
	private int endurance; /**< current endurance */
	private int strength; /**< current strength */
	private int jaguarsKilled; /**< current number of killed jaguars */
	private Position2D tarzanPosition; /**< current position */
	private Handler handler; /**< handler for game / map / world */
	private int speed; /**< speed of Tarzan */
	private boolean[] keys; /**< for the key listener */

	/**
	 * Constructor.
	 * @param pos, handler, level, strength, endurance
	 */
	public Tarzan(Position2D pos, Handler handler, Level level, int strength, int endurance) {
		this.handler = handler;
		tarzanPosition = pos;
		energy = INITIAL_ENERGY;
		this.strength = strength;
		this.endurance = endurance;
		jaguarsKilled = 0;
		speed = SPEED;
		keys = new boolean[256];
		System.out.println("Tarzan created!");
	}

	/**
	 * Getter.
	 * @return tarzanPosition
	 */
	public Position2D getTarzanPosition() {
		return tarzanPosition;
	}

	/**
	 * Setter.
	 * @param tarzanPosition
	 */
	public void setTarzanPosition(Position2D tarzanPosition) {
		this.tarzanPosition = tarzanPosition;
	}

	/**
	 * Setter.
	 * @param x, y
	 */
	public void setTarzanPosition(int x, int y) {
		// to ensure that Tarzan is always on the map
		if (x > Map.SIZE_MAP) {
			x = Map.SIZE_MAP - 1;
		}
		if (x < 0) {
			x = 0;
		}
		if (y > Map.SIZE_MAP) {
			y = Map.SIZE_MAP - 1;
		}
		if (y < 0) {
			y = 0;
		}
		tarzanPosition.set(x, y);
	}

	/**
	 * Returns true if Tarzan is on a WaterTile.
	 * @return boolean
	 */
	private boolean inTheWater() {
		return (handler.getHandlerWorld().getWorldTiles()[tarzanPosition.getX()][tarzanPosition.getY()] == 1);
	}

	/**
	 * Interaction with Jaguar: loss of energy. Tarzan might die.
	 */
	public void fightJaguar() {
		System.out.println("Fighting a jaguar!");
		if (Jaguar.JAGUAR_STRENGTH >= energy) {
			energy = 0;
			System.out.println("This is the end of the game!");
		} else {
			energy -= Jaguar.JAGUAR_STRENGTH;
		}
	}

	/**
	 * Interaction with Banana: score and endurance increased.
	 */
	public void eatBanana() {
		System.out.println("Eating a banana...");
		endurance += Banana.ENDURANCE_GIVEN;
		handler.getHandlerGame().addGameScore(Banana.ENDURANCE_GIVEN*handler.getHandlerMap().getMapLevel().getLevelNumber());
	}

	/**
	 * Interaction with Knife: score and strength increased.
	 */
	public void pickKnife() {
		System.out.println("Picking up a knife...");
		strength += Knife.STRENGTH_GIVEN;
		handler.getHandlerGame().addGameScore(Knife.STRENGTH_GIVEN*handler.getHandlerMap().getMapLevel().getLevelNumber());
	}

	/**
	 * Interaction with Kavurus: score and energy increased.
	 */
	public void takePill() {
		System.out.println("Taking a magic pill...");
		handler.getHandlerGame().addGameScore(Kavurus.ENERGY_GIVEN*handler.getHandlerMap().getMapLevel().getLevelNumber());
	}

	/**
	 * Interaction with Jane: if goals are met, game won.
	 * @throws IOException 
	 */
	public void janeFound() throws IOException {
		System.out.println("Jane found!");
		if (areGoalsMet()) {
			handler.getHandlerGame().addGameScore(energy*handler.getHandlerMap().getMapLevel().getLevelNumber());
			endOfGameWin();{
			}
		} else {
			System.out.println("Meet the goals and come find me later!");
		}
	}

	/**
	 * Returns true if goals are met.
	 * @return boolean
	 */
	private boolean areGoalsMet() {
		if (strength >= handler.getHandlerMap().getMapLevel().getGoalStrength()
				&& endurance >= handler.getHandlerMap().getMapLevel().getGoalEndurance()
				&& jaguarsKilled >= handler.getHandlerMap().getMapLevel().getGoalJaguars()) {
			System.out.println("Goals are met!");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Getter.
	 * @return handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * Getter.
	 * @return energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Getter.
	 * @return endurance
	 */
	public int getEndurance() {
		return endurance;
	}

	/**
	 * Getter.
	 * @return strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Getter.
	 * @return jaguarsKilled
	 */
	public int getJaguarsKilled() {
		return jaguarsKilled;
	}

	private int energyLoss() {
		return Math.abs((150 - endurance) / 10);
	}

	/**
	 * Increment jaguarsKilled by 1 and increased score.
	 */
	public void killsJaguar() {
		jaguarsKilled += 1;
		handler.getHandlerGame().addGameScore(Jaguar.JAGUAR_STRENGTH*handler.getHandlerMap().getMapLevel().getLevelNumber());
		System.out.println("A jaguar is dead!");
	}

	/**
	 * End of game - player lost. New game application.
	 * @throws IOException 
	 */
	private void endOfGameLost() throws IOException {
		handler.getHandlerGame().getGameApp().newJOptionPane("Sorry, you lost :( \n Score: " + String.valueOf(handler.getHandlerGame().getGameScore().getScore())); 
		handler.getHandlerGame().updateHighScores(handler.getHandlerGame().getGameScore());
		handler.getHandlerGame().getGameApp().setVisible(false);
		handler.getHandlerGame().initNew(); // new Game --> back to start (keeps player name)
	}

	/**
		 * End of game - player won (bonus for score). New game application.
		 * @throws IOException 
		 */
		private void endOfGameWin() throws IOException {
			handler.getHandlerGame().addGameScore(WINNING_BONUS);
			handler.getHandlerGame().getGameApp().newJOptionPane("Congrats, you win :D \n Score: " + String.valueOf(handler.getHandlerGame().getGameScore().getScore())); 
			handler.getHandlerGame().updateHighScores(handler.getHandlerGame().getGameScore());
			handler.getHandlerGame().getGameApp().setVisible(false);
			handler.getHandlerGame().initNew();
		}

		/**
		 * Update.
		 */
		public void tick() {
			handler.getHandlerGame().getGameApp().getGamePanel().updateGameSettings(strength, endurance, energy,
					jaguarsKilled, handler.getHandlerMap().getMapLevel().getGoalStrength(),
					handler.getHandlerMap().getMapLevel().getGoalEndurance(),
					handler.getHandlerMap().getMapLevel().getGoalJaguars(), handler.getHandlerGame().getGameScore().getScore());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
				return;
			keys[e.getKeyCode()] = true;

			if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
				tarzanPosition.setY(Math.max(0, tarzanPosition.getY() - speed));
				energy -= ENERGY_LOSS * energyLoss();
			}
			if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
				tarzanPosition.setY(Math.min(Map.SIZE_MAP - 1, tarzanPosition.getY() + speed));
				energy -= ENERGY_LOSS * energyLoss();
			}
			if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
				tarzanPosition.setX(Math.max(0, tarzanPosition.getX() - speed));
				energy -= ENERGY_LOSS * energyLoss();
			}
			if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
				tarzanPosition.setX(Math.min(Map.SIZE_MAP - 1, tarzanPosition.getX() + speed));
				energy -= ENERGY_LOSS * energyLoss();
			}

			if (inTheWater()) {
				System.out.println("In the water... Energy decreased!");
				energy -= WATER_ENERGY_LOSS;
			}

			if (energy <= 0) {
				try {
					endOfGameLost();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (handler.getHandlerWorld().getWorldNonMovings(tarzanPosition) != null) {
				//System.out.println("Non moving encountered");
			handler.getHandlerWorld().getWorldNonMovings(tarzanPosition).interact(this);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
