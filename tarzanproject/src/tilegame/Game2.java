package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gui.Assets;
import gui.GameApplication;
import imageloader.ImageLoader;
import map.Map2;

public class Game2 implements Runnable {
	private GameApplication gameApp;
	private Thread gameThread;
	private boolean gameRunning = false;
	private KeyManager keyManager;
	private BufferStrategy gameBuffer;
	private Graphics g;
	private Map2 gameMap;
		
	public Game2() {
		this.keyManager = new KeyManager();
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
	
	private void init() {
		gameApp = new GameApplication();
		gameMap = new Map2(gameApp.getInitialStrength(), gameApp.getInitialEndurance(), gameApp.getLevel(), this);
		Assets.init();
		//this.gameApp.getGamePanel().addKeyListener(this.keyManager);
		//this.gameApp.getGamePanel().setGamePanelFocusable(); // not working
		// would be better to add to gamePanel but I don't know how to do it
		// for now this works fine
		gameApp.addKeyListener(this.keyManager);
		gameApp.setFocusable(true);
	}
	
	private void tick() {
		keyManager.tick();
		gameMap.mapTarzan.tick();
	}
	
	private void render() {
		gameBuffer = gameApp.getGamePanel().getGameCanvas().getBufferStrategy();
		if(gameBuffer == null) {
			gameApp.getGamePanel().getGameCanvas().createBufferStrategy(3);
			return;
		}
		//draw
		g = gameBuffer.getDrawGraphics();
		gameMap.setMapDrawing(this.g);
		gameBuffer.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		int fps = 60; // tick method called 60 times per second
		double timePerTick = 1000000000 /fps; // because nanoseconds
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		//long timer = 0;
		//long ticks = 0;
		
		while(gameRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			//timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				//ticks++;
				delta--;
			}
			
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	
}
