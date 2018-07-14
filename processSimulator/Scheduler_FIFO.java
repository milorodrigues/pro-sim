package processSimulator;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler_FIFO extends Scheduler {
	
	private static Queue<Process> queue;
	
	public Scheduler_FIFO() {
		super();
		queue = new LinkedList<Process>();
		
	}
	
	public boolean addProcess(int pid, int duration, int deadline, int priority, int delay) {
		
		Process newprocess = new Process(Manager.scheduler.generatePID(), duration, 0, 0, delay);
		
		boolean success = queue.add(newprocess); 
		if (success) {
			Manager.scheduler.increaseLastPID();
		}
		
		return success;
	}

}
