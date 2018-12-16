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
import tilegame.Handler;
import tilegame.Level;
import tilegame.Position2D;

// FOR NOW: NO FLOWER, NO HUT, ONLY "TIGER"=JAGUAR

public class Map implements Drawable {
	public final static int SIZE_MAP = 8;	
	public final static int PIXEL_SCALE = 500/SIZE_MAP;
	private Level level;
	private float[][] landMap;
	// free position = no tarzan, no animal, no notMovings, not water
	private boolean[][] freePositions; // set directly boolean[SIZE_MAP][SIZE_MAP]?
	private boolean[][] fieldOfViewMatrix; // true if in fov, false otherwise ; set directly double[SIZE_MAP][SIZE_MAP]

	private World mapWorld;

	private List<Object> positionnableList = new ArrayList <Object>();
	private List<NotMovings> mapNotMovings = new ArrayList<NotMovings>();
	private List<Position2D> tarzanFieldOfView = new ArrayList<Position2D>();
	public Tarzan mapTarzan;	

	public Map(int strength, int endurance, int level, Handler handler){ // Handler instead of Game?
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(level, 1, 1); //  lvl: increase number of water tiles with level
		landMap = mapGen.createMap(SIZE_MAP);
		//System.out.println("Level "+level);
		this.level = new Level(level, SIZE_MAP);
		mapWorld = new World(landMap);
		freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		fieldOfViewMatrix = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i=0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(freePositions[i], true); // or when rock put false
		}
		setWaterNotPositionFree();

		mapTarzan = new Tarzan(new Position2D(0,0), handler, this.level, strength, endurance);
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false;


		createPositionables(this.level); 
		//setTarzanFieldOfView(); 
		//setFieldOfViewMatrix();
	}


	public Tarzan getMapTarzan() {
		return mapTarzan;
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


	public World getMapWorld() {
		return mapWorld;
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
				if (Math.abs(Math.round(landMap[x][y])) >= 0.5) { // > 0.5 = water
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
		mapWorld.getTile(j.getNotMovingsPosition().getX(), j.getNotMovingsPosition().getY()).setHasNotMovings(j);
	}

	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotMovings.add(b);
		positionnableList.add(b);
		freePositions[b.getNotMovingsPosition().getY()][b.getNotMovingsPosition().getX()] = false;
		mapWorld.getTile(b.getNotMovingsPosition().getX(), b.getNotMovingsPosition().getY()).setHasNotMovings(b);
	}

	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotMovings.add(j);
		positionnableList.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
		mapWorld.getTile(j.getNotMovingsPosition().getX(), j.getNotMovingsPosition().getY()).setHasNotMovings(j);
		
	}

	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.getTile(k.getNotMovingsPosition().getX(), k.getNotMovingsPosition().getY()).setHasNotMovings(k);
	}

	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.getTile(k.getNotMovingsPosition().getX(), k.getNotMovingsPosition().getY()).setHasNotMovings(k);
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