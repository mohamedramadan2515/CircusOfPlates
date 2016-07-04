package eg.edu.alexu.csd.oop.game.sample.memento;

import java.util.HashMap;

public class MementoManager {

	private HashMap<Integer, Memento> scoresToMementos = new HashMap<Integer, Memento>();

	public void snapShot(Memento memento, int score) {
		if (scoresToMementos.get(Integer.valueOf(score)) == null)
			scoresToMementos.put(Integer.valueOf(score), memento);
	}

	public Memento restorFromMemento(int score) {
		return scoresToMementos.get(Integer.valueOf(score));
	}

}
