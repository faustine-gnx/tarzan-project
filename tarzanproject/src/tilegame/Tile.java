package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import map.Map;

/**
 * @author Faustine & Martina
 * 
 *         The Tile class is an abstract class to compose the world (either
 *         grass or water).
 * 
 */

public abstract class Tile {

	public static Tile[] tiles = new Tile[5]; /**< set of tiles */
	public static Tile grassTile = new GrassTile(0); /**< create grass tile with index 0 */
	public static Tile waterTile = new WaterTile(1); /**< create water tile with index 1 */

	protected Color tileColor;
	protected final int tileID;

	/**
	 * Constructor. Create the tiles with a color (depending on the type, grass or
	 * water) and a unique ID.
	 * @param color, id
	 */
	public Tile(Color color, int id) {
		tileColor = color; // in our case color?
		tileID = id;
		tiles[id] = this;
	}

	/**
	 * Getter.
	 * @return tileID
	 */
	public int getId() {
		return tileID;
	}

	/**
	 * Draw the tile according to its color.
	 * @param g, x, y
	 */
	public void render(Graphics g, int x, int y) {
		g.setColor(tileColor);
		g.fillRect(y * Map.PIXEL_SCALE, x * Map.PIXEL_SCALE, Map.PIXEL_SCALE, Map.PIXEL_SCALE);
	}

	/**
	 * Draw a grey tile (when not in Tarzan's field of view).
	 * @param g, x, y
	 */
	public void renderGrayTile(Graphics g, int x, int y) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(y * Map.PIXEL_SCALE, x * Map.PIXEL_SCALE, Map.PIXEL_SCALE, Map.PIXEL_SCALE);
	}
}
