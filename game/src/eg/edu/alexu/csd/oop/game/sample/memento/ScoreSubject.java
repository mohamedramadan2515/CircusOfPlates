package eg.edu.alexu.csd.oop.game.sample.memento;

import java.util.HashMap;
import java.util.Observable;

public class ScoreSubject extends Observable {
	private int score;
	private HashMap<Integer, Boolean> scores;

	public ScoreSubject() {
		scores = new HashMap<>();
		score = 0;
		scores.put(5, true);
		scores.put(10, true);
		scores.put(15, true);
		scores.put(20, true);
	}

	private boolean isCritical(Integer score) {
		return scores.containsKey(score);
	}

	private void scoreChanged() {
		setChanged();
		notifyObservers();
	}

	public void setScore(int score) {
		if (this.score != score && isCritical(score)) {
			this.score = score;
			scoreChanged();
		}
	}

	public int getScore() {
		return score;
	}
}
