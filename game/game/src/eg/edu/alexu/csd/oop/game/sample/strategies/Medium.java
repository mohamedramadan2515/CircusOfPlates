package eg.edu.alexu.csd.oop.game.sample.strategies;

import java.util.*;

import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;
public class Medium extends CircusOfPlates {

	public Medium(int screenWidth, int screenHeight,Observable observable ) {
		super(screenWidth, screenHeight,observable , 2 );
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}

}
