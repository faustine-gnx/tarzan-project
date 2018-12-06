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
import com.sun.tools.javac.Main;
import animals.Animal;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Hut;
import notmoving.Kavurus;
import notmoving.Knife;
import tilegame.Goal;
import tilegame.Level;
import tilegame.Settings; 

public class Tarzan { 
	
	// I would set everything private? since there is no subclass - true 
					  // + make getters
	protected String name; 
	protected int energy;
	protected int endurance;
	protected int level; // M: i added the protected int level if not used we can delete it later 
	protected int strength;
	protected boolean isAlive ; 
	// attribute or method depending on energy? M:--> true : but in order to make the game fails if tarzan is killed? 
	protected int numberOfOpponentsKilled;
	protected int numberOfFlowersPicked;
	private int[][] animalPosition;
	private int[][] positionTarzan;
	    
	// Constructors
	// Constructor called in game:
	// lvl and setg passed as arguments are attributes of game
	public Tarzan(int[][] pos, Level lvl, Settings setg){
		this.name = "Tarzan";
		this.positionTarzan = pos;
		this.energy = lvl.getInitialEnergy();
		this.endurance =setg.getInitialStrength();
		this.isAlive = true;
		this.numberOfOpponentsKilled = 0;
		this.numberOfFlowersPicked = 0;
		this.animalPosition = animalPosition;
		//this.level = setg.getLevel();		
	}

	void fight(Animal a) throws Throwable {
		this.energy -= a.getAnimalStrength();
		this.numberOfOpponentsKilled += 1;
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		a.finalize();}	
	
	// I added a method dead and a method to check conditions
	// if it's dead or alive
	 public void takeDamage(int damage) {
	        //manipulate the amount of damage taken
	        if(this.endurance - damage <= 0) {
	            endurance = 0;
	            isAlive = false;
	            System.out.println("This is the end of the game!");
	        } else {
	            endurance -= damage;
	        }
	    }

 // void levelUp() {} F: what is this used for? 
 // M: i deleted levelUp () I thought we could used to go 
  //from one level to another but useless
	
	 void showAbility() {}
	
	void eatBanana(Banana b) throws Throwable{
		this.endurance += Banana.getEnduranceGiven();
		b.finalize(); // destroy banana
	}
	
	void pickFlower(Flower f) throws Throwable{
		this.numberOfFlowersPicked += 1;
		f.finalize(); // destroy flower
	}
	
	void pickKnife(Knife kn) throws Throwable{
		this.strength += Knife.getStrengthGiven();
		kn.finalize(); // destroy knife
	}
	
	void takePill(Kavurus k) throws Throwable {
		this.energy += Kavurus.getEnergyGiven(); // no idea how much
		k.finalize(); // destroy pill
	}
	
	// added the hut how much energy ? 
	void insideHut(Hut hut) {
		this.energy += Hut.getEnergyGiven(); // how much? type name = new type(); 
	}
	
	// should we add a method also if JaveFound = end of the game in Tarzan class?
	
	void fieldOfView() {} // M: what is this for ? 
	
	void inReachDistance() {} // M: what is this for ? 
	
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
	
	void update() { 
		
		// called at each time step --> M: what does it mean? 
		
		
		// if position of animal or not living = position of tarzan
		// call fight 
		if (animalPosition == positionTarzan);
		{
			try {
				fight(null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		
		// several methods to check the position of not livings == tarzan then method associated 
		
		int[][] KavarusPosition = null ;
		if (KavarusPosition == positionTarzan)
			try {
				takePill (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		int[][] FlowerPosition = null ;
		if (FlowerPosition == positionTarzan)
			try {
				pickFlower (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		int[][] BananaPosition = null ;
		if (BananaPosition == positionTarzan)
			try {
				eatBanana (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		int[][] KnifePosition = null ;
		if (KnifePosition == positionTarzan)
			try {
				pickKnife (null);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// if goals are reached
		// --> message stating goals are reached
		if (hasReachedGoal(null)== true) {
			System.out.println("The goals are reached ! Congratulations");
		}
		
		// decrease energy by value
		// --> value depends on mode: wailing, swimming, swinging
		// M: should we add a class mode or just made the methods inside Tarzan class? 
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
	public void logic(long delta) {
	    float dx = 0;
	    float dy = 0;
	    boolean left = false;
		if (left) {
			dx -= 1;
	    }
	    boolean right = false;
		if (right) {
			dx += 1;
	    }
	    boolean up = false;
		if (up) {
			dy -= 1;
	    }
	    boolean down = false;
		if (down) {
			dy += 1;
	    }

	    if ((dx != 0) || (dy != 0)) {
	    	Playermove(dx * delta * 0.003f, dy * delta * 0.003f);
	    }
	}

	// listen to the player move and check if it is valid location on the map
	public boolean Playermove(float dx, float dy) {
		float x = 0;
		float nx = x + dx; 
		float y = 0;
		float ny = y + dy; 
		if (validLocation(nx, ny)) { 
			x = nx; y = ny; 
			return true; 
		} else { 
		return false; 
		}	
	}

	// i have a doubt with validLocation. did we say that rock are invalid location?
	// should we check if it's valid location in this case? 
	// should we create "blocked location"?
	private boolean validLocation(float nx, float ny) {
		return true;
	}
	}


