package tilegame;

import java.awt.Color;

/**
 * @author Faustine & Martina
 * 
 * The GrassTile class extends the Tile class to compose water.  
 * 
 */

public class WaterTile extends Tile {
	/**
	 * Constructor. Create the tiles with blue color and a unique ID.
	 * @param id
	 */
	public WaterTile(int id) {
		super(Color.BLUE, id);
	}
}
