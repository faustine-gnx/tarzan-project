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

public class Game implements Runnable {
	
	//display of our game
	private Map map; 
	protected Level lvl;
	protected static Settings setg;
	public int width;
	public int height; 
	public String title; 
	
	// while does not run 
	private boolean running = false; 
	//thread = run class separately from our program = more efficient
	private Thread thread;
	
	//variables for Draw 
	// how draw things to the screen
	private BufferStrategy bufferStrategy; 
	private Graphics graphics; 
	private BufferedImage ImageTitle ; 
	private BufferedImage testsheet;
	
	// Game constructor
	
	public Game() {
	}
	
	//set method initializeGraphics and new Display 
	public void initializeGraphics () {
		DisplayOfGame display = new DisplayOfGame (); 
		testsheet = ImagesLoader.loadImage("/textures/welcometothejungle.png"); 
	}
	
	// update everything 
	private void tick () {
	}
	
	// draw everything 
	private void render() {
		//set buffer strategy to whatever buffer strategy is in our canvas 
		DisplayOfGame display = null;
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if(bufferStrategy == null) {
			// three buffers
			display.getCanvas().createBufferStrategy(3);
			return; 
		}
		
		{
		// graphics object 
		graphics = bufferStrategy.getDrawGraphics();
		
		// Screen empty
		graphics.clearRect(0, 0, width, height);
		
		//draw
		graphics.drawImage(testsheet,0,0,null);
		
		//end draw
		bufferStrategy.show();
		graphics.dispose();
		}
	}
	
	// new timer 
	
	Timer timer = new Timer();
	
	
	// run the timer task 
	
	//run method 
	public void run () {
		
	// call method initialize graphics
		initializeGraphics(); 
	
	// game loop. 
	//update all variables, positions of objects like animals tarzan etc and draw everything to the screen. 
	// while running variable is true = run over and over again
		while (running) {
			tick();
			render();
		}
	
		stop();
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
	
	void endGame(Graphics g) {

	    // Create a message telling the player the game is over
	    String message = "Game over";

	    // Create a new font instance
	    Font font = new Font("Times New Roman", Font.BOLD, 14);
	    FontMetrics metrics = getFontMetrics(font);

	    // Set the color of the text to red, and set the font
	    g.setColor(Color.red);
	    g.setFont(font);

	    // Draw the message to the board
	    g.drawString(message, (width - metrics.stringWidth(message)) / 2,
	            height / 2);

	    System.out.println("Game Ended");

	}

	
	private FontMetrics getFontMetrics(Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	// Main
	public static void main(String[] args) {
		DisplayOfGame newGameDisplay = new DisplayOfGame();
		
		Game newGame = new Game();
		
		// read endurance energy and levelNumber from user entry --> HOW --> M: scanner
		newGame.setg = new Settings(Scanner.endurance, Scanner.energy, Scanner.levelNumber);
		newGame.lvl = new Level(newGame.setg.getLevel());
		newGame.map = new Map(newGame.lvl, setg); // 
		
	}
}