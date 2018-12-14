package notmoving;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import tilegame.Position2D;



public class Flower extends NotLivings {
	
	private boolean isPicked;
	public Object getFlowerPosition;
	
	// constructor
	public Flower (Position2D position) {
		super(position, "FLOWER"); 
		this.isPicked = false;
	}
	
	public int getNotLivingsType() {
		return 2;
	}
	
	//overriding finalize method 
	@Override
	public void finalize () throws Throwable { 
		 System.out.println("Flower picked");
} 

/*
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
			
			

		*/
}

