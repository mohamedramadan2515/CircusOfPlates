package eg.edu.alexu.csd.oop.game.sample.states;

public class RunningState implements TimeState{
	private long MAX_TIME ;
	private long startTime ;
	public RunningState(long startTime , long MAX_TIME){
		this.MAX_TIME  = MAX_TIME ;
		this.startTime = startTime;
	}
	@Override
	public boolean timeOut() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() - startTime > MAX_TIME;
	}
	@Override
	public int currentTime() {
		// TODO Auto-generated method stub
		return (int)Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000) ;
	}
	@Override
	public long getPassedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
