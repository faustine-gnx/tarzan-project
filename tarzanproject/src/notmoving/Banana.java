package notmoving;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.text.Position;

public class Banana extends NotLivings {

	//private boolean isEaten; I don't thing we need this
	protected static final int ENDURANCE_GIVEN = 10; // no idea how much
	public Object getBananaPosition;

	// constructor
	public Banana (Position position) {
		super(position, "Banana"); 
		//this.isEaten = false;
	}

	public static int getEnduranceGiven() {
		return ENDURANCE_GIVEN;
	}


	public int getNotLivingsType() {
		return 1;
	}


	//overriding finalize method 
	@Override
	public void finalize () throws Throwable { 
		System.out.println("Banana eaten. Congratulations! Your endurance increased!");
	} 

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

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public int getBanana() {
		// TODO Auto-generated method stub
		return 0;
	}




}

