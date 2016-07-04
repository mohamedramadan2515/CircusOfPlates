package eg.edu.alexu.csd.oop.game.sample.factories;

import eg.edu.alexu.csd.oop.game.sample.objects.FallingObject;

public interface PoolOfPlates {
	public FallingObject getPlate();
	public void releasePlate(FallingObject plate);
}
