package tilegame;

import java.awt.Color;

/**
 * @author Faustine & Martina
 * 
 *         The GrassTile class extends the Tile class to compose grass.
 * 
 */

public class GrassTile extends Tile {
	/**
	 * Constructor. Create the tiles with green color and a unique ID.
	 * 
	 * @param id
	 */
	public GrassTile(int id) {
		super(Color.GREEN, id);
	}
}
