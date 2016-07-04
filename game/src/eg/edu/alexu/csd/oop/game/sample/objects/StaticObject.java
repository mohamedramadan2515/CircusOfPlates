package eg.edu.alexu.csd.oop.game.sample.objects;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class StaticObject implements GameObject {
	protected static final int MAX_MSTATE = 1;
	protected BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	protected int x;
	protected int y;
	protected boolean visible;

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract void setScreenWidth(int screenWidth);
	
	public abstract LinkedList<GameObject> checkTheStacks();

	public abstract FallingObject rightPeek();

	public abstract FallingObject leftPeek();

	public abstract void addRightStack(FallingObject righPlate);

	public abstract void addLeftStack(FallingObject leftPlate);
	
	public abstract Stack<FallingObject> getLft();

	public abstract Stack<FallingObject> getRyt();

	@Override
	public int getX() {
		return x;
	}

	@Override
	public abstract void setX(int x);

	@Override
	public int getY() {
		return y;
	}

	@Override
	public abstract void setY(int y);

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth() {
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public abstract int getType();

	public abstract void setType(int type);
	
	public abstract StaticObject clone();

}
