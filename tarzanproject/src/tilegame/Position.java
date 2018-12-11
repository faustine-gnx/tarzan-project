package tilegame;
 public class Position {
	private int x, y; 
	 
    public Position(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
    
    public Position(Position p) { 
        this.x = p.getX(); 
        this.y = p.getY(); 
    } 
    
    public int getX() { 
        return x; 
    } 
 
    public int getY() { 
        return y; 
    } 
    
    public Position setX(int x) { 
        this.x = x; 
        return this; 
    } 
 
    public Position setY(int y) { 
        this.y = y; 
        return this; 
    } 
 
    public Position set(double x, double y) { 
        this.x = x; 
        this.y = y; 
        return this; 
    }
    
    public boolean isEqual(Position p) {
    	if (this.x == p.getX() && this.y == p.getY()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}