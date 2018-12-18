package highscores;

import java.util.*;
import java.io.*;

/**
 * @author Faustine & Martina
 * 
 *         The HighScoreManager is the most important class of the highscores'
 *         package. It creates a binary file to save the high scores. It permits
 *         to save the best scores, sort them, add new best score and the delete
 *         the one that are not best scores. The high scores are put in an
 *         Arraylist. The maximum number of best scores is set to 10.
 */

public class HighScoreManager {
	private final static int MAX_NUMBER_SCORES = 10; 
	private ArrayList<Score> scores; // variable Arraylist of the type "score". We will use it to work with the
										// scores inside the class
	private static final String HIGHSCORE_FILE = "scores.dat";// The name of the file where the Highscores will be saved
	private ObjectOutputStream outputStream;// Initializing an outputStream for working with the file
	private ObjectInputStream inputStream; // Initializing an inputStream for working with the file
	private ScoreComparator comparator;
	private String highScoreString;

	/**
	 *Constructor.
	 */
	public HighScoreManager() {
		//outputStream = null;
		//inputStream  = null;
		comparator = new ScoreComparator();
		scores = new ArrayList<Score>();
	}

	/**
	 * Getter. 
	 * @return scores
	 */
	public ArrayList<Score> getScores() {
		return scores;
	}
	
	/**
	 * Initialize the scores: load them and sort them. 
	 * @return scores
	 */
	public void initScores() {
		loadScoreFile();
		sort();
	}

	/**
	 * Sort the scores using a ScoreComparator and the collection.sort function of the Java Collection
	 * Class (part of java.util)
	 */
	private void sort() {
		Collections.sort(scores, comparator);
	}

	/**
	 * Add score to the scoreFile.
	 * @param name, score
	 */
	public void addScore(String name, int score) {
		loadScoreFile();
		scores.add(new Score(name, score));
		updateScoreFile();
	}

	/**
	 * load arraylist of score in the high scores file and put them into the
	 * scores-arraylist try catch necessary to avoid crashing
	 */
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject(); // does that work??
		} catch (FileNotFoundException e) {
			System.out.println("[Load] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Load] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Load] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Load] IO Error: " + e.getMessage());
			}
		}
	}

	/**
	 * writing on the score arraylist to the file
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(getHighScoreString());
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ", the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Getter. Max MAX_NUMBER_SCORES top players. // put the high scores into the label of the GUI // Not true!
	 * @return highScoreString
	 */
	public String getHighScoreString() {
		initScores();

		int i = 0;
		int x = scores.size();
		if (x > MAX_NUMBER_SCORES) {
			x = MAX_NUMBER_SCORES;
		}
		while (i < x) {
			highScoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
			i++;
		}
		return highScoreString;
	}
}