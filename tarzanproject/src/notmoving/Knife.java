package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import tarzan.Tarzan;
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
	
	@Override
	public void interact(Tarzan t) {
		t.pickKnife();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Wooow, new weapon! Strength increased");
	}
}
