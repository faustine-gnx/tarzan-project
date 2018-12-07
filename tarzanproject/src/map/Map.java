package map;
import java.util.Random;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Hut;
import notmoving.Jane;
import notmoving.Kavurus;
import notmoving.Knife;
import notmoving.NotLivings;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.text.Position;
import animals.Animal;
import animals.Crocodile;
import animals.Elephant;
import animals.Lion;
import animals.Snake;
import animals.Tiger;
import interfaces.Drawable;
import tarzan.Tarzan;
import tilegame.Level;
import tilegame.Settings;

public class Map implements Drawable {
	private final static int SIZE_MAP = 20;	
	double[][] landMap; // land type map // Size_map
	boolean[][] freePositions; // Size_map 
	
	//List<int[][],> occupationList = new ArrayList<>(); // 
	List<Object> positionnableList = new ArrayList <Object>();
	
	//Level lvl; // need for attribute ? or attribute of game? and getters? IN GAME
	//Settings setg; // need for attribute ? or attribute of game? and getters? IN GAME
	
	private Timer gameTimer;
	//private TimerTask task; // TimerTask is an abstract class
	
	//public final int sizeLandMap; //depends on level
	//private final static int[][] NOISE_MAP;
	//private final int[][] landMap; //initialized for each new game
	
	List<Animal> mapAnimals = new ArrayList<Animal>();
	List<NotLivings> mapNotLivings = new ArrayList<NotLivings>();
	
	//Animal[] MapAnimals;
	//NotLivings[] MapNotLivings;
	//List<Animal> animals = new ArrayList<Animal>();
	//List<NonLiving> nonLivings = new ArrayList<NonLiving>();
	
	Tarzan mapTarzan;
	
	
	public Map(Level l, Settings s){ // need to be public because not in same package as Game, which is calling it
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		this.landMap = mapGen.createMap(SIZE_MAP);
		java.util.Arrays.fill(this.freePositions[0], false);
		java.util.Arrays.fill(this.freePositions[1], false);
		//mapIm.visualize(array, "generatedMap");
		this.mapTarzan = new Tarzan(randomPosition(), l, s);
		createPositionables(l); 
	}
	
	public Map(Position tarzanPosition, Level l, Settings s){
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		this.landMap = mapGen.createMap(SIZE_MAP);
		java.util.Arrays.fill(this.freePositions[0], true);
		java.util.Arrays.fill(this.freePositions[1], true);
		freePositions[((Tarzan) tarzanPosition).get()][((Tarzan) tarzanPosition).get()] = false;
		//mapIm.visualize(array, "generatedMap");
		this.mapTarzan = new Tarzan(tarzanPosition, l, s);
		createPositionables(l); 
	}
	
	
	private void createPositionables(Level lvl) {
		//int levelNumber = lvl.getLevelNumber();
		for (int i = 0; i < lvl.getNumberOfOpponents(); i++) {
			createOneOfEachAnimal();
		}
		
		// create NotLivings
		createJane();
		
		for (int i = 0; i < lvl.getNumberOfBananas(); i++) { // all the same for now
			createOneBanana();
			createOneFlower();
			createOneKavurus();
			createOneKnife();
		}
		
		for (int i = 0; i < lvl.getNumberOfHuts(); i++) {
			createOneHut();
		}
	}
	
	
	// ALL THIS IN GAME? --> NO
	
	private void createOneOfEachAnimal() { // or each animal apart? 
		//random position checked if not occupied
		// create Lion
		Lion l = new Lion(randomPosition());
		mapAnimals.add(l);
		positionnableList.add(l);
		freePositions[((Lion) l.getLionPosition).getX()][((Lion) l.getLionPosition).getY()] = false;
		
		// create Tiger
		Tiger t = new Tiger(randomPosition());
		mapAnimals.add(t);
		positionnableList.add(t);
		freePositions[((Tiger) t.getTigerPosition).getX()][((Tiger) t.getTigerPosition).getY()] = false;
		
		Snake s = new Snake(randomPosition());
		mapAnimals.add(s);
		positionnableList.add(s);
		freePositions[((Snake) s.getSnakePosition).getX()][((Snake) s.getSnakePosition).getY()] = false;
		
		Elephant e = new Elephant(randomPosition());
		mapAnimals.add(e);
		positionnableList.add(e);
		freePositions[((Elephant) e.getElephantPosition).getX()][((Elephant) e.getElephantPosition).getY()] = false;
		
		Crocodile c = new Crocodile(randomPosition());
		mapAnimals.add(c);
		positionnableList.add(c);
		freePositions[((Crocodile) c.getCrocodilePosition).getX()][((Crocodile) c.getCrocodilePosition).getY()] = false;
	}
	
	private Position randomPosition(){ // return a random position which is free
		Random rand = null;
		int x = rand.nextInt(SIZE_MAP);
		int y = rand.nextInt(SIZE_MAP);
		
		if (isPositionFree(Position(x,y))) {
			return Position(x,y); 
		} else {
			return randomPosition(); // find other position - not sure if it will work
		}
	}
	
	private Position Position(int x, int y) {
		return null;
	}

	private boolean isPositionFree(Position pos) { 
		return freePositions[pos.getX()][pos.getY()];
	}
	
	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotLivings.add(b);
		positionnableList.add(b);
		freePositions[((Banana) b.getBananaPosition).getX()][((Banana) b.getBananaPosition).getY()] = false;
	}
	
	private void createOneFlower() {
		Flower f = new Flower(randomPosition());
		mapNotLivings.add(f);
		positionnableList.add(f);
		freePositions[((Flower) f.getFlowerPosition).getX()][((Flower) f.getFlowerPosition).getY()] = false;
	}
	
	private void createOneHut() {
		Hut h = new Hut(randomPosition());
		mapNotLivings.add(h);
		positionnableList.add(h);
		freePositions[((Hut) h.getHutPosition).getX()][((Hut) h.getHutPosition).getY()] = false;
	}
	
	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotLivings.add(j);
		positionnableList.add(j);
		freePositions[((Jane) j.getJanePosition).getX()][((Jane) j.getJanePosition).getY()] = false;
	}
	
	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[((Kavurus) k.getKavurusPosition).getX()][((Kavurus) k.getKavurusPosition).getY()] = false;
	}
	
	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[((Knife) k.getKnifePosition).getY()][((Knife) k.getKnifePosition).getY()] = false;
}
	
	public void run() { // wrt timer
		int secondsPassed = 0;
		secondsPassed++; 
		System.out.println ("Seconds passed:" + secondsPassed);
	}	
	
	//lvl.getGoal()
	//lvl.getInitialEnergy()
	public void evolve() {}
	public void draw() {}
	private void drawWorld() {} // called in draw to draw animals and nonlivings in field of view
	private void drawPlayer() {}
	//public String getTextureForDrawing() {}
	
	
}