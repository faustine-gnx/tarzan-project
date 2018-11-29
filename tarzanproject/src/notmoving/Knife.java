package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class Knife extends NotLivings {
		// constructor
		Knife (int position, String name) {
			super(position,name); }
			int position [][]; 
			String name = "Harmful knife"; 
{
			//texture 
			try {
			    URL imageUrl = new URL("http://www.pngpix.com/download/military-knife-png-transparent-image");
			    InputStream in = imageUrl.openStream();
			    BufferedImage image = ImageIO.read(in);
			    in.close();
			}
			catch (IOException ioe) {
			    //log the error
			}
			
			//enduranceProvided 
		    // the "if" clause: banana eaten
		    if ("Knife-isFound" != null){
		        // the "then" clause: increase current energy 
		       System.out.println("+ 20 points of strength");
    
		    }
		}
			//overriding finalize method 
			 @Override
			 protected void finalize () throws Throwable { 
				 System.out.println("Knife picked");
		} 
			
		
			}
