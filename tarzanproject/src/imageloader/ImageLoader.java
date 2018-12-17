package imageloader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Faustine & Martina
 * 
 *         The ImageLoader class is used to load images from folders.
 * 
 */

public class ImageLoader {

	/**
	 * Loads an image from a given path.
	 * 
	 * @param path
	 * @return BufferedImage
	 */
	public static BufferedImage loadImage(String path) {
		try {
			// return image loaded
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e1) {
			e1.printStackTrace();
			// fails = exit
			System.exit(1);
		}
		return null;
	}
}
