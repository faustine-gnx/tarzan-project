package notmoving;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import tarzan.Tarzan;
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
	public void interact(Tarzan t) {
		t.takePill();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Yeeaah, feeling better! Energy increased");
	
	}
}