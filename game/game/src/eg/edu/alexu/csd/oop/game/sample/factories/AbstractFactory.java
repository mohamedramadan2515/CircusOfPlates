package eg.edu.alexu.csd.oop.game.sample.factories;

import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;
import eg.edu.alexu.csd.oop.game.sample.objects.StaticObject;

public abstract class AbstractFactory {
	protected DynamicLoader dl = DynamicLoader.getInstance("");
	public abstract StaticObject getClown(int posX,int posY,String path);
	public abstract FallingObject getRandomPlate(int posX,int posY,int type);
}
