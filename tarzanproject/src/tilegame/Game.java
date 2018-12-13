package tilegame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import display.DisplayOfGame;
import imageloader.ImagesLoader;
import map.Map;
import java.util.Timer; 


//main class of our game --> all methods here 

public class Game implements Runnable { // does it need to implement runnable? already in GameApp

	//display of our game
	private Map map; 
	protected Level level;
	protected final Settings setg;
	
	//thread = run class separately from our program = more efficient
	private Thread thread;
	private boolean running; 

	private KeyManager keyManager ;

	// Game constructor

	public Game(int lvl, int strength, int endurance) {
		
		keyManager = new KeyManager();
		this.level = new Level(lvl);
		this.setg = new Settings(strength, endurance, lvl);
		this.map = new Map(this.level, this.setg);
		
	}

	// update everything 
	private void tick () {
		
	
	}


	// new timer 

	Timer timer = new Timer();


	// run the timer task 

	//run method 
	public void run () {
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		// game loop. 
		//update all variables, positions of objects like animals tarzan etc and draw everything to the screen. 
		// while running variable is true = run over and over again
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	private void render() {
		// TODO Auto-generated method stub
		
	}

	// initialize methods for Thread : start Thread, stop Thread. 
	public synchronized boolean start() { 
		// if it's already running do not do anything
		if(running){
			// running true while loop and initialize thread
			running = true; 
			// initialize Thread
			thread = new Thread (this) ; 
			// start threat.Call run method 
			thread.start();
		}
		return running;
	}


	// stop thread safely
	public synchronized void stop() {
		// if not running = equal to false. do not anything
		if (!running) 
			return; 
		running = false; 
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace () ;
		}

	}

	// game ended 

	void endGame() {
		// Draw the message to the board
		System.out.println("Game Ended");

	}

	
}