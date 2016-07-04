package eg.edu.alexu.csd.oop.game.sample.memento;

import java.util.LinkedList;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.sample.objects.StaticObject;

public class History {
	private long time;
	private int score;
	private int observcnt;
	private List<GameObject> constant;
	private List<GameObject> moving;
	private List<GameObject> control;

	public void setConstant(List<GameObject> constant) {
		this.constant = new LinkedList<GameObject>(constant);
	}

	public void setMoving(List<GameObject> moving) {
		this.moving = new LinkedList<GameObject>();
	}

	public void setControl(List<GameObject> control) {
		this.control = new LinkedList<>();
		for (int i = 0; i < control.size(); i++) {
			GameObject a = control.get(i);
			if (a instanceof StaticObject) {
				this.control.add(((StaticObject) a).clone());
			}
		}

	}

	public void setObservcnt(int observcnt) {
		this.observcnt = observcnt;
	}

	public int getObservcnt() {
		return observcnt;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<GameObject> getConstant() {
		return this.constant;
	}

	public List<GameObject> getMoving() {
		return this.moving;
	}

	public List<GameObject> getControl() {
		return this.control;
	}

	public long getTime() {
		return this.time;
	}

	public int getScore() {
		return this.score;
	}

}
