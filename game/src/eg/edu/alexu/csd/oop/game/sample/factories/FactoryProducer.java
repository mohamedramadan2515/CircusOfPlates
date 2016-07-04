package eg.edu.alexu.csd.oop.game.sample.factories;

import eg.edu.alexu.csd.oop.game.sample.factories.AbstractFactory;

public class FactoryProducer {
	private static FactoryProducer instance;

	public static FactoryProducer getInstance() {
		if (instance == null)
			instance = new FactoryProducer();
		return instance;
	}

	public static void destroy() {
		instance = null;
	}

	private FactoryProducer() {
	}

	public AbstractFactory getFactory(String name) {
		if (name == "Plate") {
			return PlateFactory.getInstance();
		} else if (name == "Clown") {
			return ClownFactory.getInstance();
		}
		return null;
	}
}
