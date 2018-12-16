package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import imageloader.ImageLoader;
import map.Map;

// TODO: CHANGE TO HAVE ONLY GIF IMAGES = NO BACKGROUND

public class Assets { // to load img / sounds ...
	private static final int WIDTH = 500/Map.SIZE_MAP;
	private static final int HEIGHT = 500/Map.SIZE_MAP;
			
	public static BufferedImage TARZAN_FIGHT;
	public static BufferedImage TARZAN_FOREST;
	public static BufferedImage TARZAN_JANE;
	public static BufferedImage TARZAN_NORMAL;
	public static BufferedImage TARZAN_WATER;
	public static BufferedImage KAVURUS;
	public static BufferedImage KNIFE;
	public static BufferedImage BANANA;
	public static BufferedImage JAGUAR;
	public static BufferedImage JANE;
	
	public static void init() {
		BufferedImage TARZAN_NORMAL0 = ImageLoader.loadImage("/textures/tarzan_normal.gif");
		TARZAN_NORMAL = resize(TARZAN_NORMAL0);
		
		BufferedImage TARZAN_FOREST0 = ImageLoader.loadImage("/textures/tarzan_forest.gif");
		TARZAN_FOREST = resize(TARZAN_FOREST0);
		
		BufferedImage JAGUAR0 = ImageLoader.loadImage("/textures/jaguar.gif");
		JAGUAR = resize(JAGUAR0);
		
		BufferedImage BANANA0 = ImageLoader.loadImage("/textures/banana.gif");
		BANANA = resize(BANANA0);
		
		BufferedImage KAVURUS0 = ImageLoader.loadImage("/textures/pill.gif");
		KAVURUS = resize(KAVURUS0);
		
		BufferedImage KNIFE0 = ImageLoader.loadImage("/textures/knife.gif");
		KNIFE = resize(KNIFE0);
		
		BufferedImage JANE0 = ImageLoader.loadImage("/textures/jane.gif");
		JANE = resize(JANE0);
	
	}
	
	public static BufferedImage resize(BufferedImage img) { 
	    Image tmp = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(WIDTH, WIDTH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;
	}  
	
	public static BufferedImage getImageFromString(String s) {
		switch(s) {
			case "JAGUAR": 
				return Assets.JAGUAR;
			case "KAVURUS": 
				return Assets.KAVURUS;
			case "BANANA": 
				return Assets.BANANA;
			case "KNIFE": 
				return Assets.KNIFE;
			case "JANE": 
				return Assets.JANE;
			default:
                throw new RuntimeException("Unknown image");
		}
	}
}
