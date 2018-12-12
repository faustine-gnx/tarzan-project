package animals;
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

public class Crocodile extends Animal {
	// constructor
	public Crocodile(Position2D position) {
		super(position, 6, "Crocodile"); 
	}
 
	public int getAnimalType(){
		return 11;
	}
}
/*		
		{
//texture animal 
		try {
			URL imageUrl = new URL("https://purepng.com/photo/1938/animals-green-crocodile");
			InputStream in = imageUrl.openStream();
			BufferedImage image = ImageIO.read(in);
			in.close(); }
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
			          Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=5u4N2Ls_PSw") ));
			        clip.open(inputStream);
		        clip.start(); 
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start(); }

}*/
