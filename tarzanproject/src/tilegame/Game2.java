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
	
	private BufferStrategy gameBuffer;
	private Graphics g;
	private Map2 gameMap;
		
	public Game2() {
	}
	
	public synchronized void start() {
		if(this.gameRunning) {
			return;
		}
		this.gameRunning = true;
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}
	
	public synchronized void stop() {
		if(!this.gameRunning) {
			return;
		}
		this.gameRunning = false;
		try {
			this.gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		this.gameApp = new GameApplication();
		this.gameMap = new Map2(this.gameApp.getInitialStrength(), this.gameApp.getInitialEndurance(), this.gameApp.getLevel());
		Assets.init();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		this.gameBuffer = this.gameApp.getGamePanel().getGameCanvas().getBufferStrategy();
		if(this.gameBuffer == null) {
			this.gameApp.getGamePanel().getGameCanvas().createBufferStrategy(3);
			return;
		}
		//draw
		this.g = this.gameBuffer.getDrawGraphics();
		this.gameMap.setMapDrawing(this.g);
		this.gameBuffer.show();
		this.g.dispose();
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
	
	
	
	
}
