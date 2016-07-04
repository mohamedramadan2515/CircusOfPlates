package eg.edu.alexu.csd.oop.game.sample.memento;

import java.util.HashMap;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;

public class MementoManager {
	private final Logger logger = Logger.getLogger(CircusOfPlates.class);
	private HashMap<Integer, Memento> scoresToMementos = new HashMap<Integer, Memento>();

	public void snapShot(Memento memento, int score) {
		if (scoresToMementos.get(Integer.valueOf(score)) == null)
			scoresToMementos.put(Integer.valueOf(score), memento);
			logger.debug("snapShot"+score);
	}

	public Memento restorFromMemento(int score) {
		return scoresToMementos.get(Integer.valueOf(score));
	}

}
