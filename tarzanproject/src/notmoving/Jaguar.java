package notmoving;

import java.util.Random;
import tarzan.Tarzan;
import tilegame.Position2D;

public class Jaguar extends NotMovings { // ACTUALLY JAGUAR, NOT TIGER
	private int jaguarStrength;
	// constructor
	public Jaguar(Position2D position) {
		super(position, "JAGUAR"); 
		jaguarStrength = 25;
	}

	public int getJaguarStrength() {
		return jaguarStrength;
	}
	
	@Override
	public void interact(Tarzan t) {
		t.takeDamage(jaguarStrength);
		// need to destroy animal ! (for optimization of memory)
		// a finalize () can we do this? I think so. I completed then we can decide
		Random rand = new Random();
		int winningChance = t.getStrength()*rand.nextInt(5);
		if (winningChance > 100) {
			t.killsJaguar();
			finalize();
			t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		} else {
			//t.backToPreviousPosition();
			System.out.println("Jaguar beat you, keep on moving, you might be luckier next time!");
		}
	}
	
	@Override
	public int getNotMovingsType() {
		return 0;
	}
	
	@Override
	public void finalize() { //changed to public
		System.out.println("Jaguar succesfully killed!");
	} 
}
/*{
	
//texture animal 
try {
	    URL imageUrl = new URL("http://pluspng.com/png-119343.html");
	    InputStream in = imageUrl.openStream();
	    BufferedImage image = ImageIO.read(in);
	    in.close();
	}
	catch (IOException ioe) {
	    //log the error
	}
			}

	// sound animal
	public static synchronized void callSound(final String url) {
			  new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			        Clip clip = AudioSystem.getClip();
			        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
			          Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=SkFx45lxlvA") ));
			        clip.open(inputStream);
		        clip.start(); 
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
			}

*/