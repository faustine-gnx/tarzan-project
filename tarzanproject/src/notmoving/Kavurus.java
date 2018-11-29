package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class Kavurus extends NotLivings {
		// constructor
		Kavurus (int position, String name) {
			super(position,name); }
			int position [][]; 
			String name = "Kavurus magic pill"; 
{
			//texture 
			try {
			    URL imageUrl = new URL("https://www.kisspng.com/png-pill-png-61797/");
			    InputStream in = imageUrl.openStream();
			    BufferedImage image = ImageIO.read(in);
			    in.close();
			}
			catch (IOException ioe) {
			    //log the error
			}
			
			//enduranceProvided 
		    // the "if" clause: banana eaten
		    if ("Kavurus-isFound" != null){
		        // the "then" clause: increase current energy 
		       System.out.println("Your energy increase 100 points");
    
		    }
		}
		
			}