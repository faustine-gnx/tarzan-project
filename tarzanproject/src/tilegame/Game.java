package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gui.Assets;
import gui.GameApplication;
import imageloader.ImageLoader;
import map.Map;

public class Game implements Runnable {
	private GameApplication gameApp;
	private Thread gameThread;
	private boolean gameRunning = false;
	private BufferStrategy gameBuffer;
	private Graphics g;
	private Map gameMap;
	private Handler gameHandler;
		
	public Game() {
	}
	
	public synchronized void start() {
		if(gameRunning) {
			return;
		}
		gameRunning = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop() {
		if(!gameRunning) {
			return;
		}
		gameRunning = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void initGame() {
		gameMap = new Map(gameApp.getInitialStrength(), gameApp.getInitialEndurance(), gameApp.getLevel(), gameHandler);
		gameHandler.setHandlerMap(gameMap);
		gameHandler.setHandlerWorld(gameMap.getMapWorld());
		gameApp.addKeyListener(gameMap.getMapTarzan());
		gameApp.setFocusable(true);
	}
	
	public void init() {
		gameApp = new GameApplication(this);
		gameHandler = new Handler(this);
		System.out.println("Level "+gameApp.getLevel());
		//gameMap = new Map(gameApp.getInitialStrength(), gameApp.getInitialEndurance(), gameApp.getLevel(), gameHandler);
		//gameHandler.setHandlerMap(gameMap);
		//gameHandler.setHandlerWorld(gameMap.getMapWorld());
		Assets.init();
		//this.gameApp.getGamePanel().addKeyListener(this.keyManager);
		//this.gameApp.getGamePanel().setGamePanelFocusable(); // not working
		// would be better to add to gamePanel but I don't know how to do it
		// for now this works fine
		
		//gameApp.addKeyListener(this.keyManager);
		//gameApp.addKeyListener(gameMap.getMapTarzan());
		//gameApp.setFocusable(true);
	}
	
	public Map getGameMap() {
		return gameMap;
	}

	private void tick() {
		if(gameApp.isGamePlaying() && gameMap != null) {
			gameMap.mapTarzan.tick();
		}
		
	}
	
	public GameApplication getGameApp() {
		return gameApp;
	}

	private void render() {
		gameBuffer = gameApp.getGamePanel().getGameCanvas().getBufferStrategy();
		if(gameBuffer == null) {
			gameApp.getGamePanel().getGameCanvas().createBufferStrategy(3);
			return;
		}
		//draw
		g = gameBuffer.getDrawGraphics();
		if (gameMap != null) {
			gameMap.render(this.g);
		}
		gameBuffer.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		//Get the system time
		long lastTime = System.nanoTime();
		//Specify how many seconds there are in a minute as a double
		//store as a double cause 60 sec in nanosec is big and store as final so it can't be changed
		int fps = 60; // tick method called 60 times per second --> 5 for now: player does not press key + than 5 times/s
		//Set definition of how many ticks per 1000000000 ns or 1 sec
		double timePerTick = 1000000000/fps; // because nanoseconds
		double delta = 0;
		long now;
		long timer = 0;
		long ticks = 0;
		
		while(gameRunning) {
			//Update the time
			now = System.nanoTime();
			//calculate change in time since last known time
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			//update last known time    
			lastTime = now;
			 //continue while delta is less than or equal to 1
			if (delta >= 1) {
				//Go through one tick  
				tick();
				render();
				ticks++;
				//decrement delta
				delta--;
			}
			
			if (timer >= 1000000000) {
				//System.out.println("Ticks and Frames" + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
}
