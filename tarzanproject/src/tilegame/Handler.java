package tilegame;

import map.Map;
import map.World;

public class Handler {
	private Game handlerGame;
	private Map handlerMap;
	private World handlerWorld;
	
	public Handler(Game game) {
		handlerGame = game;
		
	}

	public Game getHandlerGame() {
		return handlerGame;
	}

	public void setHandlerGame(Game handlerGame) {
		this.handlerGame = handlerGame;
	}
	
	public Map getHandlerMap() {
		return handlerMap;
	}

	public void setHandlerMap(Map handlerMap) {
		this.handlerMap = handlerMap;
	}

	public World getHandlerWorld() {
		return handlerWorld;
	}

	public void setHandlerWorld(World handlerWorld) {
		this.handlerWorld = handlerWorld;
	}
	
}
