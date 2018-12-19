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
 *         The Game class is where the game and the gui are centralized. It is
 *         the Runnable of our project. It contains a GameApplication for the
 *         gui and a map to play the game.
 * 
 */

public class Game implements Runnable {

	private GameApplication gameApp; // variable game application
	private Thread gameThread; // variable thread of the game
	private boolean gameRunning = false; // variable game running set to false
	private BufferStrategy gameBuffer; // variable buffer
	private Graphics g;// variable graphics
	private Map gameMap;// variable map
	private Handler gameHandler; // variable handler
	private long lastTime;
	// Specify how many seconds there are in a minute as a double
	// store as a double cause 60 sec in nanosec is big and store as final so it
	// can't be changed
	private int fps = 60; // tick method called 60 times per second --> 5 for now: player does not press
	// key + than 5 times/s
	// Set definition of how many ticks per 1000000000 ns or 1 sec
	private double timePerTick; // because nanoseconds
	private double delta; // variable for create delta (= change in time)
	private long now; // variable for the time of the system
	private long timer; // variable to set the timer
	private HighScoreManager highScoreManager;
	private Score gameScore;

	/**
	 * Start new game (new thread).
	 */
	public synchronized void start() {
		if (gameRunning) {
			return;
		}
		gameRunning = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Stops the game.
	 */
	public synchronized void stop() {
		if (!gameRunning) {
			return;
		}
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
		gameScore = new Score(gameApp.getPlayerName(), 0);
		gameHandler.setHandlerMap(gameMap);
		gameHandler.setHandlerWorld(gameMap.getMapWorld());
		gameApp.addKeyListener(gameMap.getMapTarzan());
		gameApp.setFocusable(true);
		highScoreManager = new HighScoreManager();
	}

	/**
	 * Initialize: new game application and initialization of assets.
	 * @throws IOException 
	 */
	public void init() throws IOException {
		gameApp = new GameApplication(this);
		gameHandler = new Handler(this);
		Assets.init();
	}
	
	/**
	 * Initialize: new game application and initialization of assets.
	 * @throws IOException 
	 */
	public void initNewGame() throws IOException {
		gameApp.initUI();
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
			gameMap.mapTarzan.tick();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the system time
		lastTime = System.nanoTime();
		fps = 60; // tick method called 60 times per second
		// Set definition of how many ticks per 1000000000 ns or 1 sec
		timePerTick = 1000000000 / fps; // because nanoseconds
		delta = 0;
		timer = 0;

		while (gameRunning) {
			// Update the time
			now = System.nanoTime();
			// calculate change in time since last known time
			delta += (now - lastTime) / timePerTick;
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
