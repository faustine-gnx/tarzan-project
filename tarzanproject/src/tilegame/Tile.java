package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import map.Map;
import notmoving.NotMovings;

public abstract class Tile {
	
	public static Tile[] tiles = new Tile[Map.SIZE_MAP*Map.SIZE_MAP];
	public static Tile grassTile = new GrassTile(0);
	public static Tile waterTile = new WaterTile(1);
	//public static Tile secretTile = new SecretTile(2); for invisible tiles but actually not the best way imo
	
	protected boolean isVisible;
	
	protected Color tileColor;
	protected final int tileID;
	protected NotMovings hasNotMovings;
	
	public Tile(Color color, int id) {
		tileColor = color; // in our case color?
		tileID = id;
		tiles[id] = this;
		hasNotMovings = null;
	}

	public NotMovings getHasNotMovings() {
		return hasNotMovings;
	}

	public void setHasNotMovings(NotMovings notMovings) {
		hasNotMovings = notMovings;
	}

	public int getId() {
		return tileID;
	}
	
	public boolean areNotLivingsDrawn() {
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.setColor(tileColor);
		g.fillRect(y*Map.PIXEL_SCALE, x*Map.PIXEL_SCALE, Map.PIXEL_SCALE, Map.PIXEL_SCALE);
	}
}
