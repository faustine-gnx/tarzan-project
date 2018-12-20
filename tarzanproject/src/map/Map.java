package map;

import java.util.Random;
import notmoving.*;
import java.awt.Color;
import java.awt.Graphics;
import gui.Assets;
import gui.GameApplication;
import tarzan.Tarzan;
import tilegame.*;

/**
 * @author Faustine & Martina
 * 
 *         The Map class is used to run the game. It has a World (the terrain),
 *         a list of "NonMovings" (all the entities of the game except Tarzan),
 *         and Tarzan. The Map depends on the level chosen. The Map is used to
 *         generate the random attribution of tiles and create NonMovings and
 *         Tarzan. It is used to update the graphics of the game (render
 *         method), calling the render methods from World and Tarzan.
 * 
 */

public class Map {
	public final static int SIZE_MAP = 16; // size of the game
	public final static int PIXEL_SCALE = GameApplication.WIDTH / SIZE_MAP; // pixel scale for drawing (same canvas size)
	private final Level mapLevel; // level for the game: influences parameters
	private float[][] landMap; // land height in map (used with perlin noise for smoother map)
	private boolean[][] freePositions; // free position = no tarzan, no nonMoving, not water
	private final World mapWorld; // terrain of the map = tile repartition, based on landMap
	private Tarzan mapTarzan; // Tarzan = player

