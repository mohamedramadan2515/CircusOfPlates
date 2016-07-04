package eg.edu.alexu.csd.oop.game.sample;

import java.awt.EventQueue;
import eg.edu.alexu.csd.oop.game.sample.GUI.GUI;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI g = new GUI();
					g.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
