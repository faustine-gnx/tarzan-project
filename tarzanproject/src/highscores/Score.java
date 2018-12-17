package highscores;

/**
 * @author Faustine & Martina
 * 
 *         This class create an arraylist of the type Score 
 *         that contains the name and score of a player 
 *         we implement serializable to be able to sort this type. 
 */
import java.io.Serializable;

public class Score implements Serializable {
	/**
	 * make object of the type Score [name + score of player]
	 */
	private static final long serialVersionUID = 1L;
	private int score; // variable integers of the score
	private String naam; // variable string of the name

	/**
	 * Getter.
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Getter.
	 * 
	 * @return naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * define the score with string name and int score
	 */
	public Score(String naam, int score) {
		this.score = score;
		this.naam = naam;
	}
}