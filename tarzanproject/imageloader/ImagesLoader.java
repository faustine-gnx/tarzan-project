package imageloader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesLoader {

	public static BufferedImage loadImage (String path) {
		try {
			// return image loaded
			return ImageIO.read(ImagesLoader.class.getResource(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// fails = exit
			System.exit(1);
		}
		return null;
} }
