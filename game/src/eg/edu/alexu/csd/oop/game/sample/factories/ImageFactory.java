package eg.edu.alexu.csd.oop.game.sample.factories;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageFactory {
	private static ImageFactory instance = null;
	private HashMap<String, BufferedImage> ImagePathToImage = DynamicLoader.getInstance("").getImages();

	private ImageFactory() { }

	public static ImageFactory getInstance() {
		if (instance == null)
			instance = new ImageFactory();
		return instance;
	}

	public BufferedImage getImage(String path) {
		return ImagePathToImage.get(path);
	}
}
