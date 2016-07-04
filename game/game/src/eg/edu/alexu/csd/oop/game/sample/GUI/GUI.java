package eg.edu.alexu.csd.oop.game.sample.GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import eg.edu.alexu.csd.oop.game.sample.factories.DynamicLoader;

public class GUI {
	private JFrame frame;
	private Command leader;

	public void run() {
		initializeFrame();
	}

	private void initializeFrame() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		if (fc.getSelectedFile() == null) {
			JOptionPane.showMessageDialog(null, "No file chosen the program will terminate !");
			return;
		}
		String path = fc.getSelectedFile().getAbsolutePath();
		DynamicLoader dl = DynamicLoader.getInstance(path);
		frame = new JFrame("Circus of Plate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			frame.setContentPane(new JLabel(new ImageIcon(dl.getImages().get("Klopp.jpg"))));
			frame.setResizable(false);
			frame.pack();
		} catch (Exception e) {
			frame.setSize(400, 400);
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.setVisible(true);
		leader = new Command(frame);
		createButton("Easy", -100);
		createButton("Medium", 0);
		createButton("Hard", 100);

	}

	private void createButton(String name, int height) {
		JButton b = new JButton(name);
		b.setSize(100, 50);
		b.setLocation(frame.getSize().width / 2 - 50, frame.getSize().height / 2 + height);
		b.addActionListener(leader);
		frame.getContentPane().add(b);
	}
}
