package highscores;

import java.util.Comparator;

/**
 * @author Faustine & Martina 
 * 
 * 			The ScoreComparator class is used to compare two Scores.
 * 
 */

public class ScoreComparator implements Comparator<Score> {
	
	/**
	 * Compare the values of two Scores.
	 * @param score1, score2
	 * @return int 
	 */
	public int compare(Score score1, Score score2) {

		int sc1 = score1.getScore(); // variable with getters to get one score
		int sc2 = score2.getScore(); // variable with getters to get second score

		if (sc1 > sc2) {
			return -1;// -1 means the first score is greater than the 2nd one
		} else if (sc1 < sc2) {
			return 1; // 1 means it's smaller
		} else {
			return 0;// and 0 means it's equal.
		}
	}
}
