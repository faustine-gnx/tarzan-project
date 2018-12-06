package map;
import notmoving.Banana;
import notmoving.Flower;
import notmoving.Hut;
import notmoving.Jane;
import notmoving.Kavurus;
import notmoving.Knife;
import notmoving.NotLivings;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import animals.Animal;
import animals.Crocodile;
import animals.Elephant;
import animals.Lion;
import animals.Snake;
import animals.Tiger;
import interfaces.Drawable;
import tarzan.Tarzan;
import tilegame.Level;
import tilegame.Settings;

public class Map implements Drawable {
	private final static int SIZE_MAP = 20;
	// WHY DOES COMMIT NOT WORK ????
	
	
	double[][] landMap; // land type map
	//List<int[][],> occupationList = new ArrayList<>(); // 
	List<Object> positionnableList = new ArrayList <Object>();
	//Level lvl; // need for attribute ? or attribute of game? and getters? IN GAME
	//Settings setg; // need for attribute ? or attribute of game? and getters? IN GAME
	private Timer gameTimer;
	//private TimerTask task; // TimerTask is an abstract class
	
	
	//public final int sizeLandMap; //depends on level
	//private final static int[][] NOISE_MAP;
	//private final int[][] landMap; //initialized for each new game
	
	List<Animal> MapAnimals = new ArrayList<Animal>();
	List<NotLivings> MapNotLivings = new ArrayList<NotLivings>();
	//Animal[] MapAnimals;
	//NotLivings[] MapNotLivings;
	//List<Animal> animals = new ArrayList<Animal>();
	//List<NonLiving> nonLivings = new ArrayList<NonLiving>();
	Tarzan MapTarzan;
	
	
	public Map(Level l, Settings s){ // need to be public because not in same package as Game, which is calling it
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		int[][] randomTarzanPosition = new int[1][1]; // not random yet
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		double[][] landMap = mapGen.createMap(SIZE_MAP);
		//mapIm.visualize(array, "generatedMap");
		this.MapTarzan = new Tarzan(randomTarzanPosition, l, s);
		createPositionables(l); 
	}
	
	public Map(int[][] tarzanPosition, Level l, Settings s){
		this.gameTimer = new Timer(); 
		//this.task = new TimerTask();
		SimplexNoiseGenerator mapGen = new SimplexNoiseGenerator(10, 0.7, 0.008);
		MapImage mapIm = new MapImage();
		double[][] landMap = mapGen.createMap(SIZE_MAP);
		//mapIm.visualize(array, "generatedMap");
		this.MapTarzan = new Tarzan(tarzanPosition, l, s);
		createPositionables(l); 
	}
	
	
	private void createPositionables(Level lvl) {
		//int levelNumber = lvl.getLevelNumber();
		for (int i = 0; i < lvl.getNumberOfOpponents(); i++) {
			createOneOfEachAnimal();
		}
		
		// create NotLivings
		
	}
	
	// ALL THIS IN GAME? --> NO
	
	private void createOneOfEachAnimal() { // or each animal apart? 
		//random position checked if not occupied
		// create Lion
		Lion l = new Lion(randomPosition());
		MapAnimals.add(l);
		positionnableList.add(l);
		// create Tiger
		Tiger t = new Tiger(randomPosition());
		MapAnimals.add(t);
		positionnableList.add(t);
		
		Snake s = new Snake(randomPosition());
		MapAnimals.add(s);
		positionnableList.add(s);
		
		Elephant e = new Elephant(randomPosition());
		MapAnimals.add(e);
		positionnableList.add(e);
		
		Crocodile c = new Crocodile(randomPosition());
		MapAnimals.add(c);
		positionnableList.add(c);
	}
	
	int[][] randomPosition(){ // return a random position which is free
		return new int[1][1];
	}
	
	private void createOneBanana() {
		Banana b = new Banana(randomPosition());
		MapNotLivings.add(b);
		positionnableList.add(b);
	}
	
	private void createOneFlower() {
		Flower f = new Flower(randomPosition());
		MapNotLivings.add(f);
		positionnableList.add(f);
	}
	
	private void createOneHut() {
		Hut h = new Hut(randomPosition());
		MapNotLivings.add(h);
		positionnableList.add(h);
	}
	
	private void createJane() {
		Jane j = new Jane(randomPosition());
		MapNotLivings.add(j);
		positionnableList.add(j);
	}
	
	private void createOneKavurus() {
		Kavurus k = new Kavurus(randomPosition());
		MapNotLivings.add(k);
		positionnableList.add(k);
	}
	
	private void createOneKnife() {
		Knife k = new Knife(randomPosition());
		MapNotLivings.add(k);
		positionnableList.add(k);
	}
	
	public void run() { // wrt timer
		int secondsPassed = 0;
		secondsPassed++; 
		System.out.println ("Seconds passed:" + secondsPassed);
	}	
	
	//lvl.getGoal()
	//lvl.getInitialEnergy()
	public void evolve() {}
	public void draw() {}
	private void drawWorld() {} // called in draw to draw animals and nonlivings in field of view
	private void drawPlayer() {}
	//public String getTextureForDrawing() {}
	
	
}