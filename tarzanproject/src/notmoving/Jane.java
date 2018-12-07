package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.text.Position;

import com.sun.tools.javac.Main;

//import com.sun.tools.javac.Main;



public class Jane extends NotLivings {
	// int position [][] ; in superclass
	//String name = "Jane"; in superclass

	public Object getJanePosition;

	// constructor
	public Jane (Position position) {
		super(position,  "Jane");
	}	
	
	public int getNotLivingsType() {
		return 4;
	}
	
	{

	//texture --> get Texture? 
	try {
	    URL imageUrl = new URL("http://pluspng.com/png-169572.html");
	    InputStream in = imageUrl.openStream();
	    BufferedImage image = ImageIO.read(in);
	    in.close();
	}
	catch (IOException ioe) {
	    //log the error
	}
	
}

//sound Tarzan find Jane music starts 
			public static synchronized void callSound(final String url) {
					  new Thread(new Runnable() {
					  // The wrapper thread is unnecessary, unless it blocks on the
					  // Clip finishing; see comments.
					    public void run() {
					      try {
					        Clip clip = AudioSystem.getClip();
					        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
					          Main.class.getResourceAsStream("/path/to/sounds/" + new URL ("https://www.youtube.com/watch?v=zc3MnoSS5Hw") ));
					        clip.open(inputStream);
					        clip.start(); 
					      } catch (Exception e) {
					        System.err.println(e.getMessage());
					      }
					    }
					  }).start();
			}

			public int getY() {
				// TODO Auto-generated method stub
				return 0;
			}

			public int getX() {
				// TODO Auto-generated method stub
				return 0;
			}
	}