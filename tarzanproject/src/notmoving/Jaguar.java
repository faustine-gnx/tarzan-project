package notmoving;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import tilegame.Position2D;

//import com.sun.tools.javac.Main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL; 

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
	public int getNotMovingsType() {
		return 0;
	}
	
	@Override
	public void finalize () throws Throwable { //changed to public
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