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
	private static final long serialVersionUID = 1L;
	private int score; // variable integers of the score
	private String name; // variable string of the name
	
	/**
	 * Constructor.
	 * @param name, score
	 */
	public Score(String name, int score) {
		this.score = score;
		this.name = name;
	}

	/**
	 * Getter.
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Getter.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Setter.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}