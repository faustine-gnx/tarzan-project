package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.text.Position;

// I THINK WE SHOULD DELETE THIS CLASS

public abstract class janefound { // Just change texture in Jane class
								 // No need for new class
	{
		Position position; 

	//texture 
	try {
	    URL imageUrl = new URL("http://pluspng.com/png-169573.html");
	    InputStream in = imageUrl.openStream();
	    BufferedImage image = ImageIO.read(in);
	    in.close();
	}
	catch (IOException ioe) {
	    //log the error
	}
}
}
