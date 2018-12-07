package animals;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.Position;

import com.sun.tools.javac.Main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL; 

public class Lion extends Animal  {
	public Lion getLionPosition;

	// constructor
	public Lion(Position position) {
		super(position, 15, "Lion"); 
	}
	
	public int getAnimalType(){
		return 13;
	}

			{
	
//texture animal 
try {
	    URL imageUrl = new URL("https://www.kisspng.com/png-lion-quest-simulator-tiger-3d-computer-graphics-li-73883/");
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
			          Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=8StqowT8hME") ));
			        clip.open(inputStream);
		        clip.start(); 
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
			}

	public int getLion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	}
	
	