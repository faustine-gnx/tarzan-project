package tarzan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import com.sun.tools.javac.Main;
import com.sun.tools.javac.Main;
import animals.Animal;
import map.Map;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Kavurus;
import notmoving.Knife;
import tilegame.Goal;
import tilegame.Level;
import tilegame.Settings;



public class Tarzan implements KeyListener { 
	
	// I would set everything private? since there is no subclass - true 
					  // + make getters
	protected String name; 
	protected int[][] position; 
	protected int energy;
	protected int endurance;
	protected int level; // M: i added the protected int level if not used we can delete it later 
	protected int strength;
	protected boolean isAlive ; 
	// attribute or method depending on energy? M:--> true : but in order to make the game fails if tarzan is killed? 
	protected int numberOfOpponentsKilled;
	protected int numberOfFlowersPicked;
	    
	// Constructors
	// Constructor called in game:
	// lvl and setg passed as arguments are attributes of game
	public Tarzan(int[][] pos, Level lvl, Settings setg){
		this.name = "Tarzan";
		this.position = pos;
		this.energy = lvl.getInitialEnergy();
		this.endurance = Settings.getInitialEndurance();
		this.strength = Settings.getInitialStrength();
		this.isAlive = true;
		this.numberOfOpponentsKilled = 0;
		this.numberOfFlowersPicked = 0;
		//this.level = setg.getLevel();		
	}

		
	void update() { 
		// called at each time step
		
		// if position of animal or not living = position of tarzan
		// --> call fight / pickup etc.
		
		// if goals are reached
		// --> message stating goals are reached
		
		// decrease energy by value
		// --> value depends on mode: waling, swimming, swinging
	}
	
	void fight(Animal a) throws Throwable {
		this.energy -= a.getAnimalStrength();
		this.numberOfOpponentsKilled += 1;
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		a.finalize();
	}
	
 // void levelUp() {} what is this used for? 
 // M: i deleted at first I thought we could used to go from one level to another 
	void showAbility() {}
	
	void eatBanana(Banana b) throws Throwable{
		this.endurance += Banana.getEnduranceGiven();
		b.finalize(); // destroy banana
	}
	
	void pickFlower(Flower f) throws Throwable{
		this.numberOfFlowersPicked += 1;
		f.finalize(); // destroy flower
	}
	
	void pickKnife(Knife k) throws Throwable{
		this.strength += Knife.getStrengthGiven();
		k.finalize(); // destroy knife
	}
	
	void takePill(Kavurus k) throws Throwable {
		this.energy += Kavurus.getEnergyGiven(); // no idea how much
		k.finalize(); // destroy pill
	}
	
	void fieldOfView() {} // what is this for ? 
	
	void inReachDistance() {}
	
	boolean hasReachedGoal(Goal g) {
		if ((this.numberOfOpponentsKilled >= g.getAnimalKilled()) 
				&& (this.numberOfFlowersPicked >= g.getFlowerPickedUp())
				&& (this.strength >= g.getFightingStrength())
				&& (this.endurance)>= g.getMobilityEndurance() ) {
			return true;
		} else {
			return false;
		}
	}
	
// CREATE getTexture / get Sound? M: yes better use create I modified it 
	void createTexture(){ 
		//texture 
		try {
			URL imageUrl = new URL("https://vignette.wikia.nocookie.net/disney/images/2/2c/Tarzan_transparent.png/revision/latest?cb=20151031043816");
			InputStream in = imageUrl.openStream();
			BufferedImage image = ImageIO.read(in);
			in.close();
		} catch(IOException ioe) {
			//log the error
		}
	}
					    
	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
					Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=6O0lLgV6KpQ") ));
			clip.open(inputStream);
			clip.start(); 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
   
	// move with keyboard
    private void move(KeyEvent e, String keyStatus){
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
        } 
            
        }

    // unimplemented for KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}}
