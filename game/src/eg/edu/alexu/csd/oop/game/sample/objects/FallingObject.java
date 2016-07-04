package eg.edu.alexu.csd.oop.game.sample.objects;

import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class FallingObject implements GameObject{
	protected static final int MAX_MSTATE = 1;
	protected BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	protected int x;
	protected int y;
	protected boolean visible;
	protected int color;
	protected boolean horizontly;
	protected int state = 0;
	protected int clown = 0;
	protected int width;
	protected String path;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

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
	
	public void setHorziontal(boolean horizontly) {
		this.horizontly = horizontly;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}

	public abstract void setState(int state, int clown, int width);
	
	public abstract void setX(int mX);

	public abstract void setY(int mY);

	public abstract FallingObject clone();

}
