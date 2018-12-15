package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import tilegame.Position2D;

public class Knife extends NotMovings {
	protected static final int STRENGTH_GIVEN = 25; // no idea how much
	public Object getKnifePosition;
	
	// constructor
	public Knife (Position2D position) {
		super(position, "KNIFE"); 
	}
	
	public static int getStrengthGiven() {
		return STRENGTH_GIVEN;
	}
	
	public int getNotMovingsType() {
		return 6;
	}
	
	//overriding finalize method 
	@Override
	public void finalize () throws Throwable { 
		 System.out.println("Knife picked");
	}
/*
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
			
*/
}
