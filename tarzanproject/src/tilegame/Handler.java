package tilegame;

import map.World;

public class Handler {
	private Game handlerGame;
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

	public World getHandlerWorld() {
		return handlerWorld;
	}

	public void setHandlerWorld(World handlerWorld) {
		this.handlerWorld = handlerWorld;
	}
	
	public KeyManager getKeyManager() {
		return handlerGame.getKeyManager();
	}
}
