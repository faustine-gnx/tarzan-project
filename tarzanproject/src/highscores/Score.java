package highscores;
import java.io.Serializable;

// make object of the type Score [name + score of player]

public class Score implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private String naam;

	public int getScore() {
		return score;
	}

	public String getNaam() {
		return naam;
	}

	public Score(String naam, int score) {
		this.score = score;
		this.naam = naam;
	}
}