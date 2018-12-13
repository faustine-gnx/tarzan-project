package map;
import java.util.Random;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Hut;
import notmoving.Jane;
import notmoving.Kavurus;
import notmoving.Knife;
import notmoving.NotLivings;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
import tilegame.Position2D;

public class Map implements Drawable {
	private final static int SIZE_MAP = 20;	
	private final int PIXEL_SCALE = 10;
	protected Level level;
	protected double[][] landMap; // land type map // Size_map; set directly double[SIZE_MAP][SIZE_MAP]?
	protected boolean[][] freePositions; // Size_map ; set directly double[SIZE_MAP][SIZE_MAP]
	protected boolean[][] fieldOfViewMatrix; // true if in fov, false otherwise ; set directly double[SIZE_MAP][SIZE_MAP]
	MapImage mapIm; 
	Graphics2D g2d;

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
	List<Position2D> tarzanFieldOfView = new ArrayList<Position2D>();

	//Animal[] MapAnimals;
	//NotLivings[] MapNotLivings;
	//List<Animal> animals = new ArrayList<Animal>();
	//List<NonLiving> nonLivings = new ArrayList<NonLiving>();

	Tarzan mapTarzan;


	public Map(Level l, Settings s){ // need to be public because not in same package as Game, which is calling it
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		this.landMap = mapGen.createMap(SIZE_MAP);
		// Can we do this:
		// this.landMap = SimplexNoiseGenerator(10, 0.7, 0.008).createMap(SIZE_MAP);
		//this.mapIm = new MapImage();
		BufferedImage bufferedImage = new BufferedImage(SIZE_MAP, SIZE_MAP, BufferedImage.TYPE_INT_RGB);
		this.g2d = bufferedImage.createGraphics();
		java.util.Arrays.fill(this.freePositions[0], true); // or when rock put false
		java.util.Arrays.fill(this.freePositions[1], true);
		//mapIm.visualize(array, "generatedMap");
		this.mapTarzan = new Tarzan(randomPosition(), l, s);
		createPositionables(l); 
		setTarzanFieldOfView(); 
		setFieldOfViewMatrix();
	}

	public Map(Position2D tarzanPosition, Level l, Settings s){
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		this.landMap = mapGen.createMap(SIZE_MAP);
		// this.landMap = SimplexNoiseGenerator(10, 0.7, 0.008).createMap(SIZE_MAP);
		//this.mapIm = new MapImage();
		BufferedImage bufferedImage = new BufferedImage(SIZE_MAP, SIZE_MAP, BufferedImage.TYPE_INT_RGB);
		this.g2d = bufferedImage.createGraphics();
		java.util.Arrays.fill(this.freePositions[0], true);
		java.util.Arrays.fill(this.freePositions[1], true);
		freePositions[tarzanPosition.getX()][tarzanPosition.getY()] = false;
		//mapIm.visualize(array, "generatedMap");
		this.mapTarzan = new Tarzan(tarzanPosition, l, s);
		createPositionables(l); 
		setTarzanFieldOfView(); 
		setFieldOfViewMatrix();
	}
	
	
	public Map(int strength, int endurance, int lvl){ // need to be public because not in same package as Game, which is calling it
		this.level = new Level(lvl, SIZE_MAP);
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		this.landMap = mapGen.createMap(SIZE_MAP);
		// Can we do this:
		// this.landMap = SimplexNoiseGenerator(10, 0.7, 0.008).createMap(SIZE_MAP);
		//this.mapIm = new MapImage();
		BufferedImage bufferedImage = new BufferedImage(SIZE_MAP, SIZE_MAP, BufferedImage.TYPE_INT_RGB);
		this.g2d = bufferedImage.createGraphics();
		this.freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		this.fieldOfViewMatrix = new boolean[SIZE_MAP][SIZE_MAP];
		java.util.Arrays.fill(this.freePositions[0], true); // or when rock put false
		java.util.Arrays.fill(this.freePositions[1], true);
		//mapIm.visualize(array, "generatedMap");
		this.mapTarzan = new Tarzan(randomPosition(), this.level, strength, endurance);
		createPositionables(this.level); 
		setTarzanFieldOfView(); 
		setFieldOfViewMatrix();
	}
	
