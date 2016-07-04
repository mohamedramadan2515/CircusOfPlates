package eg.edu.alexu.csd.oop.game.sample.world;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.sample.factories.AbstractFactory;
import eg.edu.alexu.csd.oop.game.sample.factories.DynamicLoader;
import eg.edu.alexu.csd.oop.game.sample.factories.FactoryProducer;
import eg.edu.alexu.csd.oop.game.sample.factories.PlatesPool;
import eg.edu.alexu.csd.oop.game.sample.iterator.Iterator;
import eg.edu.alexu.csd.oop.game.sample.iterator.RemovedPlateIterator;
import eg.edu.alexu.csd.oop.game.sample.memento.History;
import eg.edu.alexu.csd.oop.game.sample.memento.IMemento;
import eg.edu.alexu.csd.oop.game.sample.memento.Memento;
import eg.edu.alexu.csd.oop.game.sample.memento.MementoManager;
import eg.edu.alexu.csd.oop.game.sample.memento.ScoreSubject;
import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;
import eg.edu.alexu.csd.oop.game.sample.objects.StaticObject;
import eg.edu.alexu.csd.oop.game.sample.states.FreezeState;
import eg.edu.alexu.csd.oop.game.sample.states.RunningState;
import eg.edu.alexu.csd.oop.game.sample.states.TimeState;;

public abstract class CircusOfPlates implements World, Observer {
	private static long MAX_TIME = 3 * 60 * 1000;
	private Observable observable;
	protected int obserevercnt = 1;
	private boolean load=false;
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private final AbstractFactory clownFactory = FactoryProducer.getInstance().getFactory("Clown");
	private final AbstractFactory platesFactory = FactoryProducer.getInstance().getFactory("Plate");
	private List<GameObject> constant = new LinkedList<GameObject>();
	private List<GameObject> moving = new LinkedList<GameObject>();
	private List<GameObject> control = new LinkedList<GameObject>();
	private int numberOfClowns;
	private TimeState state;
	private MementoManager mementoManger = new MementoManager();
	private DynamicLoader dl = DynamicLoader.getInstance("");
	private final Logger logger = Logger.getLogger(CircusOfPlates.class);

	public CircusOfPlates(int screenWidth, int screenHeight, Observable observable, int numberOfClowns) {
		width = screenWidth;
		height = screenHeight;
		this.numberOfClowns = numberOfClowns;
		this.observable = observable;
		observable.addObserver(this);
		int clownWidth = addClown(width / 3);
		if (numberOfClowns > 1) {
			for (int i = 0; i < numberOfClowns - 1; i++) {
				addClown(width / 3 + (clownWidth + 100) * (i + 1));
			}
		}
		for (int i = 0; i < 20; i++) {
			FallingObject plate = PlatesPool.getInstance().getPlate();
			plate.setX((int) (Math.random() * screenWidth));
			plate.setY(-1 * (int) (Math.random() * screenHeight));
			plate.setVisible(true);
			moving.add(plate);
		}
		StaticObject background = imgObject(0, 0, "lfc.jpg", 0);
		background.setY((int) (screenHeight - background.getHeight()));
		constant.add(background);
		setState(new RunningState(startTime, MAX_TIME));
	}

