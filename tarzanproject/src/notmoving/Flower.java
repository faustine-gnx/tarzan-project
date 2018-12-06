package notmoving;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;



public class Flower extends NotLivings {
	
	private boolean isPicked;
	
	// constructor
	public Flower (int[][] position) {
		super(position, "Exotic beautiful pink flower"); 
		this.isPicked = false;
	}
	
	public int getNotLivingsType() {
		return 2;
	}


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
			public void finalize () throws Throwable { 
				 System.out.println("Flower picked");
		} 
			
		
			}

