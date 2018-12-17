package map;

/**
 * @author Faustine & Martina
 * 
 * The MapGenerator interface is used to tell classes to create a map.
 * 
 */

public interface MapGenerator {
	public float[][] createMap(int size); // assume square map
}
