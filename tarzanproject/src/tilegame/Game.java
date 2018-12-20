package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import gui.Assets;
import gui.GameApplication;
import highscores.HighScoreManager;
import highscores.Score;
import map.Map;

/**
 * @author Faustine & Martina
 * 
 *         The Game class is where the game and the GUI are centralized. It is
 *         the executable (Runnable) of our project. It contains a GameApplication 
 *         for the GUI and a map to play the game.
 * 
 */

public class Game implements Runnable {

	private GameApplication gameApp; // game application to handle GUI
	private Thread gameThread; // to execute program
	private boolean gameRunning = false; // !! different than playing !!
	private BufferStrategy gameBuffer; // to draw
	private Graphics g; // graphics for render
	private Map gameMap;// map = the game
	private Handler gameHandler; // to access game, map and world more easily
	private long lastTime;
	private final static int FPS = 60; // tick method called 60 times per second
	// Set definition of how many ticks per 1000000000 ns or 1 sec
	private final static double TIME_PER_TICK = 1000000000 / FPS; // 1000000000 because nanoseconds
	private double delta; // variable for create delta (= change in time)
	private long now; // variable for the time of the system
	private long timer; // variable to set the timer
	private HighScoreManager highScoreManager; // to manage high score
	private Score gameScore; // score of the player in this game

	/**
	 * Start new game (new thread).
	 */
	public synchronized void start() {
		if (gameRunning) {
			return;
		}
		System.out.println("Starting game");
		gameRunning = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Stops the game.
	 * @exception InterruptedException
	 */
	public synchronized void stop() {
		if (!gameRunning) {
			return;
		}
		System.out.println("Stopping game");
		gameRunning = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize game: new map and initialization of game application.
	 */
	public void initGame() {
		gameMap = new Map(gameApp.getInitialStrength(), gameApp.getInitialEndurance(), gameApp.getLevel(), gameHandler);
		gameScore.setName(gameApp.getPlayerName());
		gameHandler.setHandlerMap(gameMap);
		gameHandler.setHandlerWorld(gameMap.getMapWorld());
		gameApp.addKeyListener(gameMap.getMapTarzan());
		gameApp.setFocusable(true);
		highScoreManager = new HighScoreManager();
		System.out.println("Game initialized");
	}

	/**
	 * Initialize: new game application and initialization of assets.
	 * @throws IOException 
	 */
	public void init() throws IOException {
		System.out.println("Initialization...");
		gameApp = new GameApplication(this);
		gameHandler = new Handler(this);
		gameScore = new Score("Anonymous", 0);
		Assets.init();
		System.out.println("Initialized!");
	}

	/**
	 * Initialize: new game application and initialization of assets.
	 * Called at the end of a game. Remember the name of the previous players.
	 * @throws IOException 
	 */
	public void initNew() throws IOException {
		System.out.println("New initialization...");
		gameApp = new GameApplication(this, getGameApp().getPlayerName());
		gameScore = new Score(getGameApp().getPlayerName(), 0);
		gameHandler = new Handler(this);
		Assets.init();
		System.out.println("Initialized!");
	}

	/**
	 * Getter.
	 * @return gameMap
	 */
	public Map getGameMap() {
		return gameMap;
	}

	/**
	 * Getter.
	 * @return gameApp
	 */
	public GameApplication getGameApp() {
		return gameApp;
	}

	/**
	 * Getter.
	 * @return gameScore
	 */
	public Score getGameScore() {
		return gameScore;
	}

	/**
	 * Setter.
	 * @param score
	 */
	public void setScoreGameScore(int score) {
		gameScore.setScore(score);
	}

	/**
	 * Setter.
	 * @param score
	 */
	public void addGameScore(int score) {
		gameScore.setScore(gameScore.getScore()+score);
	}

	/**
	 * Setter.
	 * @param score
	 */
	public void updateHighScores(Score score) {
		highScoreManager.addScore(score);
	}

	/**
	 * Update.
	 */
	private void tick() {
		if (gameApp.isGamePlaying() && gameMap != null) {
			gameMap.getMapTarzan().tick();
		}
	}

	/**
	 * Update graphics.
	 */
	private void render() {
		gameBuffer = gameApp.getGamePanel().getGameCanvas().getBufferStrategy();
		if (gameBuffer == null) {
			gameApp.getGamePanel().getGameCanvas().createBufferStrategy(3);
			return;
		}
		// draw
		g = gameBuffer.getDrawGraphics();
		if (gameMap != null) {
			gameMap.render(this.g);
		}
		gameBuffer.show();
		g.dispose();
	}

	/**
	 * Run the game. Tick and render are called 60 times per second.
	 */
	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Get the system time
		lastTime = System.nanoTime();
		// Set definition of how many ticks per 1000000000 ns or 1 sec
		delta = 0;
		timer = 0;

		while (gameRunning) {
			// Update the time
			now = System.nanoTime();
			// calculate change in time since last known time
			delta += (now - lastTime) / TIME_PER_TICK;
			timer += now - lastTime;
			// update last known time
			lastTime = now;
			// continue while delta is less than or equal to 1
			if (delta >= 1) {
				// Go through one tick
				tick();
				render();
				// decrement delta
				delta--;
			}
			if (timer >= 1000000000) {
				timer = 0;
			}
		}
		stop();
	}
}
