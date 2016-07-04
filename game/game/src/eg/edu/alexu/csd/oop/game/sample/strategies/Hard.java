package eg.edu.alexu.csd.oop.game.sample.strategies;

import java.util.*;

import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;
public class Hard  extends CircusOfPlates{

	public Hard(int screenWidth, int screenHeight,Observable observable ) {
		super(screenWidth, screenHeight,observable , 1);
		obserevercnt = 2;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int getControlSpeed() {
		return 25;
	}

}