	/**
	 * Constructor. Initialize the Map: - Generates a random repartition for the
	 * tiles - Sets the level and initial parameters - Creates Tarzan - Creates the
	 * NonMovings
	 * @param strength, endurance, level, handler
	 */
	public Map(int strength, int endurance, int level, Handler handler) {
		System.out.println("Initializing the parameters of a new game: ");
		// Simplex noise (not that useful now that we just have 2 sorts of tiles)
		// More difficult terrain with higher level
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(level, 1, 1); // increase number of water tiles with level
		System.out.println("Generating a map");								
		landMap = mapGen.createMap(SIZE_MAP); 
		mapLevel = new Level(level);
		mapWorld = new World(landMap);
		freePositions = new boolean[SIZE_MAP][SIZE_MAP];
		for (int i = 0; i < SIZE_MAP; i++) {
			java.util.Arrays.fill(freePositions[i], true); // 1) set all positions free = true
		}
		setWaterNotPositionFree(); // 2) set positions with water to false
		mapTarzan = new Tarzan(new Position2D(0, 0), handler, mapLevel, strength, endurance);
		// 3) set Tarzan position to false
		freePositions[mapTarzan.getTarzanPosition().getY()][mapTarzan.getTarzanPosition().getX()] = false; 
		System.out.println("NonMovings creation");
		// 4) create nonMovings in free positions (and set positions to false)
		createNonMovings(mapLevel);
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
	public Color getColor(int x, int y) { 
		if (landMap[x][y] <= 0.5) { // if value equals 0, fill with grass
			return Color.GREEN;
		} else if (landMap[x][y] > 0.5) { // if value equals 1, fill with water
			return Color.BLUE;
		} else {
			return Color.DARK_GRAY;
		}
	}

	/**
	 * Draw the world: tiles + non movings
	 * @param g
	 */
	private void drawWorld(Graphics g) {
		//System.out.println("Drawing the world...");
		mapWorld.renderGrayTiles(g); // 1) all grey tiles
		mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(), mapTarzan.getTarzanPosition().getY()); // tarzan tile
		for (int x = 1; x <= Tarzan.FIELD_OF_VIEW_RADIUS; x++) { // show tiles in field of view
			for (int y = 1; y <= Tarzan.FIELD_OF_VIEW_RADIUS; y++) {
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() + x,
						mapTarzan.getTarzanPosition().getY());
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() - x,
						mapTarzan.getTarzanPosition().getY());
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(),
						mapTarzan.getTarzanPosition().getY() + y);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX(),
						mapTarzan.getTarzanPosition().getY() - y);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() + x - 1,
						mapTarzan.getTarzanPosition().getY() + y - 1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() - x + 1,
						mapTarzan.getTarzanPosition().getY() - y + 1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() + x - 1,
						mapTarzan.getTarzanPosition().getY() - y + 1);
				mapWorld.renderOneTile(g, mapTarzan.getTarzanPosition().getX() - x + 1,
						mapTarzan.getTarzanPosition().getY() + y - 1);
			}
		}
	}

	/**
	 * Draw Tarzan.
	 * @param g
	 */
	private void drawTarzan(Graphics g) {
		g.drawImage(Assets.TARZAN_NORMAL, mapTarzan.getTarzanPosition().getX() * PIXEL_SCALE,
				mapTarzan.getTarzanPosition().getY() * PIXEL_SCALE, null);
	}

	/*
	 * private void drawNonMovings(Graphics g) { for (int x=0; x<SIZE_MAP; x++) {
	 * for (int y=0; y<SIZE_MAP; y++) { if(mapWorld.getWorldNonMovings(new
	 * Position2D(x,y)) != null) {
	 * g.drawImage(Assets.getImageFromString(mapWorld.getWorldNonMovings(new
	 * Position2D(x,y)).getName()), x*PIXEL_SCALE, y*PIXEL_SCALE, null);
	 * 
	 * } } } }
	 */ // Now done in draw world

	/**
	 * Create the NonMovings; number depending on level.
	 * @param level
	 */
	private void createNonMovings(Level level) {
		for (int i = 0; i < level.getNumberOfJaguars(); i++) {
			createOneJaguar();
		}

		createJane();

		for (int i = 0; i < level.getNumberOfBananas(); i++) { // all 3 same number
			createOneBanana();
			createOneKavurus();
			createOneKnife();
		}
	}

	/**
	 * Generate random position for the creation of the NonMovings. Checks if
	 * position is free, if not generate a new random position recursively.
	 * @return Position2D
	 */
	private Position2D randomPosition() {
		Random rand = new Random();
		int x = rand.nextInt(SIZE_MAP); // new random position
		int y = rand.nextInt(SIZE_MAP);
		Position2D pos = new Position2D(x, y);
		if (isPositionFree(pos)) { // check if position is free
			freePositions[pos.getY()][pos.getX()] = false; // set it to false
			return pos; // return it
		} else { // if not free
			return randomPosition(); // Recursive way to find other position if previous position is not free
		}
	}

	/**
	 * Check if position is free: not a water Tile and not an other NonMoving on it.
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
		for (int x = 0; x < SIZE_MAP; x++) {
			for (int y = 0; y < SIZE_MAP; y++) {
				if (Math.abs(Math.round(landMap[x][y])) >= 0.5) { // > 0.5 = water
					freePositions[x][y] = false;
				}
			}
		}
	}

	/**
	 * Create a new Jaguar, add it to the map and world, set its position to false
	 * in the freePosition matrix.
	 */
	private void createOneJaguar() {
		Jaguar j = new Jaguar(randomPosition());
		freePositions[j.getNonMovingPosition().getY()][j.getNonMovingPosition().getX()] = false;
		mapWorld.setWorldNonMovings(j);
		System.out.println("Jaguar created");
	}

	/**
	 * Create a new Banana, add it to the map and world, set its position to false
	 * in the freePosition matrix.
	 */
	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		freePositions[b.getNonMovingPosition().getY()][b.getNonMovingPosition().getX()] = false;
		mapWorld.setWorldNonMovings(b);
		System.out.println("Banana created");
	}

	/**
	 * Create Jane, add it to the map and world, set its position to false in the
	 * freePosition matrix.
	 */
	private void createJane() {
		Jane j = new Jane(randomPosition());
		freePositions[j.getNonMovingPosition().getY()][j.getNonMovingPosition().getX()] = false;
		mapWorld.setWorldNonMovings(j);
		System.out.println("Jane created!");
	}

	/**
	 * Create a new Kavuru's pill, add it to the map and world, set its position to
	 * false in the freePosition matrix.
	 */
	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		freePositions[k.getNonMovingPosition().getY()][k.getNonMovingPosition().getX()] = false;
		mapWorld.setWorldNonMovings(k);
		System.out.println("Kavuru's pill created");
	}

	/**
	 * Create a new Knife, add it to the map and world, set its position to false in
	 * the freePosition matrix.
	 */
	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		freePositions[k.getNonMovingPosition().getY()][k.getNonMovingPosition().getX()] = false;
		mapWorld.setWorldNonMovings(k);
		System.out.println("Knife created");
	}

	/**
	 * Draw the world (thus also NonMovings) and Tarzan.
	 * @param g
	 */
	public void render(Graphics g) {
		drawWorld(g);
		drawTarzan(g);
	}
}