package processSimulator;

public class Scheduler {
	
	private static int lastPID;
	
	public Scheduler() {
		lastPID = 0;		
	}
	
	public int generatePID() {
		
		return lastPID + 1;
	}
	
	public void increaseLastPID() {
		lastPID++;
	}
	
	public boolean addProcess(int pid, int duration, int deadline, int priority, int delay) {
		
		return false;
	}

}
