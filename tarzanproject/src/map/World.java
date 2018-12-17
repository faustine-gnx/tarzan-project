package map;

import java.awt.Graphics;

import gui.Assets;
import notmoving.NotMovings;
import tilegame.Position2D;
import tilegame.Tile;

public class World {

	private int[][] worldTiles;
	private NotMovings[][] worldNotMovings = new NotMovings[Map.SIZE_MAP][Map.SIZE_MAP];
	
	public World(float[][] landMap) { // pass Map2 as argument and create tiles based on landMap
		worldTiles = new int[Map.SIZE_MAP][Map.SIZE_MAP];
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				//System.out.println(Math.abs(Math.round(landMap[x][y])));
				worldTiles[x][y] = Math.abs(Math.round(landMap[x][y]));
				//worldNotMovings[x][y] = null;
			}
		}		
	}
	
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[worldTiles[x][y]];
		if(t == null) {
			return Tile.grassTile;
		}
		return t;
	}
	
	public Tile getTile(Position2D pos) {
		Tile t = Tile.tiles[worldTiles[pos.getX()][pos.getY()]];
		if(t == null) {
			return Tile.grassTile;
		}
		return t;
	}
	

	public int[][] getWorldTiles() {
		return worldTiles;
	}
	
	public NotMovings getWorldNotMovings(Position2D pos) {
		return worldNotMovings[pos.getX()][pos.getY()];
	}

	public void setWorldNotMovings(NotMovings notMovings) {
		
		worldNotMovings[notMovings.getNotMovingsPosition().getX()][notMovings.getNotMovingsPosition().getY()] = notMovings;
		System.out.println(notMovings.getNotMovingsPosition().getX());
	}
	
	public void setWorldNotMovingsNull(Position2D pos) {
		worldNotMovings[pos.getX()][pos.getY()] = null;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				//System.out.println(x + ", " + y);
				getTile(x, y).render(g, x, y);
			}
		}
	}
	
	public void renderGrayTiles(Graphics g) {
		for (int x = 0; x < Map.SIZE_MAP; x++) {
			for (int y = 0; y < Map.SIZE_MAP; y++) {
				getTile(x, y).renderGrayTile(g, y, x);
			}
		}
	}

	public void renderOneTile(Graphics g, int x, int y) {
		if (0 <= x && x < Map.SIZE_MAP && 0 <= y && y < Map.SIZE_MAP) {
			getTile(x, y).render(g, y, x);
			if (worldNotMovings[x][y] != null) {
				g.drawImage(Assets.getImageFromString(worldNotMovings[x][y].getName()), x*Map.PIXEL_SCALE, y*Map.PIXEL_SCALE, null);
			}
		}
	}
	
	public void renderOneGrayTile(Graphics g, int x, int y) {
		getTile(x, y).renderGrayTile(g, y, x);
	}
}