	private StaticObject imgObject(int i, int j, String path, int k) {
		Class<?> clazz = dl.getClasses().get("ImageObject");
		try {
			Constructor<?> con = clazz.getConstructor(int.class, int.class, String.class, int.class);
			return (StaticObject) con.newInstance(i, j, path, k);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int addClown(int widthOfClown) {
		StaticObject clown = clownFactory.getClown(widthOfClown, -1000, "stdg.png");
		clown.setScreenWidth(width);
		clown.setY((int) (height - clown.getHeight()));
		FallingObject rightPlate = platesFactory.getRandomPlate(widthOfClown + clown.getWidth() * 1 / 45, (int) (height - clown.getHeight()), 2);
		FallingObject leftPlate = platesFactory.getRandomPlate(widthOfClown + clown.getWidth() * 7 / 10, (int) (height - clown.getHeight()), 2);
		control.add(clown);
		control.add(rightPlate);
		control.add(leftPlate);
		clown.addRightStack(rightPlate);
		clown.addLeftStack(leftPlate);
		return clown.getWidth();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ScoreSubject) {
			obserevercnt++;
			moving.add(imgObject((int) (Math.random() * width), -1 * (int) (Math.random() * height), "star.png", 0));
			History h = new History();
			this.creatMementoShot(h);
		}

	}

	private void addPlate() {
		FallingObject plate = PlatesPool.getInstance().getPlate();
		plate.setX((int) (Math.random() * width));
		plate.setY(-1 * (int) (Math.random() * height));
		plate.setVisible(true);
		moving.add(plate);
	}

	private boolean intersect(GameObject o1, GameObject o2) {
		//logger.debug("intersect");
		return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth())
				&& (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
	}

	private void setState(TimeState state) {
		this.state = state;
	}

	@Override
	public boolean refresh() {
		boolean timeout = state.timeOut();
		if (state.getPassedTime() >= 5 * 1000) {
			setState(new RunningState(startTime + state.getPassedTime(), MAX_TIME));
		}
		LinkedList<GameObject> look = new LinkedList<GameObject>();
		for (GameObject m : moving) {
			m.setY((m.getY() + obserevercnt));
			if (m.getY() >= getHeight()) {
				m.setY(-1 * (int) (Math.random() * getHeight()));
				m.setX((int) (Math.random() * getWidth()));
				if (m instanceof StaticObject) {
					look.add(m);
				}
			}
			for (int i = 0; i < numberOfClowns * 3; i += 3) {
				StaticObject c = (StaticObject) control.get(i);
				GameObject ry = c.rightPeek();
				GameObject lf = c.leftPeek();
				if (ry.getY() <= 0 || lf.getY() <= 0) {
					if (score > 5&&!load) {
						setMemento(5);
						load=true;
						return true;
					} else {
						setState(new RunningState(MAX_TIME, MAX_TIME));
						return false;
					}
				}
				if (m instanceof StaticObject) {
					if (!timeout && intersect(c, m)) {
						setState(new FreezeState(System.currentTimeMillis(), MAX_TIME, startTime));
						look.add(m);
					}
				} else if (!timeout && intersect(m, ry)) {
					m.setX(ry.getX());
					m.setY(ry.getY() - 10);
					c.addRightStack((FallingObject) m);
					look.add(m);
					control.add(m);
				} else if (!timeout && intersect(m, lf)) {
					m.setX(lf.getX());
					m.setY(lf.getY() - 10);
					c.addLeftStack((FallingObject) m);
					look.add(m);
					control.add(m);
				}
			}
		}
		
		Iterator iterator = new RemovedPlateIterator(look);
		while (iterator.hasNext()) {
			GameObject a = iterator.next();
			moving.remove(a);
			if (!(a instanceof StaticObject))
				addPlate();
		}
		for (int i = 0; i < numberOfClowns * 3; i += 3) {
			StaticObject c = (StaticObject) control.get(i);
			LinkedList<GameObject> toberemoved = c.checkTheStacks();
			score += toberemoved.size() / 3;
			for (GameObject g : toberemoved) {
				control.remove(g);
			}
		}
		((ScoreSubject) observable).setScore(score);
		return !timeout;
	}

	@Override
	public abstract int getSpeed();

	@Override
	public int getControlSpeed() {
		return 15;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {
		return "Please Use Arrows To Move | Score = " + score + " | Time = " + state.currentTime() + " s";
	}

	public void creatMementoShot(History history) {
		history.setConstant(this.constant);
		history.setControl(this.control);
		history.setObservcnt(this.obserevercnt);
		history.setScore(this.score);
		history.setTime(System.currentTimeMillis());
		IMemento memento = new Memento(history);
		mementoManger.snapShot((Memento) memento, score);
	}

	public void setMemento(int score) {
		IMemento memento = this.mementoManger.restorFromMemento(score);
		this.constant = memento.getHistory().getConstant();
		this.control = memento.getHistory().getControl();
		this.obserevercnt = memento.getHistory().getObservcnt();
		LinkedList<GameObject> temp = new LinkedList<GameObject>();
		List<GameObject> temp2 = new LinkedList<GameObject>();

		for (int i = 0; i < control.size(); i++) {
			GameObject a = control.get(i);
			if (a instanceof StaticObject) {
				temp2.add(a);
				Stack<FallingObject> rty = ((StaticObject) a).getRyt();
				for (FallingObject b : rty) {
					temp.add(b);
				}
				Stack<FallingObject> lft = ((StaticObject) a).getLft();
				for (FallingObject b : lft) {
					temp.add(b);
				}
			}
		}
		control.clear();
		for (int i = 0; i < temp2.size(); i++) {
			control.add(temp2.get(i));
		}
		for (int i = 0; i < temp.size(); i++) {
			control.add(temp.get(i));
		}
		if (numberOfClowns == 2) {
			GameObject clown = control.get(1);
			GameObject swap = control.get(3);
			control.remove(1);
			control.remove(2);
			control.add(1, swap);
			control.add(3, clown);
		}
		moving = new LinkedList<GameObject>();
		for (int i = 0; i < 20; i++) {
			FallingObject plate = PlatesPool.getInstance().getPlate();
			plate.setX((int) (Math.random() * width));
			plate.setY(-1 * (int) (Math.random() * height));
			plate.setVisible(true);
			moving.add(plate);
		}
		startTime += (System.currentTimeMillis() - memento.getHistory().getTime());
		this.score = memento.getHistory().getScore();
		state = new RunningState(startTime, MAX_TIME);
	}

}
