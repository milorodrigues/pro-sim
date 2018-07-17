package processSimulator;


public class Scheduler {
	
	protected static int lastPID;
	public static int limit = 10;
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
	
	public String addProcess(int duration, int deadline, int priority, int delay) {
		
		return "fail";
	}
	
	public Object[][] getProcessData(){
		
		Object[][] dataArray = {};
		
		return dataArray;
	}
	
	public void updateCurrent() {
		if (current == null) {
			getNextProcess();
		}
		
		if (current != null) {
			if (current.timeleft <= 0) {
				endCurrentProcess();
			}
			current.timeleft--;
			Manager.swapper.pageLoop(current);
		}
	}
	
	public void getNextProcess() {
		current = null;
	}
	
	public void endCurrentProcess() {
		Manager.swapper.clearPagesFromDisk(current);
		getNextProcess();
	}
	
	public void updateCurrentQuantum() {
	}
	
	public void updateCurrentOverload() {
	}
	
	public void updateCurrentNext() {
	}
}
