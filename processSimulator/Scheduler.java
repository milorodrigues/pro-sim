package processSimulator;


public class Scheduler {
	
	protected static int lastPID;
	public Process current;
	
	public Scheduler() {
		lastPID = 0;
		current = null;
	}
	
	public int generatePID() {
		
		return lastPID + 1;
	}
	
	public void increaseLastPID() {
		lastPID++;
	}
	
	public boolean addProcess(int duration, int deadline, int priority, int delay) {
		
		return false;
	}
	
	public Object[][] getProcessData(){
		
		Object[][] dataArray = {};
		
		return dataArray;
	}
	
	public void updateCurrent() {
	}
}
