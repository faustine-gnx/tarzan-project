package map;

/**
 * The MapGenerator interface is used to tell classes to create a map.
 * 
 */

public interface MapGenerator {
	/**
	 * To create a map.
	 * 
	 * @param size
	 * @return float[][]
	 */
	public float[][] createMap(int size); // assume square map
}