	public int getSize() {
		return SIZE_MAP;
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
		freePositions[l.getAnimalPosition().getX()][l.getAnimalPosition().getY()] = false;

		// create Tiger
		Tiger t = new Tiger(randomPosition());
		mapAnimals.add(t);
		positionnableList.add(t);
		freePositions[t.getAnimalPosition().getX()][t.getAnimalPosition().getY()] = false;

		Snake s = new Snake(randomPosition());
		mapAnimals.add(s);
		positionnableList.add(s);
		freePositions[s.getAnimalPosition().getX()][s.getAnimalPosition().getY()] = false;

		Elephant e = new Elephant(randomPosition());
		mapAnimals.add(e);
		positionnableList.add(e);
		freePositions[e.getAnimalPosition().getX()][e.getAnimalPosition().getY()] = false;

		Crocodile c = new Crocodile(randomPosition());
		mapAnimals.add(c);
		positionnableList.add(c);
		freePositions[c.getAnimalPosition().getX()][c.getAnimalPosition().getY()] = false;
}

	private Position2D randomPosition(){ // return a random position which is free
		Random rand = new Random();
		int x = rand.nextInt(SIZE_MAP); // print this out to check value
		int y = rand.nextInt(SIZE_MAP);
		Position2D pos = new Position2D(x,y);
		if (isPositionFree(pos)) {
			return pos; 
		} else {
			return randomPosition(); // find other position - not sure if it will work
		}
	}


	// F: what is this?
	/*private Position Position(int x, int y) {
		return null;
	}*/

