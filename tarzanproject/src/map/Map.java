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

/**
 * @author Faustine & Martina
 * 
 * The Map class is used to run the game. 
 * It has a World (the terrain), a list of "NotMovings" (all the entities of the game except Tarzan), and Tarzan.
 * The Map depends on the level chosen.
 * The Map is used to generate the random attribution of tiles and create NotMovings and Tarzan.
 * It is used to update the graphics of the game (render method), calling the render methods from World and Tarzan.
 * 
 */

public class Map {
	public final static int SIZE_MAP = 16;	
	public final static int PIXEL_SCALE = GameApplication.WIDTH/SIZE_MAP;
	private final Level mapLevel;
	private float[][] landMap;
	// free position = no tarzan, no animal, no notMovings, not water
	private boolean[][] freePositions; // set directly boolean[SIZE_MAP][SIZE_MAP]?
	private final World mapWorld;
	private List<NotMovings> mapNotMovings = new ArrayList<NotMovings>();
	public Tarzan mapTarzan;	

	/**
	 * Constructor. Initialize the Map:
	 * 		- Generates a random repartition for the tiles
	 * 		- Sets the level and initial parameters
	 * 		- Creates Tarzan
	 * 		- Creates the NotMovings
	 * @param strength, endurance, level, handler
	 */
	public Map(int strength, int endurance, int level, Handler handler){ // Handler instead of Game?
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(level, 1, 1); //  lvl: increase number of water tiles with level
		landMap = mapGen.createMap(SIZE_MAP);
		mapLevel = new Level(level);
		mapWorld = new World(landMap);
		freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i=0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(freePositions[i], true); // or when rock put false
		}
		setWaterNotPositionFree();
		mapTarzan = new Tarzan(new Position2D(0,0), handler, mapLevel, strength, endurance);
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false;
		createNotMovings(mapLevel); 
	}

	/**
	 * Getter.
	 * @return mapTarzan
	 */
	public Tarzan getMapTarzan() {
		return mapTarzan;
	}
	
	/**
	 * Getter.
	 * @return mapLevel
	 */
	public Level getMapLevel() {
		return mapLevel;
	}
	
	/**
	 * Getter.
	 * @return mapWorld
	 */
	public World getMapWorld() {
		return mapWorld;
	}
	
	/**
	 * Getter.
	 * @return Color
	 */
	public Color getColor(int x, int y) { // maybe MapImage is not necessary
		if (landMap[x][y] <= 0.5) { // if value equals 0, fill with water
			return Color.GREEN;
		} else if (landMap[x][y] > 0.5) { // if value equals 1, fill with forest
			return Color.BLUE; // put grass green and delete forest?
		} else {
			return Color.DARK_GRAY;
		}
	}
	
	/**
	 * Draw the world: tiles + not movings
	 * @param g
	 */
	private void drawWorld(Graphics g) {
		mapWorld.renderGrayTiles(g);
		mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(), mapTarzan.getTarzanPosition().getY());
		for (int x=1; x<= Tarzan.FIELD_OF_VIEW_RADIUS; x++) {
			for (int y=1; y<= Tarzan.FIELD_OF_VIEW_RADIUS; y++) {
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()+x, mapTarzan.getTarzanPosition().getY());
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()-x, mapTarzan.getTarzanPosition().getY());
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(), mapTarzan.getTarzanPosition().getY()+y);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(), mapTarzan.getTarzanPosition().getY()-y);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()+x-1, mapTarzan.getTarzanPosition().getY()+y-1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()-x+1, mapTarzan.getTarzanPosition().getY()-y+1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()+x-1, mapTarzan.getTarzanPosition().getY()-y+1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX()-x+1, mapTarzan.getTarzanPosition().getY()+y-1);
			}
		}
	}
	
	/**
	 * Draw Tarzan.
	 * @param g
	 */
	private void drawTarzan(Graphics g) {
		g.drawImage(Assets.TARZAN_NORMAL, mapTarzan.getTarzanPosition().getX()*PIXEL_SCALE, mapTarzan.getTarzanPosition().getY()*PIXEL_SCALE, null);
	}

	/*private void drawNotMovings(Graphics g) {
		for (int x=0; x<SIZE_MAP; x++) {
			for (int y=0; y<SIZE_MAP; y++) {
					if(mapWorld.getWorldNotMovings(new Position2D(x,y)) != null) {
						g.drawImage(Assets.getImageFromString(mapWorld.getWorldNotMovings(new Position2D(x,y)).getName()), x*PIXEL_SCALE, y*PIXEL_SCALE, null);

					}
			}
		}
	}*/ // Now done in draw world
	
	/**
	 * Create the NotMovings; number depending on level.
	 * @param level
	 */
	private void createNotMovings(Level level) {
		for (int i = 0; i < level.getNumberOfJaguars(); i++) {
			createOneJaguar();
		}

		// create NotMovings
		createJane();

		for (int i = 0; i < level.getNumberOfBananas(); i++) { // all the same for now
			createOneBanana();
			createOneKavurus();
			createOneKnife();
		}
	}
	
	/**
	 * Generate random position for the creation of the NotMovings.
	 * Checks if position is free, if not generate a new random position recursively.
	 * @return Position2D
	 */
	private Position2D randomPosition() { 
		Random rand = new Random();
		int x = rand.nextInt(SIZE_MAP); 
		int y = rand.nextInt(SIZE_MAP);
		Position2D pos = new Position2D(x,y);
		if (isPositionFree(pos)) {
			freePositions[pos.getY()][pos.getX()] = false;
			return pos; 
		} else {
			return randomPosition(); // Recursive way to find other position if previous position is not free
		}
	}
	
	/**
	 * Check if position is free: not a water Tile and not an other NotMoving on it.
	 * @param pos
	 * @return Position2D
	 */
	private boolean isPositionFree(Position2D pos) { 
		return freePositions[pos.getY()][pos.getX()];
	}

	/**
	 * Initialize the freePosition matrix to false for all water tiles.
	 */
	private void setWaterNotPositionFree() { 
		for (int x=0; x<SIZE_MAP; x++) {
			for (int y=0; y<SIZE_MAP; y++) {
				if (Math.abs(Math.round(landMap[x][y])) >= 0.5) { // > 0.5 = water
					freePositions[x][y] = false;
				}
			}
		}
	}
	
	/**
	 * Create a new Jaguar, add it to the map and world, set its position to false in the freePosition matrix.
	 */
	private void createOneJaguar() {
		// create Jaguar
		Jaguar j = new Jaguar(randomPosition());
		mapNotMovings.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(j);
	}
	
	/**
	 * Create a new Banana, add it to the map and world, set its position to false in the freePosition matrix.
	 */
	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		mapNotMovings.add(b);
		freePositions[b.getNotMovingsPosition().getY()][b.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(b);
	}
	
	/**
	 * Create Jane, add it to the map and world, set its position to false in the freePosition matrix.
	 */
	private void createJane() {
		Jane j = new Jane(randomPosition());
		mapNotMovings.add(j);
		freePositions[j.getNotMovingsPosition().getY()][j.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(j);
	}
	
	/**
	 * Create a new Kavuru's pill, add it to the map and world, set its position to false in the freePosition matrix.
	 */
	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		mapNotMovings.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(k);
	}

	/**
	 * Create a new Knife, add it to the map and world, set its position to false in the freePosition matrix.
	 */
	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		mapNotMovings.add(k);
		freePositions[k.getNotMovingsPosition().getY()][k.getNotMovingsPosition().getX()] = false;
		mapWorld.setWorldNotMovings(k);
	}
	
	/**
	 * Draw the world (thus also NotMovings) and Tarzan.
	 * @param g
	 */
	public void render(Graphics g) { 
		drawWorld(g);
		drawTarzan(g);
	}
}