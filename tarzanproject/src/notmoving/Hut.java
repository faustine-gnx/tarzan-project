package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.text.Position;


public class Hut extends NotLivings {
	public Object getHutPosition;

	// constructor
	public Hut (Position position) {
		super(position, "Cosy hut"); 
	}
	
	public int getNotLivingsType() {
		return 3;
	}
	
{
			//texture 
			try {
			    URL imageUrl = new URL("http://naturelovervillage.com/wp-content/uploads/2013/01/hut-1.png");
			    InputStream in = imageUrl.openStream();
			    BufferedImage image = ImageIO.read(in);
			    in.close();
			}
			catch (IOException ioe) {
			    //log the error
			}

//enduranceProvided 
// the "if" clause: banana eaten
if ("Hut-isFound" != null){
    // the "then" clause: increase current energy 
   System.out.println("+ 20 points of energy");

} 
}

public static int getEnergyGiven() {
	// TODO Auto-generated method stub
	return 0;
}

public int getX() {
	// TODO Auto-generated method stub
	return 0;
}

public int getY() {
	// TODO Auto-generated method stub
	return 0;
}
}	