	private boolean isPositionFree(Position2D pos) { 
		return freePositions[pos.getX()][pos.getY()];
	}

	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotLivings.add(b);
		positionnableList.add(b);
		freePositions[b.getNotLivingsPosition().getX()][b.getNotLivingsPosition().getY()] = false;
	}

	private void createOneFlower() {
		Flower f = new Flower(randomPosition());
		mapNotLivings.add(f);
		positionnableList.add(f);
		freePositions[f.getNotLivingsPosition().getX()][f.getNotLivingsPosition().getY()] = false;
	}

	private void createOneHut() {
		Hut h = new Hut(randomPosition());
		mapNotLivings.add(h);
		positionnableList.add(h);
		freePositions[h.getNotLivingsPosition().getX()][h.getNotLivingsPosition().getY()] = false;
	}

	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotLivings.add(j);
		positionnableList.add(j);
		freePositions[j.getNotLivingsPosition().getX()][j.getNotLivingsPosition().getY()] = false;
	}

	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotLivingsPosition().getX()][k.getNotLivingsPosition().getY()] = false;
	}

	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotLivingsPosition().getY()][k.getNotLivingsPosition().getY()] = false;
	}

	public void run() { // wrt timer
		int secondsPassed = 0;
		secondsPassed++; 
		System.out.println ("Seconds passed:" + secondsPassed);
	}	


	public boolean hasAnimal(Position2D p) {
		for (int i=0 ; i < mapAnimals.size() ; ++i) { 
			Position2D posAnim = new Position2D(mapAnimals.get(i).getAnimalPosition()) ; 
			if (p.isEqual(posAnim)) { 
				return true; 
			} 
		}
		return false; 
	}

	public boolean hasNotLivings(Position2D p) {
		for (int i=0 ; i < mapNotLivings.size() ; ++i) { 
			Position2D posNotLiv = new Position2D(mapNotLivings.get(i).getNotLivingsPosition()) ; 
			if (p.isEqual(posNotLiv)) { 
				return true; 
			} 
		}
		return false; 
	}


	public void setTarzanFieldOfView() { // return only positions of field of view OR
		// return boolean map of size_map*size_map with true if position is in FoV ???
		// is tarzanPosition itself part of the field of view? --> i would say yes
		int radius = mapTarzan.getFieldOfViewRadius();
		int size = radius*2 + 1; 
		for (int x = 0; x < size; x++) { // or radius and do -radius to +radius in x and y directions
			for (int y=0; y < size; y++) {
				this.tarzanFieldOfView.add(new Position2D((x + mapTarzan.getTarzanPosition().getX() - radius), (y + mapTarzan.getTarzanPosition().getY() - radius)));
			}
		}		
	}

	public List<Position2D> getTarzanFieldOfView() { // return only positions of field of view OR
		// return boolean map of size_map*size_map with true if position is in FoV ???
		// is tarzanPosition itself part of the field of view? --> i would say yes
		int radius = mapTarzan.getFieldOfViewRadius();
		int size = radius*2 + 1; 
		for (int x = 0; x < size; x++) { // or radius and do -radius to +radius in x and y directions
			for (int y=0; y < size; y++) {
				this.tarzanFieldOfView.add(new Position2D((x + mapTarzan.getTarzanPosition().getX() - radius), (y + mapTarzan.getTarzanPosition().getY() - radius)));
			}
		}
		return this.tarzanFieldOfView;
	}

	public void setFieldOfViewMatrix() { 
		java.util.Arrays.fill(this.fieldOfViewMatrix[0], false);
		java.util.Arrays.fill(this.fieldOfViewMatrix[1], false);
		int radius = mapTarzan.getFieldOfViewRadius();
		int size = radius*2 + 1; 
		for (int x = 0; x < size; x++) { // or radius and do -radius to +radius in x and y directions
			for (int y=0; y < size; y++) {
				if (((x + mapTarzan.getTarzanPosition().getX() - radius) > 0) && ((y + mapTarzan.getTarzanPosition().getY() - radius) > 0)) {
					this.fieldOfViewMatrix[x + mapTarzan.getTarzanPosition().getX() - radius][y + mapTarzan.getTarzanPosition().getY() - radius] = true;
				}
				if (((x + mapTarzan.getTarzanPosition().getX() + radius) < SIZE_MAP) && ((y + mapTarzan.getTarzanPosition().getY() + radius) < SIZE_MAP)) {
					this.fieldOfViewMatrix[x + mapTarzan.getTarzanPosition().getX() + radius][y + mapTarzan.getTarzanPosition().getY() + radius] = true;
				}
			}
		}
	}

	public boolean isInFieldOfView(Position2D p) {
		for (int i=0 ; i < tarzanFieldOfView.size() ; ++i) { 
			if (p.isEqual(tarzanFieldOfView.get(i))) { 
				return true; 
			} 
		}
		return false; 
	}

	//lvl.getGoal()
	//lvl.getInitialEnergy()
	public void evolve() {}

	public void draw() {

	}

	public Color getColor(int x, int y) { // maybe MapImage is not necessary
		if (this.landMap[x][y] <= 0.01) { // if value equals 0, fill with water
			return Color.BLUE;

		} else if (this.landMap[x][y] <= 0.45) { // if value equals 2, fill with grass
			return Color.YELLOW;

		} else if (this.landMap[x][y] <= 0.9) { // if value equals 1, fill with forest
			return Color.GREEN; // put grass green and delete forest?

		} else if (this.landMap[x][y] > 0.9) { // if value equals 1, fill with rocks
			return Color.DARK_GRAY;
		} else {
			return Color.BLACK;
		}
	}

	public void drawMap() {
		// new drawing each time the function is called
		BufferedImage bufferedImage = new BufferedImage(SIZE_MAP, SIZE_MAP, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, SIZE_MAP, SIZE_MAP);

		for (int x = 0; x < SIZE_MAP; x++) {
			for (int y = 0; y < SIZE_MAP; y++) {
				if (this.fieldOfViewMatrix[x][y] == true) {
					g2d.setColor(getColor(x, y));
				} else {
					g2d.setColor(Color.BLACK);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);
				}
			}
		}		
		g2d.dispose();
	}
	
	public Graphics2D getMapDrawing() {
		// new drawing each time the function is called
		BufferedImage bufferedImage = new BufferedImage(SIZE_MAP, SIZE_MAP, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, SIZE_MAP, SIZE_MAP);

		for (int x = 0; x < SIZE_MAP; x++) {
			for (int y = 0; y < SIZE_MAP; y++) {
				if (this.fieldOfViewMatrix[x][y] == true) {
					g2d.setColor(getColor(x, y));
				} else {
					g2d.setColor(Color.BLACK);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);
				}
			}
		}		
		g2d.dispose();
		return g2d;
	}

	private void drawWorld() { // called in draw to draw animals and nonlivings in field of view
		// for animal in animalList
			// call draw
		// for nonliving in notlivingsList
			// call draw
	}
	
	private void drawPlayer() {
		// draw Tarzan
	}
	
	//public String getTextureForDrawing() {}


}