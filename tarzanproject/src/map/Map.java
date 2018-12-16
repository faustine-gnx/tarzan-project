package map;
import java.util.Random;
import notmoving.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import gui.Assets;
import gui.GameApplication;
import tarzan.Tarzan;
import tilegame.*;

// FOR NOW: NO FLOWER, NO HUT, ONLY "TIGER"=JAGUAR

public class Map {
	public final static int SIZE_MAP = 10;	
	public final static int PIXEL_SCALE = GameApplication.WIDTH/SIZE_MAP;
	private final Level mapLevel;
	private float[][] landMap;
	// free position = no tarzan, no animal, no notMovings, not water
	private boolean[][] freePositions; // set directly boolean[SIZE_MAP][SIZE_MAP]?
	private boolean[][] fieldOfViewMatrix; // true if in fov, false otherwise ; set directly double[SIZE_MAP][SIZE_MAP]

	private final World mapWorld;
	private List<Object> positionnableList = new ArrayList <Object>();
	private List<NotMovings> mapNotMovings = new ArrayList<NotMovings>();
	private List<Position2D> tarzanFieldOfView = new ArrayList<Position2D>();
	public Tarzan mapTarzan;	

	public Map(int strength, int endurance, int level, Handler handler){ // Handler instead of Game?
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(level, 1, 1); //  lvl: increase number of water tiles with level
		landMap = mapGen.createMap(SIZE_MAP);
		//System.out.println("Level "+level);
		mapLevel = new Level(level);
		mapWorld = new World(landMap);
		freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		fieldOfViewMatrix = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i=0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(freePositions[i], true); // or when rock put false
		}
		setWaterNotPositionFree();

		mapTarzan = new Tarzan(new Position2D(0,0), handler, mapLevel, strength, endurance);
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false;


		createPositionables(mapLevel); 
		//setTarzanFieldOfView(); 
		//setFieldOfViewMatrix();
	}


	public Tarzan getMapTarzan() {
		return mapTarzan;
	}

	public Level getMapLevel() {
		return mapLevel;
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
		for (int x=0; x<SIZE_MAP; x++) {
			for (int y=0; y<SIZE_MAP; y++) {
					if(mapWorld.getWorldNotMovings(new Position2D(x,y)) != null) {
						g.drawImage(Assets.getImageFromString(mapWorld.getWorldNotMovings(new Position2D(x,y)).getName()), x*PIXEL_SCALE, y*PIXEL_SCALE, null);
						
					}
			}
		}
	}

	private void createPositionables(Level lvl) {
		for (int i = 0; i < lvl.getNumberOfJaguars(); i++) {
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
		if (isPositionFree(pos)) {
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
		mapWorld.setWorldNotMovings(j);
	}

	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotMovings.add(b);
		positionnableList.add(b);
		freePositions[b.getNotMovingsPosition().getY()][b.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(b);
	}

	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotMovings.add(j);
		positionnableList.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(j);
		
	}

	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(k);
	}

	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotMovings.add(k);
		positionnableList.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(k);
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