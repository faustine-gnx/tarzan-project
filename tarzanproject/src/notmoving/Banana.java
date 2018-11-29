package notmoving;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class Banana extends NotLivings {
	
		private static boolean isEaten;
		// constructor
		Banana (int position, String name) {
			super(position,name); }
			int position [][]; 
			String name = "Banana"; 
{
			//texture 
			try {
			    URL imageUrl = new URL("https://www.kisspng.com/png-banana-powder-fruit-cavendish-banana-banana-90233/");
			    InputStream in = imageUrl.openStream();
			    BufferedImage image = ImageIO.read(in);
			    in.close();
			}
			catch (IOException ioe) {
			    //log the error
			}
			
			
			//enduranceProvided 
			    // the "if" clause: banana eaten
			    if ("Banana-isEaten" != null){
			        // the "then" clause: increase current energy 
			       System.out.println("+ 20 points of endurance");
	    
			    }
			}
		
			//overriding finalize method 
			 @Override
			 protected void finalize () throws Throwable { 
				 System.out.println("Banana eaten. Congratulations! Your energy increased!");
		} 
			
		
			}

