package processSimulator;

public class Process {
	
	public int pid;
	protected int duration;
	protected int timeleft;
	protected int deadline;
	protected int priority;
	protected int delay;
	
	protected int lastTime = 0;
	
	public static int numPages = 10;
	
	public Process(int pid, int duration, int deadline, int priority, int delay) {
		this.pid = pid;
		this.duration = duration;
		this.timeleft = duration;
		this.deadline = deadline;
		this.priority = priority;
		this.delay = delay;
	}
	
}
