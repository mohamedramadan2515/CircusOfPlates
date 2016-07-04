package eg.edu.alexu.csd.oop.game.sample.memento;

public class Memento implements IMemento {
	private History history;

	public Memento(History history) {
		this.setHistory(history);
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
}
