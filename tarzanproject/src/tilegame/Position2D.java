package tilegame;
 public class Position2D {
	private int x, y; 
	 
    public Position2D(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
    
    public Position2D(Position2D p) { 
        this.x = p.getX(); 
        this.y = p.getY(); 
    } 
    
    public int getX() { 
        return x; 
    } 
 
    public int getY() { 
        return y; 
    } 
    
    public Position2D setX(int x) { 
        this.x = x; 
        return this; 
    } 
 
    public Position2D setY(int y) { 
        this.y = y; 
        return this; 
    } 
 
    public Position2D set(int x, int y) { 
        this.x = x; 
        this.y = y; 
        return this; 
    }
    
    public boolean isEqual(Position2D p) {
    	if (this.x == p.getX() && this.y == p.getY()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}