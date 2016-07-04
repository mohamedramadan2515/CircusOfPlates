package eg.edu.alexu.csd.oop.game.sample.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;
import eg.edu.alexu.csd.oop.game.sample.objects.StaticObject;

public class PlateFactory extends AbstractFactory {
	private static PlateFactory instance;
	private ArrayList<Class<? extends FallingObject>> plateClasses;

	public static PlateFactory getInstance() {
		if (instance == null)
			instance = new PlateFactory();
		return instance;
	}

	public static void destroy() {
		instance = null;
	}

	@SuppressWarnings("unchecked")
	private PlateFactory() {
		plateClasses = new ArrayList<>();
		plateClasses.add((Class<? extends FallingObject>) dl.getClasses().get("Plate"));
	}

	@Override
	public StaticObject getClown(int posX, int posY, String path) {
		return null;
	}

	@Override
	public FallingObject getRandomPlate(int posX, int posY, int type) {
		if (type == 1) {
			int random = (int) (1 + Math.random() * 3);
			int randomIdx = (int) (Math.random() * plateClasses.size());
			Constructor<?> con = null;
			try {
				con = plateClasses.get(randomIdx).getConstructor(new Class[] { int.class, int.class, String.class, int.class, boolean.class });
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			try {
				return (FallingObject) con.newInstance(posX, posY, random + ".png", random, false);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			} 
		} else {
			int randomIdx = (int) (Math.random() * plateClasses.size());
			Constructor<?> ctor = null;
			try {
				ctor = plateClasses.get(randomIdx).getConstructor(new Class[] { int.class, int.class, String.class, int.class, boolean.class });
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			try {
				return (FallingObject) ctor.newInstance(posX, posY, "fixedplate.png", 0, true);
			} catch (InstantiationException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}
