package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import tilegame.Position2D;

public class Kavurus extends NotMovings {
	protected static final int ENERGY_GIVEN = 50; // No idea how much
	public Object getAnimalPosition;
	public Kavurus getKavurusPosition;
	
	// constructor
	public Kavurus (Position2D position) {
		super(position, "KAVURUS"); 
	}
	
	public static int getEnergyGiven() {
		return ENERGY_GIVEN;
	}
	
	public int getNotMovingsType() {
		return 5;
	}
	
	@Override
	public void finalize () throws Throwable { 
		System.out.println("Kavurus pill taken. Congratulations! Your energy increased!");
	}
		
/*
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


*/
		
}