package processSimulator;

public class Process {
	
	protected int pid;
	protected int duration;
	protected int timeleft;
	protected int deadline;
	protected int priority;
	protected int delay;
	
	public Process(int pid, int duration, int deadline, int priority, int delay) {
		this.pid = pid;
		this.duration = duration;
		this.timeleft = duration;
		this.deadline = deadline;
		this.priority = priority;
		this.delay = delay;
	}
	
}
