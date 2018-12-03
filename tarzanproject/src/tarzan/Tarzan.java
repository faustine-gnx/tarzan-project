package tarzan;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import com.sun.tools.javac.Main;
import sun.applet.Main;

import animals.Animal;
import notmoving.Kavurus;
import notmoving.Knife;
import tilegame.Goal;
import tilegame.Level;
import tilegame.Settings;

public class Tarzan { // I would set everything private? since there is no subclass
					  // + make getters
	protected String name; 
	protected int[][] position; 
	protected int energy;
	protected int endurance;
	//protected int level; // needed?
	protected int strength;
	protected boolean isAlive ; // attribute or method depending on energy?
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


	void fight(Animal a) {
		
	}
	
	void levelUp() {} // what is this used for?
	void showAbility() {}
	
	void pickFlower(){
		this.numberOfFlowersPicked += 1;
	}
	
	void pickKnife(){
		this.strength += Knife.getStrengthGiven();
	}
	
	void move() {}
	
	void takePill() {
		this.energy += Kavurus.getEnergyGiven(); // no idea how much
	}
	
	void fieldOfView() {} // 
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
	
	{}		


// WHAT IS THIS DOING HERE ? NOT METHOD, NOT ATTRIBUTE..? 
// CREATE getTexture / get Sound?
	void getTexture(){ 
		//texture 
		try {
			URL imageUrl = new URL("https://vignette.wikia.nocookie.net/disney/images/2/2c/Tarzan_transparent.png/revision/latest?cb=20151031043816");
			in = imageUrl.openStream();
			BufferedImage image = ImageIO.read(in);
			in.close();
		} catch(IOException ioe) {
			//log the error
		}
	}

			// sound Tarzan
	public static synchronized void callSound(final String url) {
		new Thread(new Runnable() { // ???
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
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
	
	
					  }).start(); // WHAT IS THIS ?
			}
	}