package map;
import drawable.Drawable;
import tarzan.Tarzan;
import tilegame.Level;
import tilegame.Settings;

public class Map implements Drawable {
	
	private final static int SIZE_MAP = 50;
	double[][] landMap;
	//Level lvl; // need for attribute ? or attribute of game? and getters? IN GAME
	//Settings setg; // need for attribute ? or attribute of game? and getters? IN GAME
	private Level lvl;
	private Settings setg;
	
	//public final int sizeLandMap; //depends on level
	//private final static int[][] NOISE_MAP;
	//private final int[][] landMap; //initialized for each new game
	
	//ArrayList <Animal> animals
	//Animal[] Animals;
	//NonLiving[] NonLivings;
	//List<Animal> animals = new ArrayList<Animal>();
	//List<NonLiving> nonLivings = new ArrayList<NonLiving>();
	//Tarzan tarzan;
	
	public Map(){ // need to be public because not in same package as Game, which is calling it
		int[][] randomTarzanPosition = new int[1][1]; // not random yet
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		double[][] landMap = mapGen.createMap(SIZE_MAP);
		//mapIm.visualize(array, "generatedMap");
		Tarzan tarzan = new Tarzan(randomTarzanPosition, this.lvl, this.setg);
		createPositionables(this.lvl); 
	}
	
	public Map(int[][] tarzanPosition){
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		double[][] landMap = mapGen.createMap(SIZE_MAP);
		//mapIm.visualize(array, "generatedMap");
		Tarzan tarzan = new Tarzan(tarzanPosition, this.lvl, this.setg);
		createPositionables(this.lvl); 
	}
	
	//private double[][] createNoiseMap(){} // see if utils exists
	//private double[][] createLandMap() {}
	private void createPositionables(Level lvl) {
		int levelNumber = Settings.getLevel();
		int numberOfOpponents = lvl.getNumberOfOpponents();
		for (int i = 0; i < numberOfOpponents; i++) {
			createOneAnimal();
		}
		
	}
	
	private void createOneAnimal() { // or each animal apart? 
		
	}
	
	private void createOneBanana() {
		
	}
	
	//lvl.getGoal()
	//lvl.getInitialEnergy()
	public void evolve() {}
	public void draw() {}
	private void drawWorld() {} // called in draw to draw animals and nonlivings in field of view
	private void drawPlayer() {}
	//public String getTextureForDrawing() {}
	
	
}