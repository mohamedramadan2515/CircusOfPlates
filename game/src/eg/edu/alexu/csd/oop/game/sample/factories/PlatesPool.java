package eg.edu.alexu.csd.oop.game.sample.factories;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;
import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;

public class PlatesPool implements PoolOfPlates {
	private static PlatesPool instance;
	private LinkedList<FallingObject> available;
	private LinkedList<FallingObject> inUse;
	private AbstractFactory factory;
	private final Logger logger = Logger.getLogger(PlatesPool.class);
	public static PlatesPool getInstance() {
		if (instance == null)
			instance = new PlatesPool();
		return instance;
	}

	public static void destroy() {
		instance = null;
	}

	private PlatesPool() {
		factory = FactoryProducer.getInstance().getFactory("Plate");
		available = new LinkedList<>();
		inUse = new LinkedList<>();
		for (int i = 0; i < 50; i++)
			available.add(factory.getRandomPlate(0, 0, 1));
	}

	@Override
	public FallingObject getPlate() {
		logger.debug("getPlate");
		if (available.isEmpty())
			available.add(factory.getRandomPlate(0, 0, 1));
		FallingObject plate = available.removeLast();
		inUse.add(plate);
		return plate;
	}

	@Override
	public void releasePlate(FallingObject plate) {
		logger.debug("releasePlate");
		inUse.remove(plate);
		available.push(plate);
	}
	
}
