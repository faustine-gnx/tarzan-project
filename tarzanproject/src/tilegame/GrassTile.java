package tilegame;

import java.awt.Color;

public class GrassTile extends Tile {
	
	public GrassTile(int id) {
		super(Color.GREEN, id);
	}
	
	@Override
	public boolean areNotLivingsDrawn() {
		return true;
	}
}
