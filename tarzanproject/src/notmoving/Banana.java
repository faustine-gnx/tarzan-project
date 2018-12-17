package notmoving;

import tarzan.Tarzan;
import tilegame.Position2D;

public class Banana extends NotMovings {

	//private boolean isEaten; I don't thing we need this
	protected static final int ENDURANCE_GIVEN = 25; // no idea how much
	public Object getBananaPosition;

	// constructor
	public Banana (Position2D position) {
		super(position, "BANANA"); 
		//this.isEaten = false;
	}

	public static int getEnduranceGiven() {
		return ENDURANCE_GIVEN;
	}


	public int getNotMovingsType() {
		return 1;
	}

	@Override
	public void interact(Tarzan t) {
		t.eatBanana();
		t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		System.out.println("Yummy yummy, banana eaten! Endurance increased");
	}
}

