package eg.edu.alexu.csd.oop.game.sample.states;

public interface TimeState {
	public boolean timeOut();
	public int currentTime();
	public long getPassedTime();

}
