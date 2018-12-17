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
	private ArrayList<Score> scores; // variable Arraylist of the type "score". We will use it to work with the
										// scores inside the class
	private static final String HIGHSCORE_FILE = "scores.dat";// The name of the file where the Highscores will be saved
	ObjectOutputStream outputStream = null;// Initializing an outputStream for working with the file
	ObjectInputStream inputStream = null; // Initializing an inputStream for working with the file

	HighScoreManager highscoreManager = new HighScoreManager(); // create object highscores manager

	/**
	 * initialize the scores in a arraylist
	 */
	public HighScoreManager() {
		scores = new ArrayList<Score>();
	}

	/**
	 * get the scores as an array It contains calls to the function loadScoreFile()
	 * and sort(), these functions will make sure you have the scores in a sorted
	 * order
	 */
	public ArrayList<Score> getScores() {
		loadScoreFile();
		sort();
		return scores;
	}

	/**
	 * tell Java that I want the scores sorted create a new object comparator which
	 * compares the class we use the collection.sort function of the Java Collection
	 * Class (part of java.util)
	 */
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	/**
	 * This method is to add scores to the scorefile. Parameters "name" and "score"
	 * are the name of the player and the score he had. First the scores that are
	 * allready in the high-score file are loaded into the "scores"-arraylist. After
	 * that the new scores are added to the arraylist and the high-score file is
	 * updated with it.
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
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	/**
	 * writing on the score arraylist to the file
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
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
	 * max top players set to 10 and put the high scores into the label of the GUI
	 */
	public String getHighScoreString() {
		String highscoreString = "";
		int max = 10; // static or not?

		ArrayList<Score> scores;
		scores = getScores();

		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			highscoreString += (i + 1) + ".\t" + scores.get(i).getNaam() + "\t\t" + scores.get(i).getScore() + "\n";
			i++;
		}

		return highscoreString;

	}

}