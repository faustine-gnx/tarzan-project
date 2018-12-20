package map;

import java.awt.Graphics;
import gui.Assets;
import notmoving.NonMoving;
import tilegame.Position2D;
import tilegame.Tile;

/**
 * @author Faustine & Martina
 * 
 *         The World class is the terrain of the world. It is composed of Tiles
 *         (grass or water) which might have NonMovings on it. The render
 *         methods draw the tiles and their NonMoving if any.
 * 
 */

public class World {

	private int[][] worldTiles; /**< tiles of the terrain; int = type */
	private NonMoving[][] worldNonMovings = new NonMoving[Map.SIZE_MAP][Map.SIZE_MAP]; /**<nonMovings of the terrain */

	/**
	 * Constructor. Create the tiles according to the noise map landMap.
	 * @param landMap
	 */
	public World(float[][] landMap) {
		worldTiles = new int[Map.SIZE_MAP][Map.SIZE_MAP];
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				worldTiles[y][x] = Math.abs(Math.round(landMap[x][y]));
			}
		}
	}

	/**
	 * Get the tile corresponding to the map position.
	 * @param x, y
	 * @return Tile
	 */
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[worldTiles[x][y]];
		if (t == null) {
			return Tile.grassTile;
		}
		return t;
	}

	/**
	 * Get the tile corresponding to the map position.
	 * @param pos
	 * @return Tile
	 */
	public Tile getTile(Position2D pos) {
		Tile t = Tile.tiles[worldTiles[pos.getX()][pos.getY()]];
		if (t == null) {
			return Tile.grassTile;
		}
		return t;
	}

	/**
	 * Getter.
	 * @return worldTiles
	 */
	public int[][] getWorldTiles() {
		return worldTiles;
	}

	/**
	 * Get the NonMoving corresponding to the map position.
	 * @param pos
	 * @return NonMovings
	 */
	public NonMoving getWorldNonMovings(Position2D pos) {
		return worldNonMovings[pos.getX()][pos.getY()];
	}

	/**
	 * Set the NonMoving in the worldNonMovings matrix.
	 * @param nonMovings
	 */
	public void setWorldNonMovings(NonMoving nonMoving) {
		worldNonMovings[nonMoving.getNonMovingPosition().getX()][nonMoving.getNonMovingPosition().getY()] = nonMoving;
	}

	/**
	 * Set the worldNonMovings matrix to null at position pos.
	 * @param pos
	 */
	public void setWorldNonMovingNull(Position2D pos) {
		worldNonMovings[pos.getX()][pos.getY()] = null;
	}

	/**
	 * Draw all tiles.
	 * @deprecated Now only visible tiles are drawn.
	 * @param g
	 */
	public void render(Graphics g) {
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				getTile(x, y).render(g, x, y);
			}
		}
	}

	/**
	 * Fill the world with gray tiles.
	 * @param g
	 */
	public void renderGrayTiles(Graphics g) {
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				getTile(x, y).renderGrayTile(g, y, x);
			}
		}
	}

	/**
	 * Draw the tile corresponding the the position x, y.
	 * @param g, x, y
	 */
	public void renderOneTile(Graphics g, int x, int y) {
		if (0 <= x && x < Map.SIZE_MAP && 0 <= y && y < Map.SIZE_MAP) {
			getTile(x, y).render(g, y, x);
			if (worldNonMovings[x][y] != null) {
				g.drawImage(Assets.getImageFromString(worldNonMovings[x][y].getName()), x * Map.PIXEL_SCALE,
						y * Map.PIXEL_SCALE, null);
			}
		}
	}
}
