package notmoving;
import tarzan.Tarzan;
import tilegame.Position2D;

//import com.sun.tools.javac.Main;



public class Jane extends NotMovings {
	// int position [][] ; in superclass
	//String name = "Jane"; in superclass

	public Object getJanePosition;

	// constructor
	public Jane (Position2D position) {
		super(position,  "JANE");
	}	
	
	public int getNotMovingsType() {
		return 4;
	}
	
	@Override
	public void interact(Tarzan t) {
		t.janeFound();
		//t.getHandler().getHandlerWorld().setWorldNotMovingsNull(notMovingsPosition);
		//System.out.println("New weapon! Strength increased");
	}
}