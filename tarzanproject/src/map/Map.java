package map;
import java.util.Random;
import notmoving.Banana;
import notmoving.*;
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
import gui.Assets;
import imageloader.ImageLoader;
import interfaces.Drawable;
import tarzan.Tarzan;
import tilegame.Game;
import tilegame.Level;
import tilegame.Position2D;

// FOR NOW: NO FLOWER, NO HUT, ONLY "TIGER"=JAGUAR

public class Map implements Drawable {
	public final static int SIZE_MAP = 8;	
	public final static int PIXEL_SCALE = 500/SIZE_MAP;
	private Level level;
	private float[][] landMap; // land type map // Size_map; set directly double[SIZE_MAP][SIZE_MAP]?
	// free = no tarzan, no animal, no notMovings, not water
	private boolean[][] freePositions; // set directly boolean[SIZE_MAP][SIZE_MAP]?
	private boolean[][] fieldOfViewMatrix; // true if in fov, false otherwise ; set directly double[SIZE_MAP][SIZE_MAP]

	private World mapWorld;

	private List<Object> positionnableList = new ArrayList <Object>();
	private List<NotMovings> mapNotMovings = new ArrayList<NotMovings>();
	private List<Position2D> tarzanFieldOfView = new ArrayList<Position2D>();
	public Tarzan mapTarzan;	

	public Map(int strength, int endurance, int level, Game game){ // Handler instead of Game?
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(2*level, 1, 1); //  2*lvl: increase number of water tiles with level
		landMap = mapGen.createMap(SIZE_MAP);
		this.level = new Level(level, SIZE_MAP);
		mapWorld = new World(landMap);
		// if handler: handler.setWorld(mapWorld);
		// handler instead of game in tarzan
		freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		fieldOfViewMatrix = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i=0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(freePositions[i], true); // or when rock put false
		}
		setWaterNotPositionFree();

		// create Tarzan here or in Game2?
		mapTarzan = new Tarzan(new Position2D(0,0), game);//, this.level, strength, endurance);
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false;


		createPositionables(this.level); 
		//setTarzanFieldOfView(); 
		//setFieldOfViewMatrix();
	}


	public void draw() {

	}

	public Color getColor(int x, int y) { // maybe MapImage is not necessary
		if (landMap[x][y] <= 0.5) { // if value equals 0, fill with water
			return Color.GREEN;
		} else if (landMap[x][y] > 0.5) { // if value equals 1, fill with forest
			return Color.BLUE; // put grass green and delete forest?
		} else {
			return Color.DARK_GRAY;
		}
	}


	private void drawMap(Graphics g) {
		mapWorld.render(g);
	}

	private void drawTarzan(Graphics g) {
		g.drawImage(Assets.TARZAN_NORMAL, mapTarzan.getTarzanPosition().getX()*PIXEL_SCALE, mapTarzan.getTarzanPosition().getY()*PIXEL_SCALE, null);
	}

	private void drawNotMovings(Graphics g) {
		for (int i=0; i<mapNotMovings.size(); i++) {
			g.drawImage(Assets.getImageFromString(mapNotMovings.get(i).getName()), mapNotMovings.get(i).getNotMovingsPosition().getX()*PIXEL_SCALE, mapNotMovings.get(i).getNotMovingsPosition().getY()*PIXEL_SCALE, null);
		}
	}



	private void createPositionables(Level lvl) {
		//int levelNumber = lvl.getLevelNumber();
		for (int i = 0; i < lvl.getNumberOfOpponents(); i++) {
			createOneJaguar();
		}

		// create NotMovings
		createJane();

		for (int i = 0; i < lvl.getNumberOfBananas(); i++) { // all the same for now
			createOneBanana();
			createOneKavurus();
			createOneKnife();
		}
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

	private boolean isPositionFree(Position2D pos) { 
		return freePositions[pos.getY()][pos.getX()];
	}

	private void setWaterNotPositionFree() { 
		for (int x=0; x<SIZE_MAP; x++) {
			for (int y=0; y<SIZE_MAP; y++) {
				if (landMap[x][y] > 0.5) { // > 0.5 = water
					freePositions[x][y] = false;
				}
			}
		}
	}

	private void createOneJaguar() {
		// create Tiger
		Jaguar j = new Jaguar(randomPosition());
		mapNotMovings.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
	}

	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotMovings.add(b);
		positionnableList.add(b);
		freePositions[b.getNotMovingsPosition().getY()][b.getNotMovingsPosition().getX()] = false;
	}

	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotMovings.add(j);
		positionnableList.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
	}

	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
	}

	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
	}

	public void tick() { 
		mapTarzan.tick();
		mapWorld.tick();
	}

	public void render(Graphics g) { 
		drawMap(g); // TODO: draw only visible tiles
		drawNotMovings(g);
		drawTarzan(g);

	}

}