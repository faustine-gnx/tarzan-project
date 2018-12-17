package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MapImage {

	private final int PIXEL_SCALE = 10;

	/**
	 * Creates a 2D PNG Image from a two dimensional array.
	 *
	 * @param array
	 */
	public void visualize(double[][] array) {
		createImage(array, "generatedMap");
	}

	/**
	 * Creates an amount of 2D PNG Images from a two dimensional array.
	 *
	 * @param array
	 */
	public void visualize(double[][] array, int amount) {
		for (int i = 0; i < amount; i++) {
			createImage(array, "generatedMap" + i);
		}
	}

	/**
	 * Creates an amount of 2D PNG Images from a two dimensional array.
	 *
	 * @param array
	 */
	public void visualize(double[][] array, String filename) {
		createImage(array, filename);
	}

	/**
	 * Private Method to create a Buffered Image and save the result in a file.
	 *
	 * @param array
	 * @param filename
	 */
	private void createImage(double[][] array, String filename) {

		//System.out.println("Creating MapImage, please wait...");

		int IMAGE_HEIGHT = array.length * PIXEL_SCALE;
		int IMAGE_WIDTH = array[0].length * PIXEL_SCALE;

		//System.out.println("Image Width: " + IMAGE_WIDTH + "px");
		//System.out.println("Image Height: " + IMAGE_HEIGHT + "px");

		// Constructs a BufferedImage of one of the predefined image types.
		BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		// Create a graphics which can be used to draw into the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();

		// fill all the image with white
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[x].length; y++) {

				//Defining coloring rules for each value
				//Use enums with switch case here!
				//System.out.println(array[x][y]);

				if (array[x][y] <= 0.01) { // if value equals 0, fill with water
					g2d.setColor(Color.BLUE);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);

				} else if (array[x][y] <= 0.45) { // if value equals 2, fill with grass
					g2d.setColor(Color.YELLOW);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);

				} else if (array[x][y] <= 0.9) { // if value equals 1, fill with forest
					g2d.setColor(Color.GREEN);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);

				} else if (array[x][y] > 0.9) { // if value equals 1, fill with rocks
					g2d.setColor(Color.DARK_GRAY);
					g2d.fillRect(y * PIXEL_SCALE, x * PIXEL_SCALE, PIXEL_SCALE, PIXEL_SCALE);
				}
			}
		}
		// Disposes of this graphics context and releases any system resources
		// that it is using.
		g2d.dispose();


		/*System.out.printf("Saving MapImage to Disk as %s.png ... \n", filename);
		// Save as PNG
		File file = new File(filename + ".png");
		try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done! \n");*/
	}
}


