package notmoving;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;



public abstract class Flower extends NotLivings {
		// constructor
		Flower (int position, String name) {
			super(position,name); }
			int position [][]; 
			String name = "Exotic beatiful pink flower"; 
{
			//texture 
			try {
			    URL imageUrl = new URL("https://www.kisspng.com/png-drawing-flower-sketch-hand-painted-flowers-86836/");
			    InputStream in = imageUrl.openStream();
			    BufferedImage image = ImageIO.read(in);
			    in.close();
			}
			catch (IOException ioe) {
			    //log the error
			}
}
			//overriding finalize method 
			 @Override
			 protected void finalize () throws Throwable { 
				 System.out.println("Flower picked");
		} 
			
		
			}

