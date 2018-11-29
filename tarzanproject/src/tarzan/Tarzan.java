package tarzan;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.applet.Main;
import tilegame.Level;
import tilegame.Settings;

public class Tarzan { // I would set everything private? since there is no subclass
					  // make getters
	protected String name; 
	protected int[][] position; 
	protected int energy;
	protected int endurance;
	//protected int level; // needed?
	protected int strength;
	protected boolean isAlive ;
	    
	// Constructors
	public Tarzan(int[][] pos, Level lvl, Settings setg){
		this.name = "Tarzan";
		this.position = pos;
		this.energy = lvl.getInitialEnergy();
		this.endurance = setg.getInitialEndurance();
		this.strength = setg.getInitialStrength();
		this.isAlive = true;
		//this.level = setg.getLevel();		
	}


	void fight() {}
	void levelUp() {}
	void showAbility() {}
	void pickFlowe(){}
	void move () {}
	void takePill () {}
	void fieldOfView () {}
	void inReachDistance () {}
	void hasReachedGoal () {
	}
	
{}		
{
	//texture 
		try {
		    URL imageUrl = new URL("https://vignette.wikia.nocookie.net/disney/images/2/2c/Tarzan_transparent.png/revision/latest?cb=20151031043816");
		    InputStream in = imageUrl.openStream();
		    BufferedImage image = ImageIO.read(in);
		    in.close();
		}
		catch (IOException ioe) {
		    //log the error		
} }

			// sound Tarzan
			public static synchronized void callSound(final String url) {
					  new Thread(new Runnable() {
					  // The wrapper thread is unnecessary, unless it blocks on the
					  // Clip finishing; see comments.
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
					  }).start();
			}
	}