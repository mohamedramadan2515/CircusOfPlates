package eg.edu.alexu.csd.oop.game.sample.states;

public class FreezeState implements TimeState {
	private long MAX_TIME;
	private long currentTime;
	private long startTime;

	public FreezeState(long currentTime, long startTime, long MAX_TIME) {
		this.MAX_TIME = MAX_TIME;
		this.startTime = startTime;
		this.currentTime = currentTime;
	}

	public long getPassedTime() {
		return System.currentTimeMillis() - currentTime;
	}

	@Override
	public boolean timeOut() {
		return false;
	}

	@Override
	public int currentTime() {
		return (int) (MAX_TIME - (currentTime - startTime)) / 1000;
	}

}
