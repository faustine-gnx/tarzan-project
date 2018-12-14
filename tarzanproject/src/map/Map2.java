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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import animals.Animal;
import animals.Crocodile;
import animals.Elephant;
import animals.Lion;
import animals.Snake;
import animals.Tiger;
import gui.Assets;
import imageloader.ImageLoader;
import interfaces.Drawable;
import tarzan.Tarzan;
import tilegame.Level;
import tilegame.Settings;
import tilegame.Position2D;

// FOR NOW: NO FLOWER, NO HUT, ONLY "TIGER"=JAGUAR

public class Map2 implements Drawable {
	private final static int SIZE_MAP = 8;	
	private final int PIXEL_SCALE = 500/SIZE_MAP;
	protected Level level;
	protected double[][] landMap; // land type map // Size_map; set directly double[SIZE_MAP][SIZE_MAP]?
	// free = no tarzan, no animal, no notlivings, not water
	protected boolean[][] freePositions; // set directly boolean[SIZE_MAP][SIZE_MAP]?
	protected boolean[][] fieldOfViewMatrix; // true if in fov, false otherwise ; set directly double[SIZE_MAP][SIZE_MAP]

	List<Object> positionnableList = new ArrayList <Object>();

	List<Animal> mapAnimals = new ArrayList<Animal>();
	List<NotLivings> mapNotLivings = new ArrayList<NotLivings>();
	List<Position2D> tarzanFieldOfView = new ArrayList<Position2D>();
	Tarzan mapTarzan;	
	
	public Map2(int strength, int endurance, int lvl){ // 
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(2*lvl, 1, 1); //  2*lvl: increase number of water tiles with level
		this.landMap = mapGen.createMap(SIZE_MAP);
		this.level = new Level(lvl, SIZE_MAP);
		this.freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		this.fieldOfViewMatrix = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i=0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(this.freePositions[i], true); // or when rock put false
			//System.out.println(Arrays.toString(freePositions[0]));
		}
		setWaterNotPositionFree();
		
		// create Tarzan here or in Game2?
		this.mapTarzan = new Tarzan(new Position2D(0,0));//, this.level, strength, endurance);
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false;

		
		createPositionables(this.level); 
		//setTarzanFieldOfView(); 
		//setFieldOfViewMatrix();
		for (int i=0; i < SIZE_MAP; i++) {
			System.out.println(Arrays.toString(freePositions[i]));
		}
	}
	

	public void draw() {

	}

	public Color getColor(int x, int y) { // maybe MapImage is not necessary
		if (this.landMap[x][y] <= 0.5) { // if value equals 0, fill with water
			return Color.GREEN;
		} else if (this.landMap[x][y] > 0.5) { // if value equals 1, fill with forest
			return Color.BLUE; // put grass green and delete forest?
		} else {
			return Color.DARK_GRAY;
		}
	}

	
	public Graphics setMapDrawing(Graphics g) {
		// new drawing each time the function is called

		for (int x = 0; x < SIZE_MAP; x++) {
			for (int y = 0; y < SIZE_MAP; y++) {
				//if (this.fieldOfViewMatrix[x][y] == true) { // uncomment when tarzan is created
					g.setColor(getColor(x, y));
					g.fillRect(y*PIXEL_SCALE, x*PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);
				//} else {
					g.setColor(Color.BLACK);
					//g.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);
				//}
			}
		}		
		//g2d.dispose();
		drawTarzan(g);
		drawAnimals(g);
		drawNotLivings(g);
		//drawObjects
		return g;
	}
	
	public void drawTarzan(Graphics g) {
		g.drawImage(Assets.TARZAN_NORMAL, this.mapTarzan.getTarzanPosition().getX()*PIXEL_SCALE, this.mapTarzan.getTarzanPosition().getY()*PIXEL_SCALE, null);
	}
	
	public void drawAnimals(Graphics g) {
		for (int i=0; i<this.mapAnimals.size(); i++) {
		g.drawImage(Assets.TIGER, this.mapAnimals.get(i).getAnimalPosition().getX()*PIXEL_SCALE, this.mapAnimals.get(i).getAnimalPosition().getY()*PIXEL_SCALE, null);
		}
	}
	
	public void drawNotLivings(Graphics g) {
		for (int i=0; i<this.mapNotLivings.size(); i++) {
			//BufferedImage notLivingImg = Assets.getImageFromString(this.mapNotLivings.get(i).getName());
			g.drawImage(Assets.getImageFromString(this.mapNotLivings.get(i).getName()), this.mapNotLivings.get(i).getNotLivingsPosition().getX()*PIXEL_SCALE, this.mapNotLivings.get(i).getNotLivingsPosition().getY()*PIXEL_SCALE, null);
		}
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
			createOneKavurus();
			createOneKnife();
		}
	}
	
	private void createOneOfEachAnimal() { // or each animal apart? 

		// create Tiger
		Tiger t = new Tiger(randomPosition());
		mapAnimals.add(t);
		positionnableList.add(t);
		freePositions[t.getAnimalPosition().getY()][t.getAnimalPosition().getX()] = false;

}

	private Position2D randomPosition(){ // return a random position which is free
		Random rand = new Random();
		int x = rand.nextInt(SIZE_MAP); // print this out to check value
		int y = rand.nextInt(SIZE_MAP);
		Position2D pos = new Position2D(x,y);
		if (freePositions[pos.getY()][pos.getX()]) {
			freePositions[pos.getY()][pos.getX()] = false;
			return pos; 
		} else {
			return randomPosition(); // find other position - not sure if it will work
		}
	}
	
	/*private Position2D randomPosition(){ // return a random position which is free
		Random rand = new Random();
		int x = rand.nextInt(SIZE_MAP); // print this out to check value
		int y = rand.nextInt(SIZE_MAP);
		Position2D pos = new Position2D(x,y);
		while(!freePositions[pos.getX()][pos.getY()]) {
			pos = randomPosition();
		}
		return pos;
	}
	*/
	private boolean isPositionFree(Position2D pos) { 
		return freePositions[pos.getY()][pos.getX()];
	}
	
	private void setWaterNotPositionFree() { 
		for (int x=0; x<SIZE_MAP; x++) {
			for (int y=0; y<SIZE_MAP; y++) {
				if (landMap[x][y] > 0.5) { // > 0.5 = water
					this.freePositions[x][y] = false;
				}
			}
		}
	}

	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotLivings.add(b);
		positionnableList.add(b);
		freePositions[b.getNotLivingsPosition().getY()][b.getNotLivingsPosition().getX()] = false;
	}

	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotLivings.add(j);
		positionnableList.add(j);
		freePositions[j.getNotLivingsPosition().getY()][j.getNotLivingsPosition().getX()] = false;
	}

	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotLivingsPosition().getY()][k.getNotLivingsPosition().getX()] = false;
	}

	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotLivings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotLivingsPosition().getY()][k.getNotLivingsPosition().getX()] = false;
	}
	
	private void updateMap() { // public maybe
		
	}

}