package tilegame;

import map.Map;
import map.World;

/**
 * @author Faustine & Martina
 * 
 * The Handler class is used to handle the game from the Tarzan class.
 * 
 */

public class Handler {
	private Game handlerGame;
	private Map handlerMap;
	private World handlerWorld;
	
	/**
	 * Constructor.
	 * @param game
	 */
	public Handler(Game game) {
		handlerGame = game;
	}

	/**
	 * Getter.
	 * @return handlerGame
	 */
	public Game getHandlerGame() {
		return handlerGame;
	}

	/**
	 * Setter.
	 * @param handlergame
	 */
	public void setHandlerGame(Game handlerGame) {
		this.handlerGame = handlerGame;
	}
	
	/**
	 * Getter.
	 * @return handlerMap
	 */
	public Map getHandlerMap() {
		return handlerMap;
	}

	/**
	 * Setter.
	 * @param handlerMap
	 */
	public void setHandlerMap(Map handlerMap) {
		this.handlerMap = handlerMap;
	}

	/**
	 * Getter.
	 * @return handlerWorld
	 */
	public World getHandlerWorld() {
		return handlerWorld;
	}

	/**
	 * Setter.
	 * @param handlerWorld
	 */
	public void setHandlerWorld(World handlerWorld) {
		this.handlerWorld = handlerWorld;
	}
}
