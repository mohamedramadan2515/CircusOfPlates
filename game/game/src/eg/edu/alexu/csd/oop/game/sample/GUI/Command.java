package eg.edu.alexu.csd.oop.game.sample.GUI;

import eg.edu.alexu.csd.oop.game.sample.memento.ScoreSubject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.sample.world.CircusOfPlates;

public class Command implements ActionListener {
	JFrame frame;

	public Command(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String buttonValue = ((JButton) arg0.getSource()).getText();
		buttonValue = "eg.edu.alexu.csd.oop.game.sample.strategies." + buttonValue;
		try {
			Class<?> c = Class.forName(buttonValue);
			if (CircusOfPlates.class.isAssignableFrom(c)) {
				Constructor<?> con = c.getConstructor(Integer.TYPE, Integer.TYPE, Observable.class);
				Observable observ = new ScoreSubject();
				CircusOfPlates game = (CircusOfPlates) con.newInstance(690, 690, observ);
				GameEngine.start("Circus Of Plates", game);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "No such game exist", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
