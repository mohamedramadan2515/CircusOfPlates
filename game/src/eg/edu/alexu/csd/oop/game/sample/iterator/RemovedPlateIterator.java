package eg.edu.alexu.csd.oop.game.sample.iterator;

import java.util.LinkedList;
import eg.edu.alexu.csd.oop.game.GameObject;

public class RemovedPlateIterator implements Iterator {
	private LinkedList<GameObject> plates;
	private int position;

	public RemovedPlateIterator(LinkedList<GameObject> plates) {
		this.plates = plates;
		position = 0;
	}

	@Override
	public boolean hasNext() {
		return position < plates.size();
	}

	@Override
	public GameObject next() {
		if (!hasNext()) {
			throw new NullPointerException();
		}
		GameObject plate = plates.get(position);
		position++;
		return plate;
	}

}
