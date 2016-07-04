package eg.edu.alexu.csd.oop.game.sample.strategies;
import java.util.*;

import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;
public class Easy   extends CircusOfPlates{

	public Easy(int screenWidth, int screenHeight,Observable observable ) {
		super(screenWidth, screenHeight,observable , 1);
	}
	
	@Override
	public int getSpeed() {
		return 15;
	}

}
