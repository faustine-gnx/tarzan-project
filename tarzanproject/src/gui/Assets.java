package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import imageloader.ImageLoader;

// TODO: CHANGE TO HAVE ONLY GIF IMAGES = NO BACKGROUND

public class Assets { // to load img / sounds ...
	private static final int WIDTH = 500/8; // 500/SIZE_MAP --> not static but final if we want to change size
	private static final int HEIGHT = 500/8;
			
	public static BufferedImage TARZAN_FIGHT;
	public static BufferedImage TARZAN_FOREST;
	public static BufferedImage TARZAN_JANE;
	public static BufferedImage TARZAN_NORMAL;
	public static BufferedImage TARZAN_WATER;
	public static BufferedImage ELEPHANT;
	public static BufferedImage TIGER;
	public static BufferedImage LION;
	public static BufferedImage CROCODILE;
	public static BufferedImage SNAKE;
	public static BufferedImage HUT;
	public static BufferedImage KAVURUS;
	public static BufferedImage KNIFE;
	public static BufferedImage BANANA;
	public static BufferedImage FLOWER;
	public static BufferedImage JANE;
	
	public static void init() {
		BufferedImage TARZAN_NORMAL0 = ImageLoader.loadImage("/textures/tarzan_normal.gif");
		TARZAN_NORMAL = resize(TARZAN_NORMAL0);
		
		BufferedImage TARZAN_FOREST0 = ImageLoader.loadImage("/textures/tarzan_forest.gif");
		TARZAN_FOREST = resize(TARZAN_FOREST0);
		
		BufferedImage ELEPHANT0 = ImageLoader.loadImage("/textures/elephant.gif");
		ELEPHANT = resize(ELEPHANT0);
		
		BufferedImage TIGER0 = ImageLoader.loadImage("/textures/tiger.gif");
		TIGER = resize(TIGER0);
		
		BufferedImage BANANA0 = ImageLoader.loadImage("/textures/banana.gif");
		BANANA = resize(BANANA0);
		
		BufferedImage KAVURUS0 = ImageLoader.loadImage("/textures/pill.gif");
		KAVURUS = resize(KAVURUS0);
		
		BufferedImage KNIFE0 = ImageLoader.loadImage("/textures/knife.gif");
		KNIFE = resize(KNIFE0);
	
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
			case "TIGER": 
				return Assets.TIGER;
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
