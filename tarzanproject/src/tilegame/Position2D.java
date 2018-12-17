package tilegame;

/**
 * @author Faustine & Martina
 * 
 * The Position2D class is created to handle positions more easily: one attribute position instead of two x and y.
 * 
 */

 public class Position2D {
	private int x;
	private int y; 
	 
	/**
	 * Constructor from two int.
	 * @param x, y
	 */
    public Position2D(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
    
    /**
	 * Constructor from another Position2D.
	 * @param pos
	 */
    public Position2D(Position2D pos) { 
        this.x = pos.getX(); 
        this.y = pos.getY(); 
    } 
    
    /**
	 * Getter.
	 * @return x
	 */
    public int getX() { 
        return x; 
    } 
 
    /**
	 * Getter.
	 * @return y
	 */
    public int getY() { 
        return y; 
    } 
    
    /**
	 * Setter.
	 * @param x
	 */
    public Position2D setX(int x) { 
        this.x = x; 
        return this; 
    } 
 
    /**
	 * Setter.
	 * @param y
	 */
    public Position2D setY(int y) { 
        this.y = y; 
        return this; 
    } 
 
    /**
	 * Setter.
	 * @param x, y
	 */
    public Position2D set(int x, int y) { 
        this.x = x; 
        this.y = y; 
        return this; 
    }
    
    /**
	 * Check if two Position2D are equal.
	 * @param pos
	 * @return boolean
	 */
    public boolean isEqual(Position2D pos) {
    	if (this.x == pos.getX() && this.y == pos.getY()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}