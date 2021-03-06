package eg.edu.alexu.csd.oop.game.sample.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;
import eg.edu.alexu.csd.oop.game.sample.objects.StaticObject;

public class ClownFactory extends AbstractFactory {
	private static ClownFactory instance;
	private LinkedList<Class<? extends StaticObject>> clownList;

	@SuppressWarnings("unchecked")
	private ClownFactory() {
		clownList = new LinkedList<>();
		clownList.add((Class<? extends StaticObject>) dl.getClasses().get("Clown"));
	}

	public static ClownFactory getInstance() {
		if (instance == null)
			instance = new ClownFactory();
		return instance;
	}

	public static void destroy() {
		instance = null;
	}

	@Override
	public StaticObject getClown(int posX, int posY, String path) {
		int randomIdx = (int) (Math.random() * clownList.size());
		Constructor<?> ctor = null;
		try {
			ctor = clownList.get(randomIdx).getConstructor(new Class[] { int.class, int.class, String.class });
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return (StaticObject) ctor.newInstance(posX, posY, path);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FallingObject getRandomPlate(int posX, int posY, int type) {
		return null;
	}


}
