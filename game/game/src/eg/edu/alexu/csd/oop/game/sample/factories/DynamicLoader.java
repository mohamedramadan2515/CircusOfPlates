package eg.edu.alexu.csd.oop.game.sample.factories;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class DynamicLoader {

	private static DynamicLoader instance = null;
	private HashMap<String, Class<?>> pathToClass = new HashMap<String, Class<?>>();
	private HashMap<String, BufferedImage> pathToImage = new HashMap<String, BufferedImage>();
	
	private DynamicLoader(String path){
		try {
			loadAll(path);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("WRONG PATH");
		}
	}

	public static DynamicLoader getInstance(String path){
		if (instance == null) {
			instance = new DynamicLoader(path);
		}
		return instance;
	}
	
	public HashMap<String, BufferedImage> getImages() {
		return pathToImage;
	}
	
	public HashMap<String, Class<?>> getClasses() {
		return pathToClass;
	}
	
	public void loadAll(String path) throws ClassNotFoundException, IOException {
		File file = new File(path);
		URL url = file.toURI().toURL();
		URLClassLoader cl = new URLClassLoader(new URL[] { url }, ClassLoader.getSystemClassLoader());
		if (path.endsWith(".jar")) {
			JarFile jarFile = new JarFile(path);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				String className = ((JarEntry) entries.nextElement()).getName();
				if (className.endsWith(".class")) {
					className = (className.substring(0, className.length() - 6)).replace('/', '.');
					Class<?> clazz = cl.loadClass(className);
					if ((GameObject.class).isAssignableFrom(clazz)) {
						pathToClass.put(className.substring(className.lastIndexOf('.') + 1, className.length()), clazz);
					}
				}
				if (className.endsWith(".jpg") || className.endsWith(".png") || className.endsWith(".jpeg")) {
					pathToImage.put(className.substring(className.lastIndexOf('/') + 1, className.length()), ImageIO.read(cl.getResourceAsStream(className)));
				}
			}
			jarFile.close();
		}
		cl.close();
	}
}